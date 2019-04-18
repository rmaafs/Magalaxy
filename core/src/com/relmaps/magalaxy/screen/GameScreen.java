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

public class GameScreen extends Pantalla {

    private Stage stage;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private PlayerEntity player;
    //private Block block;

    public GameScreen(InitGame game) {
        super(game);
        stage = new Stage(new FitViewport(1024, 640));
        world = new World(new Vector2(0, -10), true);
        stage.setDebugAll(true);

        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 80, Gdx.graphics.getHeight() / 80);
    }

    @Override
    public void show() {
        player = new PlayerEntity(world, getRecurso("player/man.png"), new Vector2(1, 5));

        //block = new Block(world, getRecurso("blocks/dirt.png"), new Vector2(1.1f, 1));
        stage.addActor(player);
        stage.addActor(new Block(world, getRecurso("blocks/dirt.png"), new Vector2(1f, 1)));
        stage.addActor(new Block(world, getRecurso("blocks/dirt.png"), new Vector2(1.5f, 1)));
        /*stage.addActor(new Block(world, getRecurso("blocks/dirt.png"), new Vector2(1.5f, 1)));
        stage.addActor(new Block(world, getRecurso("blocks/dirt.png"), new Vector2(1.7f, 1)));
        stage.addActor(new Block(world, getRecurso("blocks/dirt.png"), new Vector2(1.9f, 1)));
        stage.addActor(new Block(world, getRecurso("blocks/dirt.png"), new Vector2(2.1f, 1)));*/

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

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
        camera.update();
        renderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
        renderer.dispose();
    }
}
