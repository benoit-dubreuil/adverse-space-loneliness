package com.adversespaceloneliness.game.assets.generation.generator;

import com.adversespaceloneliness.game.assets.generation.AssetGenerationController;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class CopyGenerator implements IAssetGenerator {

    @Override
    public boolean isTopRawDirectoryGeneratable(String topRawDirectory) {
        return true;
    }

    @Override
    public void generateTopRawDirectory(String topRawDirectory) {
        String topGeneratedDirectory = topRawDirectory.replaceFirst('^' + AssetGenerationController.RAW_DIRECTORY, AssetGenerationController.GENERATED_DIRECTORY);

        try {
            FileUtils.copyDirectory(new File(topRawDirectory), new File(topGeneratedDirectory));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
