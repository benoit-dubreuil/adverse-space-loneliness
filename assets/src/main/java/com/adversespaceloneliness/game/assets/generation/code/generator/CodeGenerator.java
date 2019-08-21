package com.adversespaceloneliness.game.assets.generation.code.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Generated;
import javax.lang.model.element.Modifier;
import java.io.File;

/**
 * Simplifies the code generation concrete classes by doing the repetitive work.
 */
public abstract class CodeGenerator implements ICodeGenerator {

    protected static final String FILENAME_SEPARATOR = "_";

    protected TypeSpec.Builder m_typeBuilder;

    public CodeGenerator() {
        AnnotationSpec generatedAnnotation = AnnotationSpec.builder(Generated.class).addMember("value", "$S", getClass().getName()).build();
        m_typeBuilder = TypeSpec.enumBuilder(getGeneratedTypeName()).addModifiers(Modifier.PUBLIC).addAnnotation(generatedAnnotation);
    }

    public abstract String getGeneratedTypeName();

    @Override
    public void endGeneration() {
        TypeSpec assetIDs = m_typeBuilder.build();
        JavaFile javaFile = JavaFile.builder(OUTPUT_PACKAGE, assetIDs).build();

        try {
            javaFile.writeTo(new File(OUTPUT_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
