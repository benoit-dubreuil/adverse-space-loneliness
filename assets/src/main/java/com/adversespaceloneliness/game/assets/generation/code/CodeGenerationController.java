package com.adversespaceloneliness.game.assets.generation.code;

import com.adversespaceloneliness.game.assets.generation.IGenerationController;
import com.adversespaceloneliness.game.assets.generation.IGenerationData;
import com.adversespaceloneliness.game.assets.generation.code.generator.concrete.AtlasIDGenerator;
import com.adversespaceloneliness.game.assets.generation.code.generator.ICodeGenerator;
import com.adversespaceloneliness.game.assets.generation.code.generator.concrete.IDGenerator;
import com.adversespaceloneliness.game.assets.util.PathUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entry point for code generation.
 */
public class CodeGenerationController implements IGenerationController {

    /**
     * Generates all code files from the assets. Principally, it generates the assets IDs (enums).
     */
    @Override
    public void generate() {
        ICodeGenerator[] generators = new ICodeGenerator[] { new IDGenerator()/*, new AtlasIDGenerator()*/ }; // TODO

        try {
            List<Path> generatedDirs = Files.walk(Paths.get(IGenerationData.GENERATED_DIRECTORY), DIRECTORY_WALKING_DEPTH).filter(Files::isDirectory).collect(Collectors.toList());
            // Remove the raw directory itself
            generatedDirs.remove(0);

            for (Path generatedDirPath : generatedDirs) {

                String normalizedGeneratedDirPath = PathUtils.normalizePathSeparators(generatedDirPath);
                generateCode(generators, normalizedGeneratedDirPath);
            }

            endGeneration(generators);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes the supplied directory path for each code generator, if they can generate code from it.
     *
     * @param generators                 All code generators.
     * @param normalizedGeneratedDirPath The normalized generated directory path.
     */
    private void generateCode(ICodeGenerator[] generators, String normalizedGeneratedDirPath) {
        for (ICodeGenerator generator : generators) {

            if (generator.isDirPathGeneratable(normalizedGeneratedDirPath)) {
                generator.generate(normalizedGeneratedDirPath);
            }
        }
    }

    /**
     * Ends the code generation for all generators.
     * <p>
     * This allows the code generators to save the finished code files.
     *
     * @param generators All code generators.
     */
    private void endGeneration(ICodeGenerator[] generators) {
        for (ICodeGenerator generator : generators) {
            generator.endGeneration();
        }
    }
}
