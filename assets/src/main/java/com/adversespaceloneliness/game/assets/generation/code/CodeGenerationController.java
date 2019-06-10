package com.adversespaceloneliness.game.assets.generation.code;

import com.adversespaceloneliness.game.assets.generation.IGenerationController;
import com.adversespaceloneliness.game.assets.generation.IGenerationData;
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
        try {
            List<Path> generatedDirs = Files.walk(Paths.get(IGenerationData.GENERATED_DIRECTORY), DIRECTORY_WALKING_DEPTH).filter(Files::isDirectory).collect(Collectors.toList());
            // Remove the raw directory itself
            generatedDirs.remove(0);

            for (Path generatedDirPath : generatedDirs) {

                String normalizedGeneratedDirPath = PathUtils.normalizePathSeparators(generatedDirPath);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
