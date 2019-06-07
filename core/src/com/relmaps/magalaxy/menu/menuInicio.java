package com.relmaps.magalaxy.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.relmaps.magalaxy.InitGame;
import com.relmaps.magalaxy.screen.GameScreen;
import com.relmaps.magalaxy.screen.Pantalla;

import static com.relmaps.magalaxy.InitGame.inputs;

public class menuInicio extends Pantalla {
    private Stage stage;
    private World world;
    private int zoom = 2;
    private Texture fondo;
    private Texture bJugar;
    private Texture bIns;
    private Texture bSalir;


    public menuInicio(InitGame game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        inputs.addProcessor(stage);

        fondo = getRecurso("menus/fondo.png");
        bJugar = getRecurso("menus/jugar.png");
        bIns = getRecurso("menus/instruciones.png");
        bSalir = getRecurso("menus/salir.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkTeclas();
        stage.getCamera().update();
        stage.act();
        stage.getBatch().begin();

        //aqui comienza
        stage.getBatch().draw(fondo, 0, 0, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
        stage.getBatch().draw(bJugar, 320 + 200, 250, 309, 94);
        stage.getBatch().draw(bIns, 640 + 200, 250, 309, 94);
        stage.getBatch().draw(bSalir, 960 + 200, 250, 309, 94);

        stage.getBatch().end();
        stage.draw();
    }

    private void checkTeclas() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 259 & Gdx.input.getX() < 412 & Gdx.input.getY() < 515 & Gdx.input.getY() > 460) {
                game.setScreen(new GameScreen(game));//comentar y crear pantalla
            } else if (Gdx.input.getY() < 515 & Gdx.input.getY() > 460 & Gdx.input.getX() > 419 & Gdx.input.getX() < 578) {
                game.setScreen(new subMenu(game));
            } else if (Gdx.input.getY() < 515 & Gdx.input.getY() > 460 & Gdx.input.getX() > 580 & Gdx.input.getX() < 725) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
