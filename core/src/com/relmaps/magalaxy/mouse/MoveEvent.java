package com.relmaps.magalaxy.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.relmaps.magalaxy.input.InputGame;
import com.relmaps.magalaxy.screen.GameScreen;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;

public class MoveEvent extends InputGame {

    private Batch batch;
    private int x = 0, y = 0;

    public MoveEvent(Batch batch) {
        this.batch = batch;
    }

    /*@Override
    public boolean mouseMoved(int screenX, int screenY) {
        System.out.println("Mouse x=" + screenX + "(" + Gdx.input.getX() + ")" + ", y=" + screenY);
        System.out.println("Player: " + GameScreen.player.getX() + ", " + GameScreen.player.getY());
        float cx = screenX / PIXELS_IN_METER;
        float cy = screenY / PIXELS_IN_METER;
        if (cx % 0.5f == 0) x = screenX;
        if (cy % 0.5f == 0) y = -screenY + Gdx.graphics.getHeight();

        return false;
    }*/

    @Override
    public boolean keyTyped(char character) {
        if (character == 'k') {
            System.out.println("Mouse x=" + Gdx.input.getX() + ", y=" + Gdx.input.getY());
            System.out.println("Player: " + GameScreen.player.getX() + ", " + GameScreen.player.getY());
            System.out.println("Bloque: " + (Gdx.input.getX() - GameScreen.player.getX()) + ", " + (Gdx.input.getY() - GameScreen.player.getY()));
        }
        return false;
    }

    public void draw() {
        if (GameScreen.player.getItemInHand() != null) {
            float sizex = PIXELS_IN_METER * 0.25f;
            float sizey = PIXELS_IN_METER * 0.25f;
            batch.draw(GameScreen.player.getItemInHand().getTexture(), x, y, sizex, sizey);
        }
    }
}
