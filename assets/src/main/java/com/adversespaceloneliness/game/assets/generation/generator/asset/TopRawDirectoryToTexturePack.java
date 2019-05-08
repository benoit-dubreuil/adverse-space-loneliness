package com.adversespaceloneliness.game.assets.generation.generator.asset;

import com.adversespaceloneliness.game.assets.generation.AssetGenerationController;

/**
 * The top directories in the assets/raw/ directory that must be texture packed. This overrides the default behaviour, which is copying the assets/raw/ directories into the
 * assets/generated/ directory.
 */
public enum TopRawDirectoryToTexturePack {

    SPRITE("sprite");

    public static final TopGeneratedDirectory TOP_GENERATED_DIRECTORY_OUTPUT = TopGeneratedDirectory.SPRITESHEET;

    private final String m_directory;

    TopRawDirectoryToTexturePack(String directory) {
        m_directory = AssetGenerationController.RAW_DIRECTORY + '/' + directory;
    }

    public String getDirectory() {
        return m_directory;
    }}
