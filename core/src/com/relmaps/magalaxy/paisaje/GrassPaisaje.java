package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.screen.Pantalla;

public class GrassPaisaje extends Actor {

    public GrassPaisaje(Pantalla screen, Stage stage) {
        stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/fondo.png"), 100f, BackgroundType.MOUNTAIN));
        stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/fondo2.png"), 100f, BackgroundType.MOUNTAIN));
        for (int i = 7; i >= 1; i--) {
            int arboles = Constants.getRand(1, 3);
            int r, tipoArbol;
            while (arboles-- > 0){
                r = Constants.getRand(-800, 800);
                tipoArbol = Constants.getRand(1, 3);
                stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));
            }
            /*switch (nivel){
                case 1:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
                case 2:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
                case 3:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
                case 4:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
                case 5:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
                case 6:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
                case 7:stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), i, r, BackgroundType.TREE));break;
            }*/
            stage.addActor(new ParallaxBackground(screen.getRecurso("paisajes/grass/grass" + i + ".png"), 2f, (5f * i), i * 0.06f, BackgroundType.GRASS));
        }
    }
}
