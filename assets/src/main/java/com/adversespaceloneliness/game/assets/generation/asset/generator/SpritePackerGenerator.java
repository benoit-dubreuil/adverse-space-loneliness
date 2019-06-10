package com.adversespaceloneliness.game.assets.generation.asset.generator;

import com.adversespaceloneliness.game.assets.generation.asset.generator.data.RawDirToTexturePack;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * The sprite packer generator, which create sprite sheets from multiple sprite assets.
 */
public class SpritePackerGenerator implements IAssetGenerator {

    private static final String ATLAS_NAME = "spritesheet_and_texture";

    @Override
    public boolean isDirPathGeneratable(String rawDirPath) {
        return rawDirPath.startsWith(RawDirToTexturePack.SPRITE.getDir());
    }

    @Override
    public void generate(String topRawDirectory) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        TexturePacker.process(settings, topRawDirectory, computeGeneratedDirPath(topRawDirectory), ATLAS_NAME);
    }
}
