package com.adversespaceloneliness.game.core;

import com.adversespaceloneliness.game.assets.generated.AssetID;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class AdverseSpaceLoneliness extends ApplicationAdapter {

    private AssetManager m_assetManager;
    private SpriteBatch m_batch;

    private BitmapFont m_font;
    private Texture m_img;
    private TextureAtlas m_atlas;
    private Sprite m_sprite;

    @Override
    public void create() {
        m_assetManager = new AssetManager();

        FileHandleResolver freeTypeResolver = new InternalFileHandleResolver();
        m_assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(freeTypeResolver));
        m_assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(freeTypeResolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter freeTypeParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        freeTypeParams.fontFileName = AssetID.WALTERTURNCOAT.getPath();
        freeTypeParams.fontParameters.size = 40;

        AssetID.WALTERTURNCOAT.loadAsset(m_assetManager, freeTypeParams);
        AssetID.BADLOGIC.loadAsset(m_assetManager);
        AssetID.SPRITESHEET_AND_TEXTURE_ATLAS.loadAsset(m_assetManager);

        m_assetManager.finishLoading();

        m_batch = new SpriteBatch();

        m_font = m_assetManager.get(AssetID.WALTERTURNCOAT.getPath());
        m_img = m_assetManager.get(AssetID.BADLOGIC.getPath());

        m_atlas = m_assetManager.get(AssetID.SPRITESHEET_AND_TEXTURE_ATLAS.getPath());
        m_sprite = m_atlas.createSprite("retro-space-pixel-shooter-pack/misc/nuke", 5);
        m_sprite.setPosition(400, 400);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        m_batch.begin();

        m_batch.draw(m_img, 0, 0);
        m_sprite.draw(m_batch);
        m_font.draw(m_batch, "Je suis un texte ttf!", 250, 100);

        m_batch.end();
    }

    @Override
    public void dispose() {
        m_batch.dispose();
        m_assetManager.dispose();
    }
}
