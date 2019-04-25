package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.relmaps.magalaxy.screen.Pantalla;

import static com.relmaps.magalaxy.entity.Constants.PIXELS_IN_METER;

public class GrassPaisaje {
    private TextureRegion below1, below2, below3, far;

    public GrassPaisaje(Pantalla screen){
        this.below1 = new TextureRegion(screen.getRecurso("paisajes/grass/below1.png"), 0, 0, 128, 18);
        this.below2 = new TextureRegion(screen.getRecurso("paisajes/grass/below2.png"), 0, 18, 128, 39);
        this.below3 = new TextureRegion(screen.getRecurso("paisajes/grass/below3.png"), 0, 0, 128, 140);
        this.far = new TextureRegion(screen.getRecurso("paisajes/grass/far.png"), 0, 0, 480, 600);
    }

    public void draw(Batch batch, float x, float y){
        batch.begin();
        batch.draw(far, x - (PIXELS_IN_METER * 16) - 10 , (y / 2) - (PIXELS_IN_METER * 25), (1024 + 10) * 2, 640 * 4);
        batch.end();
    }
}
