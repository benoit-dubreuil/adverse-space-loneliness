package com.adversespaceloneliness.game.assets.api;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import org.apache.commons.lang3.StringUtils;

/**
 * All asset types in the LibGDX game engine and their supported extensions.
 * <p>
 * All asset types in this enum store their extensions as plain extensions without a dot prefix.
 */
public enum AssetType implements ICachedEnumValues<AssetType> {

    IMAGE(new String[] { "jpg", "jpeg", "png", "bmp" }, Texture.class),
    AUDIO(new String[] { "wav", "mp3", "ogg" }, Sound.class), // Or Music.class
    FONT(new String[] { "fnt", "ttf" }, BitmapFont.class),
    TEXTURE_ATLAS(new String[] { "atlas" }, TextureAtlas.class),
    GUI_SKIN(new String[] { "json" }, Skin.class),
    LOCALIZATION(new String[] { "properties" }, I18NBundle.class),
    PARTICLE_EFFECT(new String[] { "p" }, ParticleEffect.class);

    public static final AssetType[] cachedEnumValues = AssetType.values();

    private static final char ID_SUFFIX_SEPARATOR = '_';

    private final String[] m_filenameExtensions;
    private final Class<?> m_importClass;

    AssetType(String[] filenameExtensions, Class<?> importClass) {
        m_filenameExtensions = filenameExtensions;
        m_importClass = importClass;
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

    public String[] getFilenameExtensions() {
        return m_filenameExtensions;
    }

    public Class<?> getImportClass() {
        return m_importClass;
    }
}