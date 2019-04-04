package com.adversespaceloneliness.game.assets.imagesplitter;

import org.apache.commons.io.FilenameUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSplitter {

    private static final String IMG_COUNT_SUFFIX_REGEX = "(_strip\\d)$";
    private static final String IMG_COUNT_REGEX = "\\d$";

    public static void main(String[] paths) {
        String input = "C:\\Users\\Admin\\Documents\\Workspaces\\ChevreuilGames\\AdverseSpaceLoneliness\\assets\\assets\\raw\\sprite\\retro-space-pixel-shooter-pack\\enemy"
            + "\\asteroid_strip2.png";

        ImageSplitter imgSplitter = new ImageSplitter();
        imgSplitter.splitImage(input, null);
    }

    public ImageSplitter() {
    }

    public void splitImages() {
    }

    /**
     * Splits an image strip into multiple images, one for each frame, in the output directory. The new images have _$ appended to their names, where $ is the frame index. Also,
     * the _strip$ suffix from the input path is not present in the output images, as it represents the number of frames in the image strip. The input image is only split if it's $
     * sub-suffix from the _strip$ suffix equals to 1, as it would mean it's not an actual image strip but a whole image.
     *
     * @param inputPath  The input path to the image strip.
     * @param outputPath The output path to the directory where the image frames should be saved.
     */
    private void splitImage(String inputPath, String outputPath) {

        String imgBaseName = FilenameUtils.getBaseName(inputPath);
        int imgCount = extractImageStripCount(imgBaseName);
        String imgOutputName = extractImageBaseName(imgBaseName);

        System.out.println(imgBaseName);
        System.out.println(imgCount);
        System.out.println(imgOutputName);

/*
        // Output
        File outputFile = new File(outputPath);
        File outputParentDirectory = outputFile.getParentFile();

        if (!outputParentDirectory.exists()) {
            outputParentDirectory.mkdirs();
        }
        */
    }

    /**
     * Extracts the image base name without the _strip$ suffix. A base name is a file's name without a path nor an extension.
     *
     * @param imgBaseName The image's base name. A base name is a file's name without a path nor an extension.
     *
     * @return The image's base name without its _strip$ suffix.
     */
    private String extractImageBaseName(String imgBaseName) {
        return imgBaseName.replaceFirst(IMG_COUNT_SUFFIX_REGEX, "");
    }

    /**
     * Extracts the image count from an image strip's name.
     *
     * @param imgBaseName The image's base name. A base name is a file's name without a path nor an extension.
     *
     * @return The image count from an image strip. Returns 1 if the image is not a strip. Returns a number bigger than 1 if the image is a strip.
     */
    private int extractImageStripCount(String imgBaseName) {
        int imgCount = 1;

        String imgCountSuffix = extractImageStripSuffix(imgBaseName);

        Pattern imgCountPattern = Pattern.compile(IMG_COUNT_REGEX);
        Matcher imgCountMatcher = imgCountPattern.matcher(imgCountSuffix);

        if (imgCountMatcher.find()) {
            String inputImgCount = imgCountMatcher.group();
            imgCount = Integer.parseInt(inputImgCount);
        }

        return imgCount;
    }

    /**
     * Extracts the image strip's suffix from it's path.
     *
     * @param imgBaseName The image's base name. A base name is a file's name without a path nor an extension.
     *
     * @return The image strip's suffix it's path.
     */
    private String extractImageStripSuffix(String imgBaseName) {
        String inputImgCountSuffix = "";

        Pattern imgCountPattern = Pattern.compile(IMG_COUNT_SUFFIX_REGEX);
        Matcher imgCountMatcher = imgCountPattern.matcher(imgBaseName);

        if (imgCountMatcher.find()) {
            inputImgCountSuffix = imgCountMatcher.group();
        }

        return inputImgCountSuffix;
    }
}