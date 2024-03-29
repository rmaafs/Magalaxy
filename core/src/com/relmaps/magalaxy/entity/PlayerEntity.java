package com.relmaps.magalaxy.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.relmaps.magalaxy.gui.Hotbar;
import com.relmaps.magalaxy.sounds.SoundType;

import static com.relmaps.magalaxy.InitGame.sound;
import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_JUMP_SPEED;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_SPEED;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_SPEED_SHIFT;

public class PlayerEntity extends Actor {
    private Texture texture;
    private TextureRegion textReg;
    private World world;
    private Body body;
    private Fixture fixture;
    private Hotbar hotbar;

    private float sizeX = 0.5f, sizeY = 1f, countWalking = 0f, countJumping = 1f;
    private float lastY = 0;//Variable para ver la última vez que estaba en Y (Sirve para ver si está callendo)
    private boolean walking = false, jumping = true, onFloor = false, falling = true, shift = false;

    public PlayerEntity(World world, Texture texture, Vector2 position, Hotbar hotbar) {
        this.world = world;
        this.texture = texture;
        this.hotbar = hotbar;


        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        Vector2[] vec = new Vector2[6];

        vec[0] = new Vector2(-0.02f, -sizeY);
        vec[1] = new Vector2(0.02f, -sizeY);
        vec[2] = new Vector2(-sizeX + 0.05f, -sizeY / 2 + 0.3f);
        vec[3] = new Vector2(-sizeX + 0.05f, sizeY);
        vec[4] = new Vector2(sizeX - 0.05f, sizeY);
        vec[5] = new Vector2(sizeX - 0.05f, -sizeY / 2 + 0.3f);

        shape.set(vec);
        fixture = body.createFixture(shape, 1);
        shape.dispose();

        //disableLightShadow();

        body.setUserData("player");
        setSize(PIXELS_IN_METER * sizeX * 2, PIXELS_IN_METER * sizeY * 2);
    }

    private void disableLightShadow() {
        Filter boxBreakFilter = new Filter();
        short value = 0;
        boxBreakFilter.categoryBits = value;
        boxBreakFilter.groupIndex = value;
        boxBreakFilter.maskBits = value;
        fixture.setFilterData(boxBreakFilter);
    }

    private void jump() {
        setOnFloor(false);
        jumping = true;
        falling = false;
    }

    public Hotbar getHotbar() {
        return hotbar;
    }

    public ItemStack getItemInHand() {
        return hotbar.getItemInHand();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - sizeX) * PIXELS_IN_METER, (body.getPosition().y - sizeY) * PIXELS_IN_METER);
        if (jumping) {
            if (!falling && getY() < lastY) {//Si el jugador está empezando a caer...
                falling = true;
                countJumping = 7f;
            }

            countJumping += 0.3f;
            int frame = (int) countJumping;
            if (falling) {
                if (frame > 8) {
                    frame = 7;
                    countJumping = 7f;
                }
            } else {
                if (frame > 7) {
                    frame = 7;
                    countJumping = 7f;
                }
            }

            textReg = new TextureRegion(texture, frame * 19, 32, 19, 32);
            lastY = getY();
        } else if (isWalking()) {
            int frame = (int) countWalking;
            if (shift && frame > 8 || !shift && frame > 7) {
                frame = 1;
                countWalking = 1f;
                sound.play(SoundType.STEP);
            } else if (frame < 1) {
                countWalking = shift ? 9f : 8f;
                frame = 8;
                sound.play(SoundType.STEP);
            }
            textReg = new TextureRegion(texture, frame * 19, shift ? 64 : 0, 19, 32);
        } else {
            textReg = new TextureRegion(texture, 0, 0, 19, 32);
        }
        if (isMirandoIzquierda()) {
            textReg.flip(true, false);
        }
        batch.draw(textReg, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            body.applyLinearImpulse(0, PLAYER_JUMP_SPEED, body.getPosition().x, body.getPosition().y, true);
            jump();
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.setLinearVelocity(shift ? PLAYER_SPEED_SHIFT : PLAYER_SPEED, body.getLinearVelocity().y);
            setWalking(true, false);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.setLinearVelocity(shift ? -PLAYER_SPEED_SHIFT : -PLAYER_SPEED, body.getLinearVelocity().y);
            setWalking(true, true);
        } else {
            body.setLinearVelocity(body.getLinearVelocity().x / 2, body.getLinearVelocity().y);
            setWalking(false, false);
        }


    }

    public Body getBody() {
        return body;
    }

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking, boolean movingIzq) {
        if (walking) {
            if (isMirandoIzquierda()) {
                if (movingIzq) {
                    countWalking += 0.15f;
                } else {
                    countWalking -= 0.15f;
                }
            } else {
                if (movingIzq) {
                    countWalking -= 0.15f;
                } else {
                    countWalking += 0.15f;
                }
            }
        } else {
            countWalking = 0;
        }
        this.walking = walking;
    }

    public boolean isMirandoIzquierda() {
        return Gdx.input.getX() < 527;
    }

    public void setOnFloor(boolean onFloor) {
        if (onFloor) {
            jumping = false;
            countJumping = 1f;
            falling = false;
        }
        this.onFloor = onFloor;
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
