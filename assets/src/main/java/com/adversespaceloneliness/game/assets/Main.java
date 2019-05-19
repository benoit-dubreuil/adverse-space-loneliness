package com.adversespaceloneliness.game.assets;

import com.adversespaceloneliness.game.assets.generation.asset.AssetGenerationController;

public class Main {

    public static void main(String[] args) {
        AssetGenerationController controller = new AssetGenerationController();
        controller.generate();
    }
}