package com.adversespaceloneliness.game.assets.generation.generator.asset;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * The default generator, which simply copies the raw assets in their respective generated subdirectories.
 */
public class CopyGenerator implements IAssetGenerator {

    @Override
    public boolean isTopRawDirectoryGeneratable(String topRawDirectory) {
        return true;
    }

    @Override
    public void generateTopRawDirectory(String topRawDirectory) {
        try {
            FileUtils.copyDirectory(new File(topRawDirectory), new File(computeTopGeneratedDirectory(topRawDirectory)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
