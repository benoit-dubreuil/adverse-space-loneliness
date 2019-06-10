package com.adversespaceloneliness.game.assets.generation.code.generator;

import com.adversespaceloneliness.game.assets.generation.code.generator.data.GeneratedDirs;

public class IDGenerator implements ICodeGenerator {

    @Override
    public boolean isDirPathGeneratable(String generatedDirPath) {
        return generatedDirPath.startsWith(GeneratedDirs.SPRITE.getDir());
    }

    @Override
    public void generate(String assetPath) {
        // TODO
    }

    @Override
    public void endGeneration() {
        // TODO
    }
}
