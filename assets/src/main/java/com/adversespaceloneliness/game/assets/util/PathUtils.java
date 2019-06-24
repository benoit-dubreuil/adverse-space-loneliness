package com.adversespaceloneliness.game.assets.util;

import java.nio.file.Path;

/**
 * Provides utilities for path representations.
 */
public final class PathUtils {

    private static final char BACKSLASH = '\\';
    private static final char SLASH = '/';

    private PathUtils() {
    }

    /**
     * Computes the normalized string path of a {@link Path}.
     * <p>
     * Normalization happens on the separator level. Windows style separators are converted into Unix style separators.
     *
     * @param path The path to normalize its separators.
     *
     * @return The normalized string path.
     */
    public static String normalizePathSeparators(Path path) {
        return path.toString().replace(BACKSLASH, SLASH);
    }
}
