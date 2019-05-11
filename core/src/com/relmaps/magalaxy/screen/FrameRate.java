package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.relmaps.magalaxy.particles.ParticleAnimation;
import com.relmaps.magalaxy.world.Planet;

public class FrameRate implements Disposable {
    long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private World world;
    private Stage stage;
    private Planet planeta;


    public FrameRate(World w, Stage stage, Planet planeta) {
        world = w;
        this.stage = stage;
        this.planeta = planeta;
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        font = new BitmapFont();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if (sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    public void render() {
        batch.begin();
        font.draw(batch, (int) frameRate + " FPS\n" +
                        "JavaHeap: " + (Gdx.app.getJavaHeap() / 1024 / 1024) + " MB\n" +
                        "NativeHeap: " + (Gdx.app.getNativeHeap() / 1024 / 1024) + " MB\n" +
                        "Blocks: " + planeta.getTotalBlocks() + "\n" +
                        "Drops: " + planeta.getBlockDrops().size() + "\n" +
                        "Bodys: " + world.getBodyCount() + "\n" +
                        "Actores: " + stage.getActors().size + "\n" +
                        "Tiempo: " + String.format("%.1f", planeta.getTime()) + "\n" +
                        "Partículas: " + ParticleAnimation.particulas.size(),
                3, Gdx.graphics.getHeight() - 3);
        batch.end();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}