package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.relmaps.magalaxy.InitGame;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.entity.PlayerEntity;
import com.relmaps.magalaxy.world.Planet;
import com.relmaps.magalaxy.world.PlanetGenerator;

import static java.lang.Math.pow;

public class GameScreen extends Pantalla {

    private boolean debugBox2d = false;

    private Stage stage;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    public static PlayerEntity player;
    private Planet planet;

    private FrameRate rate;

    private int zoom = 2;

    public GameScreen(InitGame game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        world = new World(new Vector2(0, -40), true);
        rate = new FrameRate(world);
        stage.setDebugAll(false);

        if (debugBox2d) {
            renderer = new Box2DDebugRenderer();
            camera = new OrthographicCamera(1024 / 20, 640 / 20);
        }

        planet = new PlanetGenerator(5.97 * pow(10, 24), 6371).generateBlocks(world, this);
        //planet = new PlanetGenerator(7.349 * pow(10, 22), 1737).generateBlocks(world, this);
        world.setGravity(new Vector2(0, -planet.getGravity() * 4));
    }

    @Override
    public void show() {
        player = new PlayerEntity(world, getRecurso("player/man.png"), new Vector2(1, 5));

        stage.addActor(player);
        planet.showBlocks(stage);
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
        //block.detach();
        //block.remove();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getCamera().position.x = player.getX();
        stage.getCamera().position.y = player.getY();
        stage.getCamera().update();

        stage.act();
        world.step(delta, 8, 3);
        //System.out.println("Delta: " + delta);
        stage.draw();

        rate.render();
        rate.update();



        if (debugBox2d) {
            camera.position.x = 10;
            camera.position.y = 15;
            camera.update();
            renderer.render(world, camera.combined);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        if (debugBox2d) renderer.dispose();
    }
}
