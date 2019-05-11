package com.relmaps.magalaxy.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.screen.GameScreen;
import com.relmaps.magalaxy.world.Planet;

import static com.relmaps.magalaxy.entity.Constants.BLOCK_DROP_FLOATING_SPEED;
import static com.relmaps.magalaxy.entity.Constants.BLOCK_DROP_JUMP_SPEED;
import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_PICKUP_DROP_DISTANCE;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_VISIBILITY_X;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_VISIBILITY_Y;

public class BlockDrop extends Actor {

    private TextureRegion texture;
    private BlockType type;
    private World world;
    private Stage stage;
    private Planet planet;

    private Body body;
    private BodyDef def;
    private PolygonShape shape;
    private Fixture fixture;

    private float size = 0.15f;
    private float floatY = 0.0f;
    private boolean movingFloatYUp = true;

    public BlockDrop(TextureRegion texture, BlockType type, World world, Stage stage, Planet planet, Vector2 position) {
        this.texture = texture;
        this.type = type;
        this.world = world;
        this.stage = stage;
        this.planet = planet;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;

        shape = new PolygonShape();
        shape.setAsBox(size, size);

        activar();

        body.applyLinearImpulse(0, BLOCK_DROP_JUMP_SPEED, body.getPosition().x, body.getPosition().y, true);

        setSize(PIXELS_IN_METER * size * 2, PIXELS_IN_METER * size * 2);
        setPosition((body.getPosition().x - size) * PIXELS_IN_METER, (body.getPosition().y - size) * PIXELS_IN_METER);
    }

    public void activar() {
        body = world.createBody(def);
        fixture = body.createFixture(shape, 1);
        stage.addActor(this);
        //this.addListener(hoverEvent);
        this.setVisible(true);
    }

    public void desactivar() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
        this.remove();
        //this.removeListener(hoverEvent);
        this.setVisible(false);
    }

    public void pickUp() {
        desactivar();
        planet.getBlockDrops().remove(this);
    }

    public boolean isAlive() {
        return this.isVisible();
    }

    public boolean estaEnRangoDeVision() {
        return (getX() > GameScreen.player.getX() - (PLAYER_VISIBILITY_X + 1) * PIXELS_IN_METER && getX() < GameScreen.player.getX() + PLAYER_VISIBILITY_X * PIXELS_IN_METER)
                && (getY() > GameScreen.player.getY() - (PLAYER_VISIBILITY_Y + 1) * PIXELS_IN_METER && getY() < GameScreen.player.getY() + PLAYER_VISIBILITY_Y * PIXELS_IN_METER);
    }

    public void refresh() {
        if (estaEnRangoDeVision()) {
            if (!isAlive()) {
                activar();
            }
        } else if (isAlive()) {
            desactivar();
        }
    }

    public void checkIfPlayerPickUp() {
        int distance = (int) (Math.sqrt(Math.pow(((GameScreen.player.getX() + (getWidth() / 2)) - getX()), 2) + Math.pow(((GameScreen.player.getY() + (getHeight() / 2)) - getY()), 2)) * PIXELS_IN_METER / 2);
        if (distance < PLAYER_PICKUP_DROP_DISTANCE) {
            body.setLinearVelocity((GameScreen.player.getBody().getPosition().x - body.getPosition().x) * 8, (GameScreen.player.getBody().getPosition().y - body.getPosition().y) * 8);
            if (distance < 2600) {
                pickUp();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (movingFloatYUp) {
            floatY += BLOCK_DROP_FLOATING_SPEED;
            if (floatY > 10) {
                movingFloatYUp = false;
            }
        } else {
            floatY -= BLOCK_DROP_FLOATING_SPEED;
            if (floatY <= 3) {
                movingFloatYUp = true;
            }
        }

        setPosition((body.getPosition().x - size) * PIXELS_IN_METER, (body.getPosition().y - size) * PIXELS_IN_METER + floatY);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        checkIfPlayerPickUp();
    }
}
