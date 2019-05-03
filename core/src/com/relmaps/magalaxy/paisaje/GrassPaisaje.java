package com.relmaps.magalaxy.paisaje;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.screen.Pantalla;

import java.util.ArrayList;
import java.util.List;

public class GrassPaisaje extends Actor {

    private List<ParallaxBackground> backgrounds = new ArrayList<ParallaxBackground>();

    public GrassPaisaje(Pantalla screen, Stage stage) {

        backgrounds.add(new ParallaxBackground(screen.getRecurso("paisajes/grass/fondo.png"), stage, 100f, BackgroundType.MOUNTAIN));
        backgrounds.add(new ParallaxBackground(screen.getRecurso("paisajes/grass/fondo2.png"), stage, 100f, BackgroundType.MOUNTAIN));
        for (int i = 7; i >= 1; i--) {
            int arboles = Constants.getRand(1, 3);
            int r, tipoArbol;
            while (arboles-- > 0) {
                r = Constants.getRand(-800, 800);
                tipoArbol = Constants.getRand(1, 3);
                backgrounds.add(new ParallaxBackground(screen.getRecurso("paisajes/grass/treeback" + tipoArbol + ".png"), stage, i, r, BackgroundType.TREE));
            }

            backgrounds.add(new ParallaxBackground(screen.getRecurso("paisajes/grass/grass" + i + ".png"), stage, 2f, (5f * i), i * 0.06f, BackgroundType.GRASS));
        }

        for (ParallaxBackground b : backgrounds) {
            stage.addActor(b);
        }
    }

    public void limpiarActores() {
        for (ParallaxBackground b : backgrounds) {
            b.refresh();
        }
    }
}
