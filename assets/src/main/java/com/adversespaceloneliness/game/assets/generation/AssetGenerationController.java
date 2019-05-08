package com.adversespaceloneliness.game.assets.generation;

import com.adversespaceloneliness.game.assets.generation.generator.asset.CopyGenerator;
import com.adversespaceloneliness.game.assets.generation.generator.asset.IAssetGenerator;
import com.adversespaceloneliness.game.assets.generation.generator.asset.SpritePackerGenerator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entry point for the asset generation.
 */
public class AssetGenerationController {

    public static final String ASSET_DIRECTORY = "assets";
    public static final String RAW_DIRECTORY = ASSET_DIRECTORY + "/raw";
    public static final String GENERATED_DIRECTORY = ASSET_DIRECTORY + "/generated";

    /**
     * Generates all the raw assets and output the results into the generated directory.
     */
    public void generateAssets() {
        IAssetGenerator defaultAssetGenerator = new CopyGenerator();
        IAssetGenerator[] assetGenerators = new IAssetGenerator[] { new SpritePackerGenerator() };

        emptyGeneratedDirectory();

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

    /**
     * Empties the generated directory.
     */
    private void emptyGeneratedDirectory() {
        try {
            FileUtils.deleteDirectory(new File(GENERATED_DIRECTORY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
