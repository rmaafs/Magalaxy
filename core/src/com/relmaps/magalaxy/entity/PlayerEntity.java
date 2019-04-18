package com.relmaps.magalaxy.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_JUMP_SPEED;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_SPEED;

public class PlayerEntity extends Actor {
    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean alive = true, jumping = false;

    private float tamañoX = 0.5f, tamañoY = 1f;

    public PlayerEntity(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(tamañoX, tamañoY);
        fixture = body.createFixture(shape, 3);
        shape.dispose();

        setSize(PIXELS_IN_METER * tamañoX * 2, PIXELS_IN_METER * tamañoY * 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - tamañoX) * PIXELS_IN_METER,
                    (body.getPosition().y - tamañoY) * PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            body.applyLinearImpulse(0, PLAYER_JUMP_SPEED, body.getPosition().x, body.getPosition().y, true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            body.applyLinearImpulse(PLAYER_SPEED, 0, body.getPosition().x, body.getPosition().y, true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            body.applyLinearImpulse(-PLAYER_SPEED, 0, body.getPosition().x, body.getPosition().y, true);
        }
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
