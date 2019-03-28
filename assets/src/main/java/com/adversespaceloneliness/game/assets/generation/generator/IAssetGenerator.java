package com.adversespaceloneliness.game.assets.generation.generator;

import java.io.File;

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
    boolean isTopRawDirectoryGeneratable(File topRawDirectory);

    /**
     * Generates the top raw directory's assets. This means that somehow the assets in the supplied directory will be transformed and put inside the generated assets.
     *
     * @param topRawDirectory The top raw directory to be generated using this generation.
     */
    void generateTopRawDirectory(File topRawDirectory);
}
