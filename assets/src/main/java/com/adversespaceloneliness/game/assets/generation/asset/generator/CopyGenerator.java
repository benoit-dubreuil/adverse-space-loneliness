package com.adversespaceloneliness.game.assets.generation.asset.generator;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * The default generator, which simply copies the raw assets in their respective generated subdirectories.
 */
public class CopyGenerator implements IAssetGenerator {

    @Override
    public boolean isRawDirPathGeneratable(String rawDirPath) {
        return true;
    }

    @Override
    public void generate(String topRawDirectory) {
        try {
            FileUtils.copyDirectory(new File(topRawDirectory), new File(computeGeneratedDirPath(topRawDirectory)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
