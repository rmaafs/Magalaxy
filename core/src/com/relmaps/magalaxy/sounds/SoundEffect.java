package com.relmaps.magalaxy.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundEffect {

    private HashMap<SoundType, Sound> sounds;

    public SoundEffect() {
        sounds = new HashMap<SoundType, Sound>();
        sounds.put(SoundType.MIRA, Gdx.audio.newSound(Gdx.files.internal("sounds/mira.ogg")));
        sounds.put(SoundType.STEP, Gdx.audio.newSound(Gdx.files.internal("sounds/step_dirt.wav")));
        sounds.put(SoundType.ITEM_PICKUP, Gdx.audio.newSound(Gdx.files.internal("sounds/item_pickup.wav")));
        sounds.put(SoundType.BLOCK_HIT, Gdx.audio.newSound(Gdx.files.internal("sounds/block_hit.wav")));
        sounds.put(SoundType.BLOCK_PLACE, Gdx.audio.newSound(Gdx.files.internal("sounds/block_place.wav")));
    }

    public void play(SoundType type) {
        sounds.get(type).play();
    }

    public void play(SoundType type, float volumen) {
        sounds.get(type).play(volumen);
    }
}
