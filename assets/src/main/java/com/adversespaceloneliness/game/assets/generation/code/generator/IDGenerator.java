package com.adversespaceloneliness.game.assets.generation.code.generator;

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
    }

    @Override
    public String getGeneratedTypeName() {
        return ENUM_NAME;
    }
}
