package com.adversespaceloneliness.game.assets.imagesplitter;

import org.apache.commons.io.FilenameUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSplitter {

    private static final String IMG_COUNT_SUFFIX_REGEX = "(_strip\\d)$";
    private static final String IMG_COUNT_REGEX = "\\d$";

    public static void main(String[] paths) {
        String input = "D:\\Workspaces\\Java\\LibGDX\\AdverseSpaceLoneliness\\assets\\assets\\raw\\sprite\\retro-space-pixel-shooter-pack\\enemy\\asteroid_strip2.png";

        ImageSplitter imgSplitter = new ImageSplitter();
        imgSplitter.splitImage(input, null);
    }

    public ImageSplitter() {
    }

    public void splitImages() {
    }

    private void splitImage(String inputPath, String outputPath) {

        int imgCount = computeImageStripCount(inputPath);
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
     * Computes the image count from an image strip's name.
     *
     * @param imgPath The image's path, whether it be absolute or relative.
     *
     * @return The image count from an image strip. Returns 1 if the image is not a strip. Returns a number bigger than 1 if the image is a strip.
     */
    private int computeImageStripCount(String imgPath) {
        int imgCount = 1;

        String imgCountSuffix = computeImageStripSuffix(imgPath);

        Pattern imgCountPattern = Pattern.compile(IMG_COUNT_REGEX);
        Matcher imgCountMatcher = imgCountPattern.matcher(imgCountSuffix);

        if (imgCountMatcher.find()) {
            String inputImgCount = imgCountMatcher.group();
            imgCount = Integer.parseInt(inputImgCount);
        }

        return imgCount;
    }

    /**
     * Computes the image strip's suffix from it's path.
     *
     * @param imgPath The image's path, whether it be absolute or relative.
     *
     * @return The image strip's suffix it's path.
     */
    private String computeImageStripSuffix(String imgPath) {
        String inputImgCountSuffix = "";
        String inputBaseName = FilenameUtils.getBaseName(imgPath);

        Pattern imgCountPattern = Pattern.compile(IMG_COUNT_SUFFIX_REGEX);
        Matcher imgCountMatcher = imgCountPattern.matcher(inputBaseName);

        if (imgCountMatcher.find()) {
            inputImgCountSuffix = imgCountMatcher.group();
        }

        return inputImgCountSuffix;
    }
}