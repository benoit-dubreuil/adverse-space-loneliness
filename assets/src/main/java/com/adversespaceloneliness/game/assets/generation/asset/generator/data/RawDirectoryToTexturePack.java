package com.adversespaceloneliness.game.assets.generation.asset.generator.data;

import com.adversespaceloneliness.game.assets.generation.IGenerationData;

/**
 * The top directories in the assets/raw/ directory that must be texture packed. This overrides the default behaviour, which is copying the assets/raw/ directories into the
 * assets/generated/ directory.
 */
public enum RawDirectoryToTexturePack {

    SPRITE("sprite");

    public static final GeneratedDirectory TOP_GENERATED_DIRECTORY_OUTPUT = GeneratedDirectory.SPRITESHEET;

    private final String m_directory;

    RawDirectoryToTexturePack(String directory) {
        m_directory = IGenerationData.RAW_DIRECTORY + '/' + directory;
    }

    public String getDirectory() {
        return m_directory;
    }
}
