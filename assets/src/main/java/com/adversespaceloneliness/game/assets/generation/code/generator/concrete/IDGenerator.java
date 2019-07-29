package com.adversespaceloneliness.game.assets.generation.code.generator.concrete;

import com.adversespaceloneliness.game.assets.generation.code.generator.CodeGenerator;

/**
 * Generates the code file that contains the IDs for all assets.
 */
public class IDGenerator extends CodeGenerator {

    private static final String ENUM_NAME = "AssetID";

    @Override
    public boolean isDirPathGeneratable(String generatedDirPath) {
        return true;
    }

    @Override
    public void generate(String assetPath) {
        // TODO
        System.out.println("IDGenerator");
        System.out.println(assetPath);
    }

    @Override
    public String getGeneratedTypeName() {
        return ENUM_NAME;
    }
}
