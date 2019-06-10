package com.adversespaceloneliness.game.assets.generation;

public interface IGenerationController {

    int DIRECTORY_WALKING_DEPTH = 1;

    /**
     * Generates this controller's specific data from the assets files and folders.
     */
    void generate();
}
