package com.relmaps.magalaxy.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.paisaje.GrassPaisaje;
import com.relmaps.magalaxy.paisaje.StarsBackground;
import com.relmaps.magalaxy.paisaje.SunBackground;
import com.relmaps.magalaxy.screen.GameScreen;
import com.relmaps.magalaxy.screen.Pantalla;

import java.util.ArrayList;
import java.util.List;

public class PlanetGenerator extends Planet {

    public PlanetGenerator(double masa, double radio) {
        super(masa, radio);
    }

    private int coordX = 0, coordY;
    private float y, lastY = 0;

    private int BLOCKS_DEPTH = 10;

    public Planet generateBlocks(World world, Pantalla screen, Stage stage) {
        this.stage = stage;
        stars = new StarsBackground(screen.getRecurso("paisajes/star.png"), stage, 3f);
        sun = new SunBackground(screen.getRecurso("paisajes/sun.png"), stage, 3f);
        paisaje = new GrassPaisaje(screen, stage);

        for (int i = 0; i < 10; i++) {
            float a = Constants.getRandFloat(1f, 10f);
            float b = Constants.getRandFloat(0.01f, 0.5f);
            //int repeticiones = Constants.getRand(1, 10);
            //generarFormula(5f, 0.05f, 0f, 1, world, screen);
            generarFormula(a, b, 0f, 1, world, screen);
        }
        ponerBloquesAire(world, screen);

        Block.hoverMouseTexture = new TextureRegion(screen.getRecurso("blocks/hovermouse.png"), 0, 0, 8, 8);
        Block.breakingTexture = screen.getRecurso("blocks/breaking.png");

        System.out.println("Total de bloques: " + blocks.size());

        //generarFormula(9f, 0.1f, 0f, 1, world, screen);
        //generarFormula(2f, 0.2f, 0f, 3, world, screen);

        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {

                Body fixtureA = contact.getFixtureA().getBody();
                Body fixtureB = contact.getFixtureB().getBody();

                if ((fixtureA.getUserData() == Block.userData && fixtureB.getUserData() == GameScreen.player.getBody().getUserData()) || (fixtureA.getUserData() == GameScreen.player.getBody().getUserData() && fixtureB.getUserData() == Block.userData)) {
                    //System.out.println("COLISIONANDO.");
                    GameScreen.player.setOnFloor(true);
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

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
                    //System.out.println("Puesto en: x=" + coordX * 0.5f + ", y=" + (y - (coordY * 0.5f)));
                    type = BlockType.DIRT_GRASS;
                } else if (coordY > 5) {
                    type = BlockType.COBBLESTONE;
                } else {
                    type = BlockType.DIRT;
                }
                addBlock(type, coordX * 0.5f, y - (coordY * 0.5f), world, screen);
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
    }

    private void addBlock(BlockType type, float x, float y, World world, Pantalla screen) {
        String positionPath = ((int) (x / 0.5)) + "," + ((int) (y / 0.5));
        Block b = new Block(type, world, screen, new Vector2(x, y), positionPath, stage, this);
        blocks.add(b);
        blocksPositions.put(positionPath, b);
        //System.out.println("Bloque puesto en " + positionPath + " (" + x + ", " + y + ")");
    }

    private void ponerBloquesAire(World world, Pantalla screen) {
        List<Block> agregar = new ArrayList<Block>();
        for (Block b : blocks) {
            revisarBlock(agregar, b, 1, 0, world, screen);
            revisarBlock(agregar, b, -1, 0, world, screen);
            revisarBlock(agregar, b, 0, 1, world, screen);
            revisarBlock(agregar, b, 0, -1, world, screen);

            revisarBlock(agregar, b, 1, 1, world, screen);
            revisarBlock(agregar, b, -1, -1, world, screen);
            revisarBlock(agregar, b, -1, 1, world, screen);
            revisarBlock(agregar, b, 1, -1, world, screen);

            for (int i = 2; i <= 15; i++) {
                revisarBlock(agregar, b, 0, i, world, screen);
            }
        }

        for (Block b : agregar) {
            blocks.add(b);
        }
    }

    private void revisarBlock(List<Block> agregar, Block b, int coordx, int coordy, World world, Pantalla screen) {
        int x = Integer.valueOf(b.getPositionPath().split(",")[0]);
        int y = Integer.valueOf(b.getPositionPath().split(",")[1]);
        if (getBlockAt((x + coordx) + "," + (y + coordy)) == null) {
            Vector2 position = b.getPosition();
            position.x += coordx * 0.5f;
            position.y += coordy * 0.5f;
            //addBlock(BlockType.AIR, (x + coordx), (y + coordy), position.x, position.y, world, screen);

            String positionPath = (x + coordx) + "," + (y + coordy);
            Block newb = new Block(BlockType.AIR, world, screen, new Vector2(position.x, position.y), positionPath, stage, this);
            agregar.add(newb);
            blocksPositions.put(positionPath, newb);

            //Validar que hacer cuando se cree un bloque tipo aire en Block()
        }
    }
}
