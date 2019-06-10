package com.adversespaceloneliness.game.assets;

import com.adversespaceloneliness.game.assets.generation.IGenerationController;
import com.adversespaceloneliness.game.assets.generation.asset.AssetGenerationController;
import com.adversespaceloneliness.game.assets.generation.code.CodeGenerationController;

public class Main {

    public static void main(String[] args) {

        // Order is important
        IGenerationController[] controllers = new IGenerationController[] { new AssetGenerationController(), new CodeGenerationController() };

        for (IGenerationController controller : controllers) {
            controller.generate();
        }
    }
}