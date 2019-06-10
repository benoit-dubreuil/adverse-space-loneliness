package com.adversespaceloneliness.game.assets.api;

import java.util.Arrays;

/**
 * All asset types in the LibGDX game engine and their supported extensions.
 * <p>
 * All asset types in this enum store their extensions twice : firstly as plain extensions and secondly as extension prefixed with a dot.
 */
public enum AssetType {
    IMAGE(new String[] { "jpg", "jpeg", "png", "bmp" }),
    AUDIO(new String[] { "wav", "mp3", "ogg" }),
    FONT(new String[] { "fnt", "ttf" }),
    TEXTURE_ATLAS(new String[] { "atlas" }),
    GUI_SKIN(new String[] { "json" }),
    LOCALIZATION(new String[] { "properties" }),
    PARTICLE_EFFECT(new String[] { "p" });

    private final String[] m_filenameExtensions;
    private final String[] m_filenameExtensionsWithDot;

    AssetType(String[] filenameExtensions) {
        m_filenameExtensions = filenameExtensions;
        m_filenameExtensionsWithDot = (String[]) Arrays.stream(filenameExtensions).map(extension -> '.' + extension).toArray();
    }

    public String[] getFilenameExtensions() {
        return m_filenameExtensions;
    }

    public String[] getFilenameExtensionsWithDot() {
        return m_filenameExtensionsWithDot;
    }
}