package com.adversespaceloneliness.game.assets;

/**
 * The top directories in the assets/raw/ directory that must be texture packed. This overrides the default behaviour, which is copying the assets/raw/ directories into the
 * assets/generated/ directory.
 */
public enum TopRawDirectoryToTexturePack {

    SPRITE("sprite");

    private final String m_directory;

    TopRawDirectoryToTexturePack(String directory) {
        m_directory = directory;
    }

    public String getDirectory() {
        return m_directory;
    }
}
