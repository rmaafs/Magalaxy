package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.screen.GameScreen;

public class SunBackground extends TimerBackground {
    public SunBackground(Texture texture, Stage stage, float size) {
        super(texture, stage, size);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        time += 0.005f;
        if (time >= 362f) {
            System.out.println("Dia completado!");
            time = 1f;
        }

        batch.draw(texture, (GameScreen.player.getX() - (texture.getWidth() * 1.5f)) + (orbit * ((float) Math.sin(Math.toRadians(time)))), (GameScreen.player.getY()) + (orbit / 2 * ((float) Math.cos(Math.toRadians(time)))), texture.getHeight() * size, texture.getHeight() * size);
    }
}
