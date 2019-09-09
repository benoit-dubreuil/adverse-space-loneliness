package com.adversespaceloneliness.game.assets.api;

/**
 * Interface for a generated asset enum.
 * <p>
 * It provides simple asset metadata.
 *
 * @see com.adversespaceloneliness.game.assets.generation.code.generator.concrete.IDGenerator
 */
public interface IAssetID {

    /**
     * Gets the asset's relative path.
     * <p>
     * The asset's relative path uses the Unix file name separator (slash).
     *
     * @return Returns the asset's relative path.
     */
    String getPath();

    /**
     * Gets the asset's {@link AssetType}.
     *
     * @return Returns the asset's {@link AssetType}.
     */
    AssetType getAssetType();
}
