package com.adversespaceloneliness.game.assets.generation.asset.generator;

import com.adversespaceloneliness.game.assets.generation.asset.generator.data.RawDirectoryToTexturePack;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * The sprite packer generator, which create sprite sheets from multiple sprite assets.
 */
public class SpritePackerGenerator implements IAssetGenerator {

    @Override
    public boolean isRawDirPathGeneratable(String rawDirPath) {
        return rawDirPath.startsWith(RawDirectoryToTexturePack.SPRITE.getDirectory());
    }

    @Override
    public void generate(String topRawDirectory) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        TexturePacker.process(settings, topRawDirectory, computeGeneratedDirPath(topRawDirectory), "spritesheet");
    }
}
