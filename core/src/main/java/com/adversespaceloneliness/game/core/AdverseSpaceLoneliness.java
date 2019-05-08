package com.adversespaceloneliness.game.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AdverseSpaceLoneliness extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    TextureAtlas atlas;
    Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("assets/generated/texture/badlogic.jpg");


        atlas = new TextureAtlas(Gdx.files.internal("assets/generated/sprite/spritesheet.atlas"));
        sprite = atlas.createSprite("retro-space-pixel-shooter-pack/misc/nuke", 5);
        sprite.setPosition(400, 400);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(img, 0, 0);
        sprite.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        atlas.dispose();
    }
}
