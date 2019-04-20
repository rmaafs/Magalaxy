package com.relmaps.magalaxy.block;

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
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.screen.Pantalla;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;

public class Block extends Actor {
    private BlockType type;
    private TextureRegion texture;
    private TextureRegion regionSobre = null;
    private int regionHeightSize = 1;
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

    private void disableColission(){
        Filter boxBreakFilter = new Filter();
        boxBreakFilter.categoryBits = 1;
        boxBreakFilter.groupIndex = 2;
        boxBreakFilter.maskBits = (short)0;
        fixture.setFilterData(boxBreakFilter);
    }

    private TextureRegion getTexture(Pantalla screen){
        int r4 = Constants.getRand(0, 4), r;
        int r7 = Constants.getRand(0, 7);

        if (isMineral()){
            switch (type){
                case COAL_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/coal_ore.png"), r7 * 8, 0, 8, 8);break;
                case COPPER_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/copper_ore.png"), r7 * 8, 0, 8, 8);break;
                case IRON_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/iron_ore.png"), r7 * 8, 0, 8, 8);break;
                case GOLD_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/gold_ore.png"), r7 * 8, 0, 8, 8);break;
                case DIAMOND_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/diamond_ore.png"), r7 * 8, 0, 8, 8);break;
                case EMERALD_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/emerald_ore.png"), r7 * 8, 0, 8, 8);break;
                case RUBY_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/ruby_ore.png"), r7 * 8, 0, 8, 8);break;
            }
            return new TextureRegion(screen.getRecurso("blocks/cobblestone.png"), r4 * 8, 0, 8, 8);
        }

        switch (type){
            case DIRT:
                return new TextureRegion(screen.getRecurso("blocks/dirt.png"), r4 * 8, 0, 8, 8);
            case DIRT_GRASS:
                r = Constants.getRand(0, 4);
                regionSobre = new TextureRegion(screen.getRecurso("blocks/grass.png"), r * 8, 0, 8, 16);
                regionHeightSize = 2;
                return new TextureRegion(screen.getRecurso("blocks/dirt.png"), r4 * 8, 0, 8, 8);
            case COBBLESTONE:
                return new TextureRegion(screen.getRecurso("blocks/cobblestone.png"), r4 * 8, 0, 8, 8);
            case GRAVEL:
                return new TextureRegion(screen.getRecurso("blocks/gravel.png"), r4 * 8, 0, 8, 8);
            default:
                return new TextureRegion(screen.getRecurso("blocks/dirt.png"), r4 * 8, 0, 8, 8);
        }
    }

    public boolean isMineral(){
        return type == BlockType.COAL_ORE || type == BlockType.COPPER_ORE
                || type == BlockType.IRON_ORE || type == BlockType.GOLD_ORE
                || type == BlockType.DIAMOND_ORE || type == BlockType.EMERALD_ORE
                || type == BlockType.RUBY_ORE;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - size) * PIXELS_IN_METER,(body.getPosition().y - size) * PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        if (regionSobre != null){
            batch.draw(regionSobre, getX(), getY(), getWidth(), getHeight() * regionHeightSize);
        }
    }
}
