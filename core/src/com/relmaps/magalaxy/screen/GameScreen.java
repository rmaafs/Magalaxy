package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.relmaps.magalaxy.InitGame;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.entity.PlayerEntity;
import com.relmaps.magalaxy.world.Planet;
import com.relmaps.magalaxy.world.PlanetGenerator;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

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

    private PointLight light;
    private RayHandler rayHandler;

    private int zoom = 2;

    public GameScreen(InitGame game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        world = new World(new Vector2(0, -40), true);
        rate = new FrameRate(world, stage);
        stage.setDebugAll(false);

        if (debugBox2d) {
            renderer = new Box2DDebugRenderer();
            camera = new OrthographicCamera(1024 / 20, 640 / 20);
        }

        planet = new PlanetGenerator(5.97 * pow(10, 24), 6371).generateBlocks(world, this, stage);
        //planet = new PlanetGenerator(7.349 * pow(10, 22), 1737).generateBlocks(world, this);
        world.setGravity(new Vector2(0, -planet.getGravity() * 4));

        rayHandler = new RayHandler(world);
        Light.setContactFilter((short) 32, (short) 32, (short) 32);

        light = new PointLight(rayHandler, 5000, Color.BLACK, 5000, 400 * Constants.PIXELS_IN_METER, 150 * Constants.PIXELS_IN_METER);
        light.setSoftnessLength(10f);


    }

    @Override
    public void show() {
        player = new PlayerEntity(world, getRecurso("player/man.png"), new Vector2(1, 5));
        stage.addActor(player);
        light.attachToBody(player.getBody(), 0f, 50f);
        planet.showBlocks(stage);
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        stage.getCamera().update();
        rate.update();

        stage.getCamera().position.x = player.getX();
        stage.getCamera().position.y = player.getY();
        rayHandler.setCombinedMatrix(stage.getCamera().combined.cpy().scale(Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER, 1f));


        stage.act();
        world.step(delta, 8, 3);
        stage.draw();

        planet.limpiarActores();

        rayHandler.updateAndRender();
        rate.render();

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
