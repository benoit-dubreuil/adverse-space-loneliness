package com.adversespaceloneliness.game.assets.generation.asset;

import com.adversespaceloneliness.game.assets.generation.IGenerationController;
import com.adversespaceloneliness.game.assets.generation.IGenerationData;
import com.adversespaceloneliness.game.assets.generation.asset.generator.CopyGenerator;
import com.adversespaceloneliness.game.assets.generation.asset.generator.IAssetGenerator;
import com.adversespaceloneliness.game.assets.generation.asset.generator.SpritePackerGenerator;
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
public class AssetGenerationController implements IGenerationController {

    /**
     * Generates all the raw assets and outputs the results into the generated directory.
     */
    @Override
    public void generate() {
        IAssetGenerator defaultAssetGenerator = new CopyGenerator();
        IAssetGenerator[] assetGenerators = new IAssetGenerator[] { new SpritePackerGenerator() };

        emptyGeneratedDirectory();

        try {
            List<Path> rawDirs = Files.walk(Paths.get(IGenerationData.RAW_DIRECTORY), 1).filter(Files::isDirectory).collect(Collectors.toList());

            // Remove the raw directory itself
            rawDirs.remove(0);

            rawDirectoryLoop:
            for (Path rawDirPath : rawDirs) {

                String rawDirectory = rawDirPath.toString().replace('\\', '/');

                for (IAssetGenerator assetGenerator : assetGenerators) {
                    if (assetGenerator.isRawDirPathGeneratable(rawDirectory)) {

                        assetGenerator.generate(rawDirectory);
                        continue rawDirectoryLoop;
                    }
                }

                if (defaultAssetGenerator.isRawDirPathGeneratable(rawDirectory)) {
                    defaultAssetGenerator.generate(rawDirectory);
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
            FileUtils.deleteDirectory(new File(IGenerationData.GENERATED_DIRECTORY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
