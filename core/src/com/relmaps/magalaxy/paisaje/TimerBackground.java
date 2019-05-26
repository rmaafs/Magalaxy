package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.screen.GameScreen;

public class TimerBackground extends Actor {

    private Texture texture;
    private Stage stage;
    private float size;
    private float time = 0f;
    private float orbit = 800f;

    public TimerBackground(Texture texture, Stage stage, float size) {
        this.texture = texture;
        this.stage = stage;
        this.size = size;
        stage.addActor(this);
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

    public void addTime(float time) {
        this.time += time;
    }

    public float getTime() {
        return time;
    }
}
