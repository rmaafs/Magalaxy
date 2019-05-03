package com.relmaps.magalaxy.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.mouse.HoverEvent;
import com.relmaps.magalaxy.screen.GameScreen;
import com.relmaps.magalaxy.screen.Pantalla;
import com.relmaps.magalaxy.world.Planet;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_VISIBILITY_X;
import static com.relmaps.magalaxy.entity.Constants.PLAYER_VISIBILITY_Y;

public class Block extends Actor {
    public static TextureRegion hoverMouseTexture;
    public static Texture breakingTexture;

    private BlockType type;
    private TextureRegion texture;
    private TextureRegion regionSobre = null;
    private int regionHeightSize = 1;
    private Body body;
    private Fixture fixture;
    private World world;
    private BodyDef def;
    private PolygonShape shape;
    private HoverEvent hoverEvent;
    private ParticleEffect particleDiging;

    private Stage stage;

    private Planet planet;
    private String positionPath;

    private boolean hoverMouse = false;
    private boolean diging = false;
    private float timeDiging = 0.0f;

    private float size = 0.25f;

    public Block(BlockType type, World world, Pantalla screen, Vector2 position, String positionPath, Stage stage, Planet planet) {
        this.type = type;
        texture = getTexture(screen);
        this.world = world;
        this.positionPath = positionPath;
        this.stage = stage;
        this.planet = planet;

        def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;

        shape = new PolygonShape();
        shape.setAsBox(size, size);

        hoverEvent = new HoverEvent(this);
        particleDiging = new ParticleEffect();
        particleDiging.load(Gdx.files.internal("blocks/particles/dig/dirt.particle"), Gdx.files.internal("blocks/particles/"));

        activar();

        setSize(PIXELS_IN_METER * size * 2, PIXELS_IN_METER * size * 2);
        setPosition((body.getPosition().x - size) * PIXELS_IN_METER, (body.getPosition().y - size) * PIXELS_IN_METER);
    }

    public void activar() {
        body = world.createBody(def);
        fixture = body.createFixture(shape, 1);
        enableLightShadow();
        stage.addActor(this);
        this.addListener(hoverEvent);
        this.setVisible(true);
    }

    public void desactivar() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
        this.remove();
        this.removeListener(hoverEvent);
        this.setVisible(false);
    }

    private void enableLightShadow() {
        Filter boxBreakFilter = new Filter();
        boxBreakFilter.categoryBits = 32;
        fixture.setFilterData(boxBreakFilter);
    }

    private void disableColission() {
        Filter boxBreakFilter = new Filter();
        boxBreakFilter.categoryBits = 1;
        boxBreakFilter.groupIndex = 2;
        boxBreakFilter.maskBits = (short) 0;
        fixture.setFilterData(boxBreakFilter);
    }

    private TextureRegion getTexture(Pantalla screen) {
        int r4 = Constants.getRand(0, 4), r;
        int r7 = Constants.getRand(0, 7);

        if (isMineral()) {
            switch (type) {
                case COAL_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/coal_ore.png"), r7 * 8, 0, 8, 8);
                    break;
                case COPPER_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/copper_ore.png"), r7 * 8, 0, 8, 8);
                    break;
                case IRON_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/iron_ore.png"), r7 * 8, 0, 8, 8);
                    break;
                case GOLD_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/gold_ore.png"), r7 * 8, 0, 8, 8);
                    break;
                case DIAMOND_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/diamond_ore.png"), r7 * 8, 0, 8, 8);
                    break;
                case EMERALD_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/emerald_ore.png"), r7 * 8, 0, 8, 8);
                    break;
                case RUBY_ORE:
                    regionSobre = new TextureRegion(screen.getRecurso("blocks/minerals/ruby_ore.png"), r7 * 8, 0, 8, 8);
                    break;
            }
            return new TextureRegion(screen.getRecurso("blocks/cobblestone.png"), r4 * 8, 0, 8, 8);
        }

        switch (type) {
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

    public boolean isMineral() {
        return type == BlockType.COAL_ORE || type == BlockType.COPPER_ORE
                || type == BlockType.IRON_ORE || type == BlockType.GOLD_ORE
                || type == BlockType.DIAMOND_ORE || type == BlockType.EMERALD_ORE
                || type == BlockType.RUBY_ORE;
    }

    public void setHoverMouse(boolean hoverMouse) {
        this.hoverMouse = hoverMouse;
    }

    public boolean getHoverMouse() {
        return hoverMouse;
    }

    public void setDiging(boolean diging) {
        this.diging = diging;
        if (diging) particleDiging.start();
    }

    public void addDiging() {
        if (diging) {
            if (timeDiging < 3) {
                timeDiging += 0.05f;
            } else {
                blockBreak();
            }
        } else {
            if (timeDiging > 0) {
                timeDiging -= 0.1f;
            }
        }
    }

    public void setType(BlockType t) {
        if (t == BlockType.AIR) {
            desactivar();
            planet.removeBlock(positionPath);
        }
    }

    public void blockBreak() {
        setType(BlockType.AIR);
        stage.getBatch().draw(new TextureRegion(texture, 0, 0, 1, 1), getX(), getY(), 1, 1);
    }

    public boolean estaEnRangoDeVision() {
        return (getX() > GameScreen.player.getX() - (PLAYER_VISIBILITY_X + 1) * PIXELS_IN_METER && getX() < GameScreen.player.getX() + PLAYER_VISIBILITY_X * PIXELS_IN_METER)
                && (getY() > GameScreen.player.getY() - (PLAYER_VISIBILITY_Y + 1) * PIXELS_IN_METER && getY() < GameScreen.player.getY() + PLAYER_VISIBILITY_Y * PIXELS_IN_METER);
    }

    public void refresh() {
        if (estaEnRangoDeVision()) {

            if (hoverEvent.isPressed() && !diging) {
                setDiging(true);
            } else if (!hoverEvent.isPressed() && (!hoverMouse && diging)) {
                setDiging(false);
            }

            if (!isAlive()) {
                activar();
            }
        } else if (isAlive()) {
            desactivar();
        }
    }

    public boolean isAlive() {
        return this.isVisible();
    }

    public void drawParticles(Batch batch) {
        if (diging || timeDiging > 0) {
            particleDiging.getEmitters().first().setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
            particleDiging.update(Gdx.graphics.getDeltaTime());
            particleDiging.draw(batch);
            if (particleDiging.isComplete()) {
                particleDiging.reset();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        addDiging();

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        if (regionSobre != null) {
            batch.draw(regionSobre, getX(), getY(), getWidth(), getHeight() * regionHeightSize);
        }
        if (getHoverMouse()) {
            batch.draw(hoverMouseTexture, getX(), getY(), getWidth(), getHeight());
        }
        if (diging || timeDiging > 0) {
            int digX = (int) timeDiging;
            batch.draw(new TextureRegion(breakingTexture, digX * 8, 0, 8, 8), getX(), getY(), getWidth(), getHeight());
        }
    }
}
