package com.relmaps.magalaxy.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.relmaps.magalaxy.InitGame;
import com.relmaps.magalaxy.screen.Pantalla;

import static com.relmaps.magalaxy.InitGame.inputs;


public class subMenu extends Pantalla {
    private Stage stage;
    private int zoom = 2;
    private Texture fondo;
    private Texture ter;
    private Texture regresar;
    private Texture _1;
    private Texture _2;
    private Texture _3;
    private Texture _4;
    private Texture _5;
    private Texture _6;
    private Texture _7;
    private Texture _8;
    private Texture _9;
    private Texture a;
    private Texture s;
    private Texture clic;
    private Texture shiift;
    private Texture mas;
    private Texture espacio;
    private Texture t1;
    private Texture t2;
    private Texture t3;
    private Texture t4;
    private Texture t5;
    private Texture t6;
    private Texture t7;

    public subMenu(InitGame game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        inputs.addProcessor(stage);
        fondo = getRecurso("menus/fondo1.png");
        ter = getRecurso("menus/salir.png");
        regresar = getRecurso("menus/regresar.png");
        _1 = getRecurso("menus/1.png");
        _2 = getRecurso("menus/2.png");
        _3 = getRecurso("menus/3.png");
        _4 = getRecurso("menus/4.png");
        _5 = getRecurso("menus/5.png");
        _6 = getRecurso("menus/6.png");
        _7 = getRecurso("menus/7.png");
        _8 = getRecurso("menus/8.png");
        _9 = getRecurso("menus/9.png");
        mas = getRecurso("menus/+.png");
        espacio = getRecurso("menus/espacio.png");
        shiift = getRecurso("menus/shift.png");
        s = getRecurso("menus/s.png");
        a = getRecurso("menus/a.png");
        clic = getRecurso("menus/clic.png");
        t1 = getRecurso("texto/t1.png");
        t2 = getRecurso("texto/t2.png");
        t3 = getRecurso("texto/t3.png");
        t4 = getRecurso("texto/t4.png");
        t5 = getRecurso("texto/t5.png");
        t6 = getRecurso("texto/t6.png");
        t7 = getRecurso("texto/t7.png");

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
        stage.getBatch().draw(regresar, 320 + 200, 50, 309, 94);
        stage.getBatch().draw(ter, 960 + 200, 50, 309, 94);

        stage.getBatch().draw(_1, 600 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_2, 700 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_3, 800 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_4, 900 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_5, 1000 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_6, 1100 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_7, 1200 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_8, 1300 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(_9, 1400 - 90, 1200 - 20, 98, 92);
        stage.getBatch().draw(t3, 950 - 90, 1050, 233, 97);

        stage.getBatch().draw(this.a, 200, 900, 99, 89);
        stage.getBatch().draw(this.s, 1700, 900, 97, 94);
        stage.getBatch().draw(t7, 200 - 80, 830, 307, 64);
        stage.getBatch().draw(t6, 1700 - 80, 830, 310, 65);

        stage.getBatch().draw(this.shiift, 200, 600, 139, 91);
        stage.getBatch().draw(this.mas, 400, 600, 100, 95);
        stage.getBatch().draw(this.a, 550, 600, 99, 89);
        stage.getBatch().draw(t5, 250, 480, 312, 99);

        stage.getBatch().draw(this.shiift, 1350, 600, 139, 91);
        stage.getBatch().draw(this.mas, 1550, 600, 100, 95);
        stage.getBatch().draw(this.s, 1700, 600, 99, 89);
        stage.getBatch().draw(t4, 1400, 480, 312, 99);

        stage.getBatch().draw(this.clic, 450, 300, 99, 89);
        stage.getBatch().draw(t1, 250, 150, 520, 128);

        stage.getBatch().draw(this.espacio, 1450, 300, 301, 95);
        stage.getBatch().draw(t2, 1525, 200, 145, 75);

        stage.getBatch().end();
        stage.draw();
    }

    private void checkTeclas() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 255 & Gdx.input.getX() < 415 & Gdx.input.getY() < 620 & Gdx.input.getY() > 555) {
                game.setScreen(new menuInicio(game));//comentar y crear pantalla
            } else if (Gdx.input.getX() > 575 & Gdx.input.getX() < 740 & Gdx.input.getY() < 620 & Gdx.input.getY() > 555) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
