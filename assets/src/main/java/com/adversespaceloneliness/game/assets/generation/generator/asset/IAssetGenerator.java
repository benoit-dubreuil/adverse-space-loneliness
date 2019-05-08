package com.adversespaceloneliness.game.assets.generation.generator.asset;

import com.adversespaceloneliness.game.assets.generation.AssetGenerationController;

/**
 * Interface for asset generator on the directory level.
 */
public interface IAssetGenerator {

    /**
     * Checks if the supplied top raw directory can be generated using this generation.
     *
     * @param topRawDirectory The top raw directory that needs to be checked for if it can be generated using this generation.
     *
     * @return True if the supplied top raw directory can be generated using this generation, false otherwise.
     */
    boolean isTopRawDirectoryGeneratable(String topRawDirectory);

    /**
     * Generates the top raw directory's assets. This means that somehow the assets in the supplied directory will be transformed and put inside the generated assets folder.
     *
     * @param topRawDirectory The top raw directory to be generated using this generation.
     */
    void generateTopRawDirectory(String topRawDirectory);

    /**
     * Computes the top generated directory subdirectory for the supplied top raw directory. In other words, computes the path that points to the new subdirectory of the generated
     * assets path.
     *
     * @param topRawDirectory The top raw directory of the asset to generate.
     *
     * @return The top generated directory.
     */
    default String computeTopGeneratedDirectory(String topRawDirectory) {
        return topRawDirectory.replaceFirst('^' + AssetGenerationController.RAW_DIRECTORY, AssetGenerationController.GENERATED_DIRECTORY);
    }
}
