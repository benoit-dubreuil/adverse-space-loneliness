package com.adversespaceloneliness.game.assets.generation.generator;

import com.adversespaceloneliness.game.assets.generation.TopRawDirectoryToTexturePack;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * The sprite packer generator, which create sprite sheets from multiple sprite assets.
 */
public class SpritePackerGenerator implements IAssetGenerator {

    @Override
    public boolean isTopRawDirectoryGeneratable(String topRawDirectory) {
        return topRawDirectory.startsWith(TopRawDirectoryToTexturePack.SPRITE.getDirectory());
    }

    @Override
    public void generateTopRawDirectory(String topRawDirectory) {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.maxWidth = 8192;
        settings.maxHeight = 8192;

        TexturePacker.process(settings, topRawDirectory, computeTopGeneratedDirectory(topRawDirectory), "spritesheet");
    }
}
