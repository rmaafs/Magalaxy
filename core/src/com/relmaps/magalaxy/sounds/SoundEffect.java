package com.relmaps.magalaxy.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundEffect {

    private HashMap<SoundType, Sound> sounds;

    public SoundEffect() {
        sounds = new HashMap<SoundType, Sound>();
        loadSound(SoundType.MIRA, "sounds/mira.ogg");
        loadSound(SoundType.STEP, "sounds/step_dirt.wav");
        loadSound(SoundType.ITEM_PICKUP, "sounds/item_pickup.wav");
        loadSound(SoundType.BLOCK_HIT, "sounds/block_hit.wav");
        loadSound(SoundType.BLOCK_PLACE, "sounds/block_place.wav");
    }

    public SoundEffect(boolean intro) {
        sounds = new HashMap<SoundType, Sound>();
        loadSound(SoundType.INTRO, "intro/intro.mp3");
    }

    public void loadSound(final SoundType type, final String path) {
        new Thread() {
            @Override
            public void run() {
                sounds.put(type, Gdx.audio.newSound(Gdx.files.internal(path)));
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }.start();
    }

    public void play(SoundType type) {
        sounds.get(type).play();
    }

    public void play(SoundType type, float volumen) {
        sounds.get(type).play(volumen);
    }

    public void dispose() {
        sounds.clear();
    }
}
