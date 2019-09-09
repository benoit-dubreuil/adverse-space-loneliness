package com.adversespaceloneliness.game.assets.generation;

/**
 * Interface for generators related to asset generation. For example, a generator could generate assets or code files.
 */
public interface IGenerator {

    /**
     * Generates this asset generator's specific data from the supplied asset path. For example, it could generate assets or code files.
     *
     * @param assetPath The asset path, whether it be a directory or a file.
     */
    void generate(String assetPath);

    /**
     * Checks if the supplied directory path can be generated using this generation.
     *
     * @param dirPath The directory path that needs to be checked for if it can be generated using this generation.
     *
     * @return True if the supplied directory path can be generated using this generation, false otherwise.
     */
    boolean isDirPathGeneratable(String dirPath);
}
