package com.adversespaceloneliness.game.assets.generation.asset.generator;

import com.adversespaceloneliness.game.assets.generation.IGenerationData;
import com.adversespaceloneliness.game.assets.generation.IGenerator;

/**
 * Interface for asset generator on the directory level.
 */
public interface IAssetGenerator extends IGenerator {

    /**
     * Checks if the supplied raw's directory path can be generated using this generation.
     *
     * @param rawDirPath The raw's directory path that needs to be checked for if it can be generated using this generation.
     *
     * @return True if the supplied raw's directory path can be generated using this generation, false otherwise.
     */
    boolean isRawDirPathGeneratable(String rawDirPath);

    /**
     * Computes the generated's directory path for the supplied raw's directory path. In other words, it replaces the first occurrence of the word "raw" by the word "generated".
     *
     * @param rawDirPath The raw's directory path that is used to compute the generated's directory path.
     *
     * @return The generated's directory path.
     */
    default String computeGeneratedDirPath(String rawDirPath) {
        return rawDirPath.replaceFirst('^' + IGenerationData.RAW_DIRECTORY, IGenerationData.GENERATED_DIRECTORY);
    }
}
