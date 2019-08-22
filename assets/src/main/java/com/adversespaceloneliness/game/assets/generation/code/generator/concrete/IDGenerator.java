package com.adversespaceloneliness.game.assets.generation.code.generator.concrete;

import com.adversespaceloneliness.game.assets.api.AssetType;
import com.adversespaceloneliness.game.assets.api.IAssetID;
import com.adversespaceloneliness.game.assets.generation.code.generator.CodeGenerator;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Generates the code file that contains the IDs for all assets.
 */
public class IDGenerator extends CodeGenerator {

    private static final String ENUM_NAME = "AssetID";

    private static final String FIELD_PATH_NAME = "m_path";
    private static final String FIELD_ASSET_TYPE_NAME = "m_assetType";

    private static final String FIELD_PATH_KEY = "path";
    private static final String FIELD_ASSET_TYPE_KEY = "assetType";

    private static final String METHOD_LOAD_ASSET_NAME = "loadAsset";
    private static final String METHOD_LOAD_ASSET_PARAM_ASSET_MANAGER = "assetManager";
    private static final String METHOD_LOAD_ASSET_PARAM_PARAMETERS = "parameters";

    private static final String METHOD_LOAD_ASSET_JAVADOC =
        " Loads the asset using the default parameters.\n" + "\n" + " @param assetManager The asset manager used to load this asset.\n";

    private static final String METHOD_LOAD_ASSET_OVERLOAD_JAVADOC = " Loads the asset using the supplied parameters.\n" + " <p>\n"
        + " The generic parameters parameterized type must be the same as the one returned by {@link AssetType#getImportClass()} for this asset.\n" + "\n"
        + " @param assetManager The asset manager used to load this asset.\n" + " @param parameters   The parameters used to load this asset.\n";

    private Map<String, String> m_idNames;
    private Set<String> m_duplicateIdNames;

    public IDGenerator() {
        m_idNames = new HashMap<>();
        m_duplicateIdNames = new HashSet<>();
    }

    @Override
    public boolean isDirPathGeneratable(String generatedDirPath) {
        return true;
    }

