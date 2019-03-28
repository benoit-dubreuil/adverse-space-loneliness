package com.adversespaceloneliness.game.assets;

import java.io.File;

/**
 * Interface for asset generators on the directory level.
 */
public interface IAssetGenerator {

    /**
     * Checks if the supplied top raw directory can be generated using this generator.
     *
     * @param topRawDirectory The top raw directory that needs to be checked for if it can be generated using this generator.
     *
     * @return True if the supplied top raw directory can be generated using this generator, false otherwise.
     */
    boolean isTopRawDirectoryGeneratable(File topRawDirectory);

    /**
     * Generates the top raw directory's assets. This means that somehow the assets in the supplied directory will be transformed and put inside the generated assets.
     *
     * @param topRawDirectory The top raw directory to be generated using this generator.
     */
    void generateTopRawDirectory(File topRawDirectory);
}
