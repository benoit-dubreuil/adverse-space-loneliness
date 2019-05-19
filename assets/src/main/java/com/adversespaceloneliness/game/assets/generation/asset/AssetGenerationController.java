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

    @Override
    public void generate() {
        IAssetGenerator defaultAssetGenerator = new CopyGenerator();
        IAssetGenerator[] assetGenerators = new IAssetGenerator[] { new SpritePackerGenerator() };

        emptyGeneratedDirectory();

        try {
            List<Path> rawDirs = Files.walk(Paths.get(IGenerationData.RAW_DIRECTORY), 1).filter(Files::isDirectory).collect(Collectors.toList());
            // Remove the raw directory itself
            rawDirs.remove(0);

            for (Path rawDirPath : rawDirs) {

                String normalizedRawDirPath = rawDirPath.toString().replace('\\', '/');
                generateAsset(defaultAssetGenerator, assetGenerators, normalizedRawDirPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates the asset at the supplied raw's normalized directory path according to the first matching asset generator from the given asset generators or from the default one
     * if none matched.
     *
     * @param defaultAssetGenerator The default asset generator to use when no asset generators match.
     * @param assetGenerators       All asset generators, ordered from more priority to less priority.
     * @param rawNormalizedDirPath  The raw's normalized directory path.
     */
    private void generateAsset(IAssetGenerator defaultAssetGenerator, IAssetGenerator[] assetGenerators, String rawNormalizedDirPath) {
        if (!generateAssetFromGenerators(assetGenerators, rawNormalizedDirPath)) {

            if (defaultAssetGenerator.isRawDirPathGeneratable(rawNormalizedDirPath)) {
                defaultAssetGenerator.generate(rawNormalizedDirPath);
            }
        }
    }

    /**
     * Generates the asset at the supplied raw's normalized directory path according to the first matching asset generator from the given asset generators.
     *
     * @param assetGenerators      All asset generators, ordered from more priority to less priority.
     * @param rawNormalizedDirPath The raw's normalized directory path.
     *
     * @return Returns true if an asset generator matched and generated this asset and false otherwise.
     */
    private boolean generateAssetFromGenerators(IAssetGenerator[] assetGenerators, String rawNormalizedDirPath) {
        for (IAssetGenerator assetGenerator : assetGenerators) {
            if (assetGenerator.isRawDirPathGeneratable(rawNormalizedDirPath)) {

                assetGenerator.generate(rawNormalizedDirPath);
                return true;
            }
        }

        return false;
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
