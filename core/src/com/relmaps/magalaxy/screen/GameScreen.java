package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.relmaps.magalaxy.gui.Hotbar;
import com.relmaps.magalaxy.world.Planet;
import com.relmaps.magalaxy.world.PlanetGenerator;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import static com.relmaps.magalaxy.InitGame.inputs;
import static java.lang.Math.pow;

public class GameScreen extends Pantalla {

    private boolean debugBox2d = false;
    private boolean lights = true;

    private Stage stage;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    public static PlayerEntity player;
    private Planet planet;

    private FrameRate rate;
    private Hotbar hotbar;

    private PointLight light;
    private RayHandler rayHandler;

    private int zoom = 2;

    public GameScreen(InitGame game) {
        super(game);
        stage = new Stage(new FitViewport(1024 * zoom, 640 * zoom));
        stage.setDebugAll(false);
        inputs.addProcessor(stage);
        //Gdx.input.setInputProcessor();
        world = new World(new Vector2(0, -40), true);
        planet = new PlanetGenerator(5.97 * pow(10, 24), 6371).generateBlocks(world, this, stage);
        rate = new FrameRate(world, stage, planet);
        hotbar = new Hotbar(getRecurso("gui/hotbar.png"));
        Gdx.input.setInputProcessor(inputs);

        if (debugBox2d) {
            renderer = new Box2DDebugRenderer();
            camera = new OrthographicCamera(stage.getWidth() / Constants.PIXELS_IN_METER, stage.getHeight() / Constants.PIXELS_IN_METER);
        }


        world.setGravity(new Vector2(0, -planet.getGravity() * 4));

        if (lights) {
            rayHandler = new RayHandler(world);
            Light.setContactFilter((short) 32, (short) 32, (short) 32);

            light = new PointLight(rayHandler, 5000, Color.BLACK, 5000, 400 * Constants.PIXELS_IN_METER, 150 * Constants.PIXELS_IN_METER);
            light.setSoftnessLength(10f);
        }
    }

    @Override
    public void show() {
        player = new PlayerEntity(world, getRecurso("player/man.png"), new Vector2(1, 5));
        stage.addActor(player);
        planet.showBlocks(stage);
        if (lights) light.attachToBody(player.getBody(), 0f, 60f);
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

        checkTeclas();


        stage.getCamera().update();
        rate.update();

        stage.getCamera().position.x = player.getX();
        stage.getCamera().position.y = player.getY();

        stage.act();
        world.step(delta, 8, 3);
        stage.draw();

        planet.limpiarActores();

        if (lights) {
            float time = planet.getTime();
            float luz = (float) (1000 * (Math.cos(Math.toRadians(time))));
            if (luz > 80f) {
                light.setDistance(luz);
            }
            rayHandler.setCombinedMatrix(stage.getCamera().combined.cpy().scale(Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER, 1f));
            rayHandler.updateAndRender();
        }

        hotbar.draw(stage.getBatch(), Gdx.graphics.getDeltaTime());
        rate.render();

        if (debugBox2d) {
            camera.update();
            camera.position.x = player.getX() / Constants.PIXELS_IN_METER;
            camera.position.y = player.getY() / Constants.PIXELS_IN_METER;
            renderer.render(world, camera.combined);
        }
    }

    private void checkTeclas() {
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            planet.addTime(-1f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            planet.addTime(1f);
        }

        if (Gdx.input.justTouched()) {
            //System.out.println("Cursor: " + Gdx.input.getX() / Constants.PIXELS_IN_METER + ", " + Gdx.input.getY() / Constants.PIXELS_IN_METER);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        if (debugBox2d) renderer.dispose();
    }
}
