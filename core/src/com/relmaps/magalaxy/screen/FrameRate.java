package com.relmaps.magalaxy.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;

public class FrameRate implements Disposable {
    long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private World world;
    private Stage stage;


    public FrameRate(World w, Stage stage) {
        world = w;
        this.stage = stage;
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        font = new BitmapFont();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void resize(int screenWidth, int screenHeight) {
        cam = new OrthographicCamera(screenWidth, screenHeight);
        cam.translate(screenWidth / 2, screenHeight / 2);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }

    public void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if(sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    public void render() {
        batch.begin();
        font.draw(batch, (int)frameRate + " FPS\nJavaHeap: " +
                (Gdx.app.getJavaHeap() / 1024 / 1024 ) + " MB\nNativeHeap: " +
                (Gdx.app.getNativeHeap() / 1024 / 1024) + " MB\nBodys: " +
                world.getBodyCount() + "\nActores: " +
                stage.getActors().size, 3, Gdx.graphics.getHeight() - 3);
        batch.end();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}