package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relmaps.magalaxy.InitGame;
import com.relmaps.magalaxy.sounds.SoundEffect;
import com.relmaps.magalaxy.sounds.SoundType;

public class IntroScreen extends Pantalla {

    private AssetManager manager;
    private SpriteBatch batch;
    private int frame = 0, wait = 0, sleep = 1;
    private String num = "";
    private SoundEffect sound;
    private InitGame game;
    private Texture t;

    public IntroScreen(InitGame game) {
        super(game);
        this.game = game;

        batch = new SpriteBatch();
        manager = new AssetManager();
        sound = new SoundEffect(true);


        for (int i = 1; i <= 1297; i++) {
            if (i < 10) {
                num = "000" + i;
            } else if (i < 100) {
                num = "00" + i;
            } else if (i < 1000) {
                num = "0" + i;
            } else {
                num = i + "";
            }

            //System.out.println("Cargando imagen: " + num);
            manager.load("intro/img/img" + num + ".png", Texture.class);
        }

        manager.finishLoading();
    }

    @Override
    public void render(float delta) {
        if (wait++ >= sleep) {
            if (frame == 0) {
                sound.play(SoundType.INTRO);
            }
            wait = 0;

            frame++;
            if (frame < 10) {
                num = "000" + frame;
            } else if (frame < 100) {
                num = "00" + frame;
            } else if (frame < 1000) {
                num = "0" + frame;
            } else {
                num = frame + "";
            }

            if (frame > 1297) {
                dispose();
                return;
            }
        }

        try {
            t = manager.get("intro/img/img" + num + ".png");
            batch.begin();
            batch.draw(t, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        } catch (Exception e) {
            game.startGame();
        }
    }

    @Override
    public void dispose() {
        manager.clear();
        t.dispose();
        sound.dispose();
    }
}
