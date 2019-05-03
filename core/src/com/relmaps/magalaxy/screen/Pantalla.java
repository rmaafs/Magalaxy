package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.relmaps.magalaxy.InitGame;

public abstract class Pantalla implements Screen {

    protected InitGame game;

    public Pantalla(InitGame game) {
        this.game = game;
    }

    public Texture getRecurso(String path) {
        return game.getManager().get(path);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
