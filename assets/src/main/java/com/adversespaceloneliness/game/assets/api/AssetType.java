package com.adversespaceloneliness.game.assets.api;

import org.apache.commons.lang3.StringUtils;

/**
 * All asset types in the LibGDX game engine and their supported extensions.
 * <p>
 * All asset types in this enum store their extensions as plain extensions without a dot prefix.
 */
public enum AssetType implements ICachedEnumValues<AssetType> {

    IMAGE(new String[] { "jpg", "jpeg", "png", "bmp" }),
    AUDIO(new String[] { "wav", "mp3", "ogg" }),
    FONT(new String[] { "fnt", "ttf" }),
    TEXTURE_ATLAS(new String[] { "atlas" }),
    GUI_SKIN(new String[] { "json" }),
    LOCALIZATION(new String[] { "properties" }),
    PARTICLE_EFFECT(new String[] { "p" });

    public static final AssetType[] cachedEnumValues = AssetType.values();

    private static final char ID_SUFFIX_SEPARATOR = '_';

    private final String[] m_filenameExtensions;

    AssetType(String[] filenameExtensions) {
        m_filenameExtensions = filenameExtensions;
    }

    /**
     * Gets the {@link AssetType} correspond to the supplied extension.
     * <p>
     * The extension must not contain a dot prefix. Also, the case is ignored.
     *
     * @param soughtExtension The file name extension without a dot prefix.
     *
     * @return Returns the correspond <code>enum</code> value to the extension.
     */
    public static AssetType getAssetTypeFromExtension(String soughtExtension) {

        for (AssetType assetType : cachedEnumValues) {
            for (String assetTypeExtension : assetType.getFilenameExtensions()) {
                if (StringUtils.equalsIgnoreCase(soughtExtension, assetTypeExtension)) {
                    return assetType;
                }
            }
        }

        return null;
    }

    @Override
    public final AssetType[] getCachedEnumValues() {
        return cachedEnumValues;
    }

    /**
     * Computes the suffix for asset IDs for code generation.
     * <p>
     * The suffix is the ID suffix separator concatenated with the asset type.
     *
     * @return The suffix for asset IDs for code generation
     */
    public String computeIDSuffix() {
        return ID_SUFFIX_SEPARATOR + name();
    }

    public String[] getFilenameExtensions() {
        return m_filenameExtensions;
    }
}