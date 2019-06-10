package com.adversespaceloneliness.game.assets.generation.asset.generator.data;

import com.adversespaceloneliness.game.assets.generation.IGenerationData;

/**
 * The top directories in the assets/raw/ directory that must be texture packed. This overrides the default behaviour, which is copying the assets/raw/ directories into the
 * assets/generated/ directory.
 */
public enum RawDirToTexturePack {

    SPRITE();

    private String m_dir;

    RawDirToTexturePack() {
        initDir(name().toLowerCase());
    }

    RawDirToTexturePack(String dir) {
        initDir(dir);
    }

    public String getDir() {
        return m_dir;
    }

    private void initDir(String dir) {
        m_dir = IGenerationData.RAW_DIRECTORY + '/' + dir;
    }
}
