package com.adversespaceloneliness.game.assets.generation.code.generator.concrete;

import com.adversespaceloneliness.game.assets.generation.code.generator.CodeGenerator;
import com.adversespaceloneliness.game.assets.generation.code.generator.data.GeneratedDirs;

/**
 * Generates the code file that contains the IDs for the atlas asset.
 */
public class AtlasIDGenerator extends CodeGenerator {

    private static final String ENUM_NAME = "AtlasID";

    @Override
    public boolean isDirPathGeneratable(String generatedDirPath) {
        return generatedDirPath.startsWith(GeneratedDirs.SPRITE.getDir());
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
