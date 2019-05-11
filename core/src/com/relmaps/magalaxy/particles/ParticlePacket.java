package com.relmaps.magalaxy.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.relmaps.magalaxy.block.Block;

public class ParticlePacket {
    private ParticleEffect particle;
    private Block block;

    public ParticlePacket(Block block) {
        this.block = block;

        particle = new ParticleEffect();
        particle.load(Gdx.files.internal("blocks/particles/dig/dirt.particle"), Gdx.files.internal("blocks/particles/"));
        particle.getEmitters().first().setPosition(block.getX() + block.getWidth() / 2, block.getY() + block.getHeight() / 2);
        particle.start();
        block.setEffectAnimate(true);
    }

    public boolean draw(Batch batch) {
        particle.update(Gdx.graphics.getDeltaTime());
        particle.draw(batch);
        if (particle.isComplete()) {
            block.setEffectAnimate(false);
            return true;
        }
        return false;
    }
}
