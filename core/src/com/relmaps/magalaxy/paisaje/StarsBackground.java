package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.screen.GameScreen;

public class StarsBackground extends TimerBackground {


    private float originX, originY;

    public StarsBackground(Texture texture, Stage stage, float size) {
        super(texture, stage, size);
        originX = texture.getWidth() / 2 * size;
        originY = texture.getHeight() / 2 * size;
    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        time += 0.00005f;
        if (time >= 362f) {
            time = 1f;
        }
        b.draw(texture, (GameScreen.player.getX() - originX), (GameScreen.player.getY() - originY), originX, originY, texture.getWidth() * size, texture.getHeight() * size, 1, 1, (float) Math.toDegrees(-time), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }
}
