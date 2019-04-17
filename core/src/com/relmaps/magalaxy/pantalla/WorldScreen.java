package com.relmaps.magalaxy.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.relmaps.magalaxy.InitGame;

public class WorldScreen extends Pantalla {

    public WorldScreen(InitGame game){
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private Body playerBody;
    private Fixture playerFixture;

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);

        BodyDef playerDef = createPlayerBodyDef();
        playerBody = world.createBody(playerDef);

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(1, 1);
        playerFixture = playerBody.createFixture(playerShape, 1);
        playerShape.dispose();
    }

    private BodyDef createPlayerBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, 10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void dispose() {
        playerBody.destroyFixture(playerFixture);
        world.destroyBody(playerBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 6, 2);

        camera.update();
        renderer.render(world, camera.combined);
    }
}
