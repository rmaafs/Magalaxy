package com.relmaps.magalaxy.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;
import com.relmaps.magalaxy.screen.Pantalla;

public class PlanetGenerator extends Planet {

    public PlanetGenerator(double masa, double radio) {
        super(masa, radio);
    }

    public Planet generateBlocks(World world, Pantalla screen) {
        float y, a = 0.5f, b = 0.2f, d = 1.5f;

        for (int coordX = 0; coordX < 50; coordX++) {
            y = new Float(Math.round(a * Math.sin(b * (coordX + d)) * 2) / 2.0);

            for (int coordY = 0; coordY < 20; coordY++) {
                BlockType type = BlockType.DIRT;
                if (coordY == 0) {
                    type = BlockType.DIRT_GRASS;
                } else if (coordY > 11){
                    type = BlockType.GRAVEL;
                } else if (coordY > 10){
                    type = BlockType.RUBY_ORE;
                } else if (coordY > 9){
                    type = BlockType.EMERALD_ORE;
                } else if (coordY > 8){
                    type = BlockType.DIAMOND_ORE;
                } else if (coordY > 7){
                    type = BlockType.GOLD_ORE;
                } else if (coordY > 6){
                    type = BlockType.IRON_ORE;
                } else if (coordY > 5){
                    type = BlockType.COPPER_ORE;
                } else if (coordY > 4){
                    type = BlockType.COAL_ORE;
                } else if (coordY > 3){
                    type = BlockType.COBBLESTONE;
                }


                addBlock(type, coordX, coordY, coordX * 0.5f, y - (coordY * 0.5f), world, screen);
            }
        }

        return this;
    }

    private void addBlock(BlockType type, int coordX, int coordY, float x, float y, World world, Pantalla screen) {
        Block b = new Block(type, world, screen, new Vector2(x, y));
        blocks.add(b);
        blocksPositions.put(new Vector2(coordX, coordY), b);
        //System.out.println("Bloque puesto en " + coordX + ", " + coordY);
    }
}
