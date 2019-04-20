package com.relmaps.magalaxy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relmaps.magalaxy.screen.GameScreen;
import com.relmaps.magalaxy.screen.WorldScreen;

public class InitGame extends Game {

    private AssetManager manager;

    @Override
    public void create() {
        manager = new AssetManager();
        manager.load("player/man.png", Texture.class);
        manager.load("blocks/dirt.png", Texture.class);
        manager.load("blocks/grass.png", Texture.class);
        manager.finishLoading();
        setScreen(new GameScreen(this));
    }

    public AssetManager getManager() {
        return manager;
    }
}
