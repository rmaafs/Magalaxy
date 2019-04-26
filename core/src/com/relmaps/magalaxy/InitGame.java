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
        manager.load("blocks/cobblestone.png", Texture.class);
        manager.load("blocks/gravel.png", Texture.class);

        manager.load("blocks/minerals/coal_ore.png", Texture.class);
        manager.load("blocks/minerals/copper_ore.png", Texture.class);
        manager.load("blocks/minerals/iron_ore.png", Texture.class);
        manager.load("blocks/minerals/gold_ore.png", Texture.class);
        manager.load("blocks/minerals/diamond_ore.png", Texture.class);
        manager.load("blocks/minerals/emerald_ore.png", Texture.class);
        manager.load("blocks/minerals/ruby_ore.png", Texture.class);

        manager.load("paisajes/grass/below1.png", Texture.class);
        manager.load("paisajes/grass/below2.png", Texture.class);
        manager.load("paisajes/grass/below3.png", Texture.class);
        manager.load("paisajes/grass/far.png", Texture.class);
        manager.load("paisajes/grass/fondo.png", Texture.class);
        manager.load("paisajes/grass/fondo2.png", Texture.class);
        for (int i = 1; i <= 7; i++){
            manager.load("paisajes/grass/grass" + i + ".png", Texture.class);
        }
        manager.load("paisajes/grass/treeback1.png", Texture.class);
        manager.load("paisajes/grass/treeback2.png", Texture.class);
        manager.load("paisajes/grass/treeback3.png", Texture.class);

        manager.finishLoading();
        setScreen(new GameScreen(this));
    }

    public AssetManager getManager() {
        return manager;
    }
}
