package com.adversespaceloneliness.game.assets.imagesplitter;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSplitter {

    private static final String IMG_COUNT_SUFFIX_REGEX = "(_strip\\d)$";
    private static final String IMG_COUNT_REGEX = "\\d$";

    public static void main(String[] paths) {
        String input = "C:\\Users\\Admin\\Documents\\Workspaces\\ChevreuilGames\\AdverseSpaceLoneliness\\assets\\assets\\raw\\sprite\\retro-space-pixel-shooter-pack\\enemy"
            + "\\asteroid_strip2.png";
        String output = "assets/assets/test/";

        ImageSplitter imgSplitter = new ImageSplitter();
        //    imgSplitter.splitImage(input, output);
        imgSplitter.splitImage(input);
    }

    public ImageSplitter() {
    }

    /**
     * Splits multiple image strips.
     *
     * @param inputPaths The input paths to each image strip.
     *
     * @see #splitImage(String)
     */
    public void splitImages(String[] inputPaths) {
        for (String inputPath : inputPaths) {
            splitImage(inputPath);
        }
    }

    /**
     * Splits an image strip into multiple images, one for each frame, in the same directory. The new images have _$ appended to their names, where $ is the frame index. Also, the
     * _strip$ suffix from the input path is not present in the output images, as it represents the number of frames in the image strip. The input image is only split if it's $
     * sub-suffix from the _strip$ suffix equals to 1, as it would mean it's not an actual image strip but a whole image.
     *
     * @param inputPath The input path to the image strip.
     */
    public void splitImage(String inputPath) {
        splitImage(inputPath, FilenameUtils.getPath(inputPath));
    }

    /**
     * Splits an image strip into multiple images, one for each frame, in the output directory. The new images have _$ appended to their names, where $ is the frame index. Also,
     * the _strip$ suffix from the input path is not present in the output images, as it represents the number of frames in the image strip. The input image is only split if it's $
     * sub-suffix from the _strip$ suffix equals to 1, as it would mean it's not an actual image strip but a whole image.
     *
     * @param inputPath  The input path to the image strip.
     * @param outputPath The output path to the directory where the image frames should be saved.
     */
    public void splitImage(String inputPath, String outputPath) {

        String imgBaseName = FilenameUtils.getBaseName(inputPath);
        int imgCount = extractImageStripCount(imgBaseName);
        String imgOutputName = extractImageBaseName(imgBaseName);
        String extension = FilenameUtils.getExtension(inputPath);

        BufferedImage img;

        try {
            img = ImageIO.read(new File(inputPath));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        createDirectoryPath(outputPath);
        extractImageFrames(outputPath, imgCount, imgOutputName, extension, img);
    }

    /**
     * Extracts the image strip's frames and save them in the output directory.
     *
     * @param outputDirectory The output directory where to extract the image frames.
     * @param imgCount        The number of frames in the image strip.
     * @param imgOutputName   The output name to give to each frame. A _$ suffix will be appended to the name whilst saving each frame.
     * @param extension       The extension of the image strip and henceforth the image frames' extension.
     * @param img             The image strip data.
     */
    private void extractImageFrames(String outputDirectory, int imgCount, String imgOutputName, String extension, BufferedImage img) {
        int imgFrameWidth = img.getWidth() / imgCount;
        for (int index = 0; index < imgCount; ++index) {
            try {
                Path imgFrameOutputPath = Path.of(outputDirectory, imgOutputName + "_" + index + "." + extension);
                ImageIO.write(img.getSubimage(index * imgFrameWidth, 0, imgFrameWidth, img.getHeight()), extension, imgFrameOutputPath.toFile());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /**
     * Assures that the supplied directory path exists. If not, then it creates it.
     *
     * @param directoryPath A path that only contains directories, i.e. it does not point to a file.
     */
    private void createDirectoryPath(String directoryPath) {
        File outputDir = new File(directoryPath);

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
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