    @Override
    public void generate(String assetPath) {
        try (Stream<Path> walk = Files.walk(Paths.get(assetPath))) {

            Stream<String> assetsInPath = walk.filter(Files::isRegularFile).map(Path::toString);
            assetsInPath.forEach(asset -> addID(asset));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endGeneration() {
        m_typeBuilder.addSuperinterface(IAssetID.class);

        addEnumFields();
        addGetters();
        addConstructor();
        addEnumConstants();
        addMethodLoadAsset();
        addMethodLoadAssetOverload();

        super.endGeneration();
    }

    @Override
    public String getGeneratedTypeName() {
        return ENUM_NAME;
    }

    private void addEnumFields() {
        FieldSpec pathField = FieldSpec.builder(String.class, FIELD_PATH_NAME, Modifier.PRIVATE).build();
        FieldSpec assetTypeField = FieldSpec.builder(AssetType.class, FIELD_ASSET_TYPE_NAME, Modifier.PRIVATE).build();

        m_typeBuilder.addField(pathField);
        m_typeBuilder.addField(assetTypeField);
    }

    private void addGetters() {
        for (Method getter : IAssetID.class.getMethods()) {
            MethodSpec.Builder pathGetterBuilder = MethodSpec.methodBuilder(getter.getName());

            pathGetterBuilder.addAnnotation(Override.class);
            pathGetterBuilder.addModifiers(Modifier.PUBLIC);
            pathGetterBuilder.returns(getter.getReturnType());

            if (StringUtils.containsIgnoreCase(getter.getName(), FIELD_PATH_KEY)) {
                pathGetterBuilder.addStatement("return $L", FIELD_PATH_NAME);
            }
            else if (StringUtils.containsIgnoreCase(getter.getName(), FIELD_ASSET_TYPE_KEY)) {
                pathGetterBuilder.addStatement("return $L", FIELD_ASSET_TYPE_NAME);
            }

            m_typeBuilder.addMethod(pathGetterBuilder.build());
        }
    }

    private void addConstructor() {
        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder();

        constructorBuilder.addParameter(String.class, FIELD_PATH_KEY);
        constructorBuilder.addParameter(AssetType.class, FIELD_ASSET_TYPE_KEY);

        constructorBuilder.addStatement("$L = $L", FIELD_PATH_NAME, FIELD_PATH_KEY);
        constructorBuilder.addStatement("$L = $L", FIELD_ASSET_TYPE_NAME, FIELD_ASSET_TYPE_KEY);

        m_typeBuilder.addMethod(constructorBuilder.build());
    }

    private void addEnumConstants() {
        for (Map.Entry<String, String> idNamePath : m_idNames.entrySet()) {
            AssetType enumConstantAssetType = AssetType.getAssetTypeFromExtension(FilenameUtils.getExtension(idNamePath.getValue()));
            TypeSpec.Builder enumConstantBuilder = TypeSpec.anonymousClassBuilder("$S, $T.$L", idNamePath.getValue(), AssetType.class, enumConstantAssetType);

            m_typeBuilder.addEnumConstant(idNamePath.getKey(), enumConstantBuilder.build());
        }
    }

    private void addMethodLoadAsset() {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(METHOD_LOAD_ASSET_NAME);

        methodBuilder.addModifiers(Modifier.PUBLIC);
        methodBuilder.addJavadoc(METHOD_LOAD_ASSET_JAVADOC);
        methodBuilder.addParameter(AssetManager.class, METHOD_LOAD_ASSET_PARAM_ASSET_MANAGER);

        methodBuilder.addStatement("$L.load($L, $L.getImportClass())", METHOD_LOAD_ASSET_PARAM_ASSET_MANAGER, FIELD_PATH_NAME, FIELD_ASSET_TYPE_NAME);

        m_typeBuilder.addMethod(methodBuilder.build());
    }

    private void addMethodLoadAssetOverload() {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(METHOD_LOAD_ASSET_NAME);

        methodBuilder.addModifiers(Modifier.PUBLIC);
        methodBuilder.addJavadoc(METHOD_LOAD_ASSET_OVERLOAD_JAVADOC);
        methodBuilder.addParameter(AssetManager.class, METHOD_LOAD_ASSET_PARAM_ASSET_MANAGER);
        methodBuilder.addParameter(AssetLoaderParameters.class, METHOD_LOAD_ASSET_PARAM_PARAMETERS);

        AnnotationSpec.Builder suppressWarningsAnnot = AnnotationSpec.builder(SuppressWarnings.class);
        suppressWarningsAnnot.addMember("value", "$S", "unchecked");
        methodBuilder.addAnnotation(suppressWarningsAnnot.build());

        methodBuilder.addStatement("$L.load($L, $L.getImportClass(), $L)",
            METHOD_LOAD_ASSET_PARAM_ASSET_MANAGER,
            FIELD_PATH_NAME,
            FIELD_ASSET_TYPE_NAME,
            METHOD_LOAD_ASSET_PARAM_PARAMETERS);

        m_typeBuilder.addMethod(methodBuilder.build());
    }

    /**
     * Adds an enum ID metadata to the {@link #m_idNames} data structure.
     * <p>
     * If two assets have the same name (no path, no extension), then the extension is added to the enum ID name. However, if two assets have the same name and same extension but
     * with a different path, then it's an error.
     *
     * @param asset The asset to add its metadata to the data structure.
     */
    private void addID(String asset) {
        String idPath = FilenameUtils.separatorsToUnix(asset);
        String idName = FilenameUtils.getBaseName(idPath).toUpperCase().replaceAll(" ", FILENAME_SEPARATOR);

        if (m_idNames.containsKey(idName)) {
            m_duplicateIdNames.add(idName);

            String similarIdPath = m_idNames.remove(idName);
            String similarIdName = assembleDuplicateIdName(idName, similarIdPath);

            m_idNames.put(similarIdName, similarIdPath);
        }

        if (m_duplicateIdNames.contains(idName)) {
            idName = assembleDuplicateIdName(idName, idPath);
        }

        m_idNames.put(idName, idPath);
    }

    /**
     * Assembles the duplicate enum ID name.
     * <p>
     * In other words, it adds the extension at the end of the enum ID name.
     *
     * @param idName The duplicate enum ID name that must be assembled.
     * @param idPath The path of the duplicate enum ID name.
     *
     * @return Returns the assembled duplicate enum ID name.
     */
    private String assembleDuplicateIdName(String idName, String idPath) {
        return idName + FILENAME_SEPARATOR + FilenameUtils.getExtension(idPath).toUpperCase();
    }
}
