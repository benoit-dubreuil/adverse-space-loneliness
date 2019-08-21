package com.adversespaceloneliness.game.assets.generation.code.generator.concrete;

import com.adversespaceloneliness.game.assets.api.AssetType;
import com.adversespaceloneliness.game.assets.api.IAssetID;
import com.adversespaceloneliness.game.assets.generation.code.generator.CodeGenerator;
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
