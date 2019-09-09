package com.adversespaceloneliness.game.assets.api;

/**
 * Indicates that the implementing <code>Enum</code> must have a cache for all its enum values.
 * <p>
 * The reason behind this is that an array of {@link Enum} values is instantiated each time the function is called.
 *
 * @param <E> The {@link Enum} that must cache its values.
 */
public interface ICachedEnumValues<E extends Enum<E>> {

    /**
     * Gets the cached <code>enum</code> values for this <code>enum</code>.
     *
     * @return Returns the cached <code>enum</code> values for this <code>enum</code>.
     */
    E[] getCachedEnumValues();
}
