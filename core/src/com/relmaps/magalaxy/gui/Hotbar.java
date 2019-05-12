package com.relmaps.magalaxy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.relmaps.magalaxy.mouse.ScrollEvent;

public class Hotbar extends Actor {

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private ScrollEvent scroll;

    private TextureRegion hotbar;
    private TextureRegion itemSelect;

    private int currentItem = 0;

    private int sizeItemSelect;

    public Hotbar(Texture texture) {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        scroll = new ScrollEvent(this);
        hotbar = new TextureRegion(texture, 0, 0, 182, 22);
        itemSelect = new TextureRegion(texture, 182, 0, 24, 24);
        sizeItemSelect = 24 * 2;
        setSize(hotbar.getRegionWidth() * 2, hotbar.getRegionHeight() * 2);
    }

    public void scroll(boolean derecha) {
        if (derecha) {
            if (++currentItem > 8) currentItem = 0;
        } else {
            if (--currentItem < 0) currentItem = 8;
        }
    }

    public void setScroll(int i) {
        currentItem = i;
    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        batch.begin();
        batch.draw(hotbar, Gdx.graphics.getHeight() + 15, Gdx.graphics.getHeight() - getHeight() - 5, getWidth(), getHeight());
        batch.draw(itemSelect, Gdx.graphics.getHeight() + 13 + (currentItem * 20 * 2), Gdx.graphics.getHeight() - sizeItemSelect - 3, sizeItemSelect, sizeItemSelect);
        batch.end();
    }
}
