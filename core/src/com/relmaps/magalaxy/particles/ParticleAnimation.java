package com.relmaps.magalaxy.particles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.sounds.SoundType;

import java.util.ArrayList;
import java.util.List;

import static com.relmaps.magalaxy.InitGame.sound;

public class ParticleAnimation {

    public static List<ParticlePacket> particulas = new ArrayList<ParticlePacket>();

    public static void showParticle(Block block) {
        if (!block.isEffectAnimate()) {
            sound.play(SoundType.BLOCK_HIT);
            particulas.add(new ParticlePacket(block));
        }
    }

    public static void refreshAnimations(Batch batch) {
        List<ParticlePacket> basura = new ArrayList<ParticlePacket>();
        for (ParticlePacket particle : particulas) {
            if (particle.draw(batch)) {
                basura.add(particle);
            }
        }
        particulas.removeAll(basura);
    }
}
