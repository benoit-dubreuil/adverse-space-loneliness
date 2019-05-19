package com.adversespaceloneliness.game.assets.generation.asset.generator.data;

import com.adversespaceloneliness.game.assets.generation.IGenerationData;

/**
 * All the top directories in the assets/generated/ directory.
 */
public enum GeneratedDirectory {

    SPRITESHEET("spritesheet");

    private final String m_directory;

    GeneratedDirectory(String directory) {
        m_directory = IGenerationData.GENERATED_DIRECTORY + '/' + directory;
    }

    public String getDirectory() {
        return m_directory;
    }
}
