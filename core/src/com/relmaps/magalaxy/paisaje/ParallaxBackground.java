package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.screen.GameScreen;

import java.math.BigDecimal;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_VISIBILITY_X;

public class ParallaxBackground extends Actor {

    private Stage stage;
    private Texture texture;
    private BackgroundType type;
    private float scroll, width, heigth, speed, lastXPlayer = 0f;
    private float movement, playerx, playery, distance;
    private int srcX, xCenter, yLevel;
    private Color treeColor;
    private int arribaY = 200;

    //MOUNTAIN
    public ParallaxBackground(Texture texture, Stage stage, float step, BackgroundType type) {
        this.type = type;
        int size = 2;
        distance = 2f;
        width = Gdx.graphics.getWidth() * size;
        heigth = Gdx.graphics.getHeight() * size;
        configure(texture, stage, step);
    }

    //GRASS
    public ParallaxBackground(Texture texture, Stage stage, float size, float step, float distance, BackgroundType type) {
        this.distance = distance;
        this.type = type;
        width = Gdx.graphics.getWidth() / size;
        heigth = Gdx.graphics.getHeight() / size;
        configure(texture, stage, step);
    }

    //TREE
    public ParallaxBackground(Texture texture, Stage stage, int level, int x, BackgroundType type) {
        float size = level * 0.7f;
        float step = 2f;
        if (level == 1) {
            size = 1f;
            step = 2f;
        } else if (level == 2) {
            step = 1.35f;
        } else if (level == 3) {
            step = 1.2f;
        } else if (level == 4) {
            step = 1.14f;
        } else if (level == 5) {
            step = 1.1f;
        } else if (level == 6) {
            step = 1.09f;
        } else if (level == 7) {
            step = 1.074f;
        }

        int reducirTam = Constants.getRand(1, 8);
        if (reducirTam >= 5) {
            size = size + (size / reducirTam);
        }


        switch (Constants.getRand(1, 4)) {
            case 1:
                treeColor = Color.CYAN;
                break;
            case 2:
                treeColor = Color.YELLOW;
                break;
            case 3:
                treeColor = Color.ORANGE;
                break;
            default:
                treeColor = Color.GREEN;
        }

        this.distance = 0.06f * level;
        this.type = type;
        this.xCenter = x;
        this.yLevel = level;
        width = Gdx.graphics.getWidth() / size;
        heigth = Gdx.graphics.getHeight() / size;
        configure(texture, stage, step);
    }

    private void configure(Texture texture, Stage stage, float step) {
        this.texture = texture;
        this.stage = stage;
        if (type != BackgroundType.TREE) {
            this.texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        }

        scroll = Constants.getRand(0, 500);
        speed = step;
    }

    public void activar() {
        stage.addActor(this);
        this.setVisible(true);
    }

    public void desactivar() {
        this.remove();
        this.setVisible(false);
    }

    public boolean estaEnRangoDeVision() {
        return (getX() > GameScreen.player.getX() - (PLAYER_VISIBILITY_X + 1) * PIXELS_IN_METER - width && getX() < GameScreen.player.getX() + PLAYER_VISIBILITY_X * PIXELS_IN_METER + heigth)
                /*&& (getY() > GameScreen.player.getY() - (PLAYER_VISIBILITY_Y + 1) * PIXELS_IN_METER - width && getY() < GameScreen.player.getY() + PLAYER_VISIBILITY_Y * PIXELS_IN_METER + heigth)*/;
    }

    public void refresh() {
        if (type == BackgroundType.TREE) {
            if (estaEnRangoDeVision()) {
                if (!isAlive()) {
                    activar();
                }
            } else if (isAlive()) {
                desactivar();
            }
        }
    }

    public boolean isAlive() {
        return this.isVisible();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        movement = 0f;
        playerx = GameScreen.player.getX();
        playery = GameScreen.player.getY();

        if (playerx < lastXPlayer) {
            movement = lastXPlayer - playerx;
            scroll -= movement / speed;
        } else if (playerx > lastXPlayer) {
            movement = playerx - lastXPlayer;
            scroll += movement / speed;
        }
        lastXPlayer = playerx;

        srcX = new BigDecimal(scroll).intValue();

        if (type == BackgroundType.GRASS) {
            batch.draw(texture, playerx - Gdx.graphics.getWidth(), (playery * distance) - Gdx.graphics.getHeight() / 2 + arribaY, 0, 0, width, heigth, 1, 1, 0, srcX, 0, texture.getWidth(), texture.getHeight(), false, false);
            batch.draw(texture, playerx - Gdx.graphics.getWidth() / 2, (playery * distance) - Gdx.graphics.getHeight() / 2 + arribaY, 0, 0, width, heigth, 1, 1, 0, srcX, 0, texture.getWidth(), texture.getHeight(), false, false);
            batch.draw(texture, playerx, (playery * distance) - Gdx.graphics.getHeight() / 2 + arribaY, 0, 0, width, heigth, 1, 1, 0, srcX, 0, texture.getWidth(), texture.getHeight(), false, false);
            batch.draw(texture, playerx + Gdx.graphics.getWidth() / 2, (playery * distance) - Gdx.graphics.getHeight() / 2 + arribaY, 0, 0, width, heigth, 1, 1, 0, srcX, 0, texture.getWidth(), texture.getHeight(), false, false);
        } else if (type == BackgroundType.MOUNTAIN) {
            batch.draw(texture, playerx - Gdx.graphics.getWidth(), (playery / distance) - Gdx.graphics.getHeight() + arribaY / 2, 0, 0, width, heigth, 1, 1, 0, srcX, 0, texture.getWidth(), texture.getHeight(), false, false);
        } else {
            Color aux = batch.getColor();
            batch.setColor(treeColor);
            setPosition(srcX - xCenter, (playery * distance) - Gdx.graphics.getHeight() / 2 + (yLevel * 35) + arribaY);
            batch.draw(texture, srcX - xCenter, (playery * distance) - Gdx.graphics.getHeight() / 2 + (yLevel * 35) + arribaY, width, heigth);
            batch.setColor(aux);
        }
    }
}
