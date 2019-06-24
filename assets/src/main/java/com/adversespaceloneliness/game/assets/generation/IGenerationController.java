package com.adversespaceloneliness.game.assets.generation;

/**
 * Describes the behaviour of a generation controller.
 */
public interface IGenerationController {

    int DIRECTORY_WALKING_DEPTH = 1;

    /**
     * Generates this controller's specific data from the assets files and folders.
     */
    void generate();
}
