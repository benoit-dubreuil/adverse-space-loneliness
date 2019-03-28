package com.adversespaceloneliness.game.assets.generation;

import com.adversespaceloneliness.game.assets.generation.generator.CopyGenerator;
import com.adversespaceloneliness.game.assets.generation.generator.IAssetGenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AssetGenerationController {

    public static final String ASSET_DIRECTORY = "assets";
    public static final String RAW_DIRECTORY = ASSET_DIRECTORY + "/raw";
    public static final String GENERATED_DIRECTORY = ASSET_DIRECTORY + "/generated";

    public void generateAssets() {
        IAssetGenerator defaultAssetGenerator = new CopyGenerator();
        IAssetGenerator[] assetGenerators = new IAssetGenerator[] {};

        try {
            List<Path> rawDirectories = Files.walk(Paths.get(RAW_DIRECTORY), 1).filter(Files::isDirectory).collect(Collectors.toList());

            // Remove the raw directory itself
            rawDirectories.remove(0);

            rawDirectoryLoop:
            for (Path rawDirectoryPath : rawDirectories) {

                String rawDirectory = rawDirectoryPath.toString().replace('\\', '/');

                for (IAssetGenerator assetGenerator : assetGenerators) {
                    if (assetGenerator.isTopRawDirectoryGeneratable(rawDirectory)) {

                        assetGenerator.generateTopRawDirectory(rawDirectory);
                        continue rawDirectoryLoop;
                    }
                }

                if (defaultAssetGenerator.isTopRawDirectoryGeneratable(rawDirectory)) {
                    defaultAssetGenerator.generateTopRawDirectory(rawDirectory);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
