package com.adversespaceloneliness.game.assets.generation.code.generator.data;

import com.adversespaceloneliness.game.assets.generation.IGenerationData;

/**
 * The top directories in the assets/generated/ directory.
 * <p>
 * All those subdirectories were generated during the asset generation process.
 */
public enum GeneratedDirs {

    SPRITE(),
    TEXTURE(),
    FONT();

    private String m_dir;

    GeneratedDirs() {
        initDir(name().toLowerCase());
    }

    GeneratedDirs(String dir) {
        initDir(dir);
    }

    private void initDir(String dir) {
        m_dir = IGenerationData.GENERATED_DIRECTORY + '/' + dir;
    }

    public String getDir() {
        return m_dir;
    }
}
