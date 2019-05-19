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
}
