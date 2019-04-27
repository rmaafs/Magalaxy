package com.relmaps.magalaxy.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.paisaje.GrassPaisaje;
import com.relmaps.magalaxy.paisaje.TimerBackground;
import com.relmaps.magalaxy.screen.Pantalla;

public class PlanetGenerator extends Planet {

    public PlanetGenerator(double masa, double radio) {
        super(masa, radio);
    }

    private int coordX = 0, coordY;
    private float y, lastY = 0;

    private int BLOCKS_DEPTH = 50;

    public Planet generateBlocks(World world, Pantalla screen, Stage stage) {
        this.stage = stage;
        sun = new TimerBackground(screen.getRecurso("paisajes/sun.png"), stage, 3f);
        //paisaje = new GrassPaisaje(screen, stage);

        for (int i = 0; i < 1; i++) {
            float a = Constants.getRandFloat(1f, 10f);
            float b = Constants.getRandFloat(0.01f, 0.5f);
            //int repeticiones = Constants.getRand(1, 10);
            //generarFormula(5f, 0.05f, 0f, 1, world, screen);
            generarFormula(a, b, 0f, 1, world, screen);
        }
        System.out.println("Total de bloques: " + blocks.size());

        //generarFormula(9f, 0.1f, 0f, 1, world, screen);
        //generarFormula(2f, 0.2f, 0f, 3, world, screen);

        return this;
    }

    private void generarFormula(float a, float b, float d, int repeticionesCoord0, World world, Pantalla screen) {
        BlockType type;
        for (int reps = 0; true; coordX++) {
            y = new Float(Math.round(a * Math.sin(b * (coordX + d)) * 2) / 2.0);
            if (reps == 0 && y != 0.0f) {
                coordX--;
                d += 0.5f;
                continue;
            }
            for (coordY = 0; coordY < BLOCKS_DEPTH; coordY++) {
                if (coordY == 0) {
                    type = BlockType.DIRT_GRASS;
                } else if (coordY > 5) {
                    type = BlockType.COBBLESTONE;
                } else {
                    type = BlockType.DIRT;
                }
                addBlock(type, coordX, coordY, coordX * 0.5f, y - (coordY * 0.5f), world, screen);
            }

            if (y == 0.0f) {
                if (reps == 0 || y != lastY) {
                    reps++;
                }
                if (reps > repeticionesCoord0) {
                    lastY = y;
                    break;
                }
            }
            lastY = y;
        }
        //System.out.println("1 LastY=" + lastY + ", y=" + y);
    }

    private void addBlock(BlockType type, int coordX, int coordY, float x, float y, World world, Pantalla screen) {
        //if (coordX % 80 == 0) type = BlockType.GRAVEL;
        Block b = new Block(type, world, screen, new Vector2(x, y), stage);
        blocks.add(b);
        blocksPositions.put(coordX + "," + coordY, b);
        //System.out.println("Bloque puesto en " + coordX + ", " + coordY);
    }
}
