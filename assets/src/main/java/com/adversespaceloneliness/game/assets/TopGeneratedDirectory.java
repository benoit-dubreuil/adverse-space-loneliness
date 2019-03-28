package com.adversespaceloneliness.game.assets;

import java.io.File;

/**
 * All the top directories in the assets/generated/ directory.
 */
public enum TopGeneratedDirectory {

    SPRITESHEET("spritesheet");

    private final File m_directory;

    TopGeneratedDirectory(String directory) {
        m_directory = new File(AssetGenerationController.GENERATED_DIRECTORY + directory);
    }

    public File getDirectory() {
        return m_directory;
    }
}
