package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.screen.Pantalla;

public class GrassPaisaje extends Actor {

    private ParallaxBackground mountain, grass1, grass2;

    public GrassPaisaje(Pantalla screen, Stage stage){
        /*this.below1 = new TextureRegion(screen.getRecurso("paisajes/grass/below1.png"), 0, 0, 128, 18);
        this.below2 = new TextureRegion(screen.getRecurso("paisajes/grass/below2.png"), 0, 18, 128, 39);
        this.below3 = new TextureRegion(screen.getRecurso("paisajes/grass/below3.png"), 0, 0, 128, 140);
        this.far = new TextureRegion(screen.getRecurso("paisajes/grass/far.png"), 0, 0, 480, 600);*/

        mountain = new ParallaxBackground(screen.getRecurso("paisajes/grass/fondo.png"), 2, 100f, false);
        stage.addActor(mountain);
        for (int i = 7; i >= 1; i--){
            stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/grass" + i + ".png"), 1, 5f + (5f * i), true));
        }

        /*mountain = new ParallaxBackground(screen.getRecurso("paisajes/grass/fondo.png"), 2, 50f, false);
        grass1 = new ParallaxBackground(screen.getRecurso("paisajes/grass/grass1.png"), 1, 20f, true);
        grass2 = new ParallaxBackground(screen.getRecurso("paisajes/grass/grass2.png"), 1, 30f, true);*/

    }
}
