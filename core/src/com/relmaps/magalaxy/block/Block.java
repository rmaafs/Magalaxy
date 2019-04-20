package com.relmaps.magalaxy.block;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.relmaps.magalaxy.screen.Pantalla;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;

public class Block extends Actor {
    private BlockType type;
    private Texture texture;
    private Body body;
    private Fixture fixture;

    private float size = 0.25f;

    public Block(BlockType type, World world, Pantalla screen, Vector2 position){
        this.type = type;
        texture = getTexture(screen);

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size, size);
        fixture = body.createFixture(shape, 1);
        shape.dispose();

        setSize(PIXELS_IN_METER * size * 2, PIXELS_IN_METER * size * 2);
    }

    private Texture getTexture(Pantalla screen){
        switch (type){
            case DIRT:
                return screen.getRecurso("blocks/dirt.png");
        }
        return screen.getRecurso("blocks/dirt.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - size) * PIXELS_IN_METER,
                (body.getPosition().y - size) * PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
