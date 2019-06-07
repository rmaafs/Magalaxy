package com.relmaps.magalaxy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.relmaps.magalaxy.menu.menuInicio;
import com.relmaps.magalaxy.sounds.SoundEffect;

public class InitGame extends Game {

    private AssetManager manager;
    public static InputMultiplexer inputs = new InputMultiplexer();
    public static SoundEffect sound;

    @Override
    public void create() {
        sound = new SoundEffect();
        //setScreen(new IntroScreen(this));
        startGame();
    }

    public void startGame() {

        manager = new AssetManager();

        manager.load("player/skin.png", Texture.class);

        manager.load("player/man.png", Texture.class);
        manager.load("blocks/dirt.png", Texture.class);
        manager.load("blocks/grass.png", Texture.class);
        manager.load("blocks/cobblestone.png", Texture.class);
        manager.load("blocks/gravel.png", Texture.class);
        manager.load("blocks/breaking.png", Texture.class);
        manager.load("blocks/hovermouse.png", Texture.class);

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
        for (int i = 1; i <= 7; i++) {
            manager.load("paisajes/grass/grass" + i + ".png", Texture.class);
        }
        manager.load("paisajes/grass/treeback1.png", Texture.class);
        manager.load("paisajes/grass/treeback2.png", Texture.class);
        manager.load("paisajes/grass/treeback3.png", Texture.class);

        manager.load("paisajes/sun.png", Texture.class);

        manager.load("gui/hotbar.png", Texture.class);
        manager.load("paisajes/star.png", Texture.class);

        manager.load("menus/fondo.png", Texture.class);
        manager.load("menus/fondo1.png", Texture.class);
        manager.load("menus/jugar.png", Texture.class);
        manager.load("menus/instruciones.png", Texture.class);
        manager.load("menus/salir.png", Texture.class);
        manager.load("menus/regresar.png", Texture.class);
        manager.load("menus/1.png", Texture.class);
        manager.load("menus/2.png", Texture.class);
        manager.load("menus/3.png", Texture.class);
        manager.load("menus/4.png", Texture.class);
        manager.load("menus/5.png", Texture.class);
        manager.load("menus/6.png", Texture.class);
        manager.load("menus/7.png", Texture.class);
        manager.load("menus/8.png", Texture.class);
        manager.load("menus/9.png", Texture.class);
        manager.load("menus/clic.png", Texture.class);
        manager.load("menus/+.png", Texture.class);
        manager.load("menus/a.png", Texture.class);
        manager.load("menus/s.png", Texture.class);
        manager.load("menus/espacio.png", Texture.class);
        manager.load("menus/shift.png", Texture.class);
        manager.load("texto/t1.png", Texture.class);
        manager.load("texto/t2.png", Texture.class);
        manager.load("texto/t3.png", Texture.class);
        manager.load("texto/t4.png", Texture.class);
        manager.load("texto/t5.png", Texture.class);
        manager.load("texto/t6.png", Texture.class);
        manager.load("texto/t7.png", Texture.class);

        manager.finishLoading();
        setScreen(new menuInicio(this));
    }

    public AssetManager getManager() {
        return manager;
    }
}
