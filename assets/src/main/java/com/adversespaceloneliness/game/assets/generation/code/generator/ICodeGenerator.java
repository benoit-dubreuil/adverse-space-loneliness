package com.adversespaceloneliness.game.assets.generation.code.generator;

import com.adversespaceloneliness.game.assets.generation.IGenerator;

/**
 * Describes a code generator.
 * <p>
 * In addition to being a simple generator, a code generator supply a method for ending the generation, which will then save the generated code file.
 */
public interface ICodeGenerator extends IGenerator {

    String OUTPUT_PACKAGE = "com.adversespaceloneliness.game.assets.generated";
    String OUTPUT_PATH = "src/main/java";

    /**
     * Ends the generation process for this generator.
     * <p>
     * This method is supposed to be called once all the intended assets were passed through this generator and that the resulting code must be generated.
     */
    void endGeneration();
}
