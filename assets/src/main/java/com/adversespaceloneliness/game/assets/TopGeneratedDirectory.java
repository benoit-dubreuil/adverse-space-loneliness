package com.adversespaceloneliness.game.assets;

/**
 * All the top directories in the assets/generated/ directory.
 */
public enum TopGeneratedDirectory {

    SPRITESHEET("spritesheet");

    private final String m_directory;

    TopGeneratedDirectory(String directory) {
        m_directory = directory;
    }

    public String getDirectory() {
        return m_directory;
    }
}
