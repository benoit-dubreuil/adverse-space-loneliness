package com.adversespaceloneliness.game.assets.generation;

/**
 * All the top directories in the assets/generated/ directory.
 */
public enum TopGeneratedDirectory {

    SPRITESHEET("spritesheet");

    private final String m_directory;

    TopGeneratedDirectory(String directory) {
        m_directory = AssetGenerationController.GENERATED_DIRECTORY + '/' + directory;
    }

    public String getDirectory() {
        return m_directory;
    }}
