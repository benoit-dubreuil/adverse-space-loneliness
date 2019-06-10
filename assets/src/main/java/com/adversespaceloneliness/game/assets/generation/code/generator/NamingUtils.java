package com.adversespaceloneliness.game.assets.generation.code.generator;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utilities for naming, more specifically type, file and enum value names.
 */
public final class NamingUtils {

    private static final String FILE_NAME_DELIMITER_REGEX = "(\\s|-|_|\\.)";
    private static final CharSequence ENUM_VALUE_DELIMITER = "_";

    /**
     * Converts a file name string into a type name.
     * <p>
     * A file name can be delimited by the following characters : ' ', '-' and '_'. The Conversion is done removing those delimiters and capitalizing each sub-word, which results
     * in a type name.
     * <p>
     * The file name's extension is ignored during this process.
     *
     * @param fileName The file name to convert.
     *
     * @return Returns the resulting type name.
     */
    public static String fileNametoTypeName(String fileName) {
        fileName = FilenameUtils.removeExtension(fileName);
        String[] fileNameChunks = fileName.split(FILE_NAME_DELIMITER_REGEX);
        return Arrays.stream(fileNameChunks).map(StringUtils::capitalize).collect(Collectors.joining());
    }

    public static String fileNametoEnumValueName(String fileName) {
        return fileNametoEnumValueName(fileName, false);
    }

    /**
     * Converts a file name string into an enum value name.
     * <p>
     * A file name can be delimited by the following characters : ' ', '-', '_' and lastly '.', if the extension is kept. The Conversion is done removing those delimiters and upper
     * casing each sub-word and the adding the '_' delimiter, which results in an enum value name.
     *
     * @param fileName      The file name to convert.
     * @param keepExtension True if the extension should be used in the converted enum value name, false otherwise.
     *
     * @return Returns the resulting enum value name.
     */
    public static String fileNametoEnumValueName(String fileName, boolean keepExtension) {
        if (!keepExtension) {
            fileName = FilenameUtils.removeExtension(fileName);
        }

        String[] fileNameChunks = fileName.split(FILE_NAME_DELIMITER_REGEX);
        return Arrays.stream(fileNameChunks).map(String::toUpperCase).collect(Collectors.joining(ENUM_VALUE_DELIMITER));
    }

    private NamingUtils() {
    }
}
