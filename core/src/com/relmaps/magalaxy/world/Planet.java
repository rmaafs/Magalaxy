package com.relmaps.magalaxy.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockDrop;
import com.relmaps.magalaxy.paisaje.GrassPaisaje;
import com.relmaps.magalaxy.paisaje.StarsBackground;
import com.relmaps.magalaxy.paisaje.TimerBackground;
import com.relmaps.magalaxy.particles.ParticleAnimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.pow;

public class Planet {

    protected List<Block> blocks = new ArrayList<Block>();
    protected HashMap<String, Block> blocksPositions = new HashMap<String, Block>();
    protected List<BlockDrop> blockDrops = new ArrayList<BlockDrop>();
    private float gravity;

    protected Stage stage;
    protected GrassPaisaje paisaje;
    protected TimerBackground sun;
    protected StarsBackground stars;

    public Planet(double masa, double radio) {
        double G = 6.67 * pow(10, -11);
        radio = radio * 1000;
        gravity = Float.valueOf((float) (G * (masa / pow(radio, 2))));
        System.out.println("Mundo creado: G=" + G + ", radio=" + radio + ", Gravedad:  " + gravity);
    }

    public void showBlocks(Stage stage) {
        for (Block block : blocks) {
            stage.addActor(block);
        }
    }

    public void limpiarActores() {
        stage.getBatch().begin();
        for (Block block : blocks) {
            block.refresh();
        }
        for (BlockDrop block : blockDrops) {
            block.refresh();
        }
        ParticleAnimation.refreshAnimations(stage.getBatch());
        stage.getBatch().end();
        paisaje.limpiarActores();
    }

    public float getTime() {
        return sun.getTime();
    }

    public void addTime(float time) {
        sun.addTime(time);
    }

    public float getGravity() {
        return gravity;
    }

    public int getTotalBlocks() {
        return blocks.size();
    }

    public List<BlockDrop> getBlockDrops() { return blockDrops; }

    public Block getBlockAt(String path) {
        return blocksPositions.get(path);
    }

    public void removeBlock(String positionPath) {
        Block b = blocksPositions.get(positionPath);
        blocksPositions.remove(positionPath);
        blocks.remove(b);
    }

    /*public void removeAirBlock(String positionPath) {
        int x = Integer.valueOf(positionPath.split(",")[0]);
        int y = Integer.valueOf(positionPath.split(",")[1]);

        if (checkBlocksAround(x - 1, y)){
            removeBlock((x - 1) + ","+ (y));
        }
        if (checkBlocksAround(x + 1, y)){
            removeBlock((x + 1) + ","+ (y));
        }
        if (checkBlocksAround(x, y - 1)){
            removeBlock((x) + ","+ (y - 1));
        }
        if (checkBlocksAround(x, y + 1)){
            removeBlock((x) + ","+ (y + 1));
        }

        if (checkBlocksAround(x - 1, y + 1)){
            //getBlockAt((x - 1) + "," + (y + 1)).setType(BlockType.EMERALD_ORE, "blocks/minerals/emerald_ore.png");
            getBlockAt((x - 1) + "," + (y + 1)).deleteBlock();
        }
    }

    public boolean checkBlocksAround(int x, int y){
        if (getBlockAt((x - 1) + "," + (y)) != null && getBlockAt((x - 1) + "," + (y)).getType() == BlockType.AIR){
            System.out.println("1");
            getBlockAt((x - 1) + "," + (y)).deleteBlock();
        }
        if (getBlockAt((x + 1) + "," + (y)) != null && getBlockAt((x + 1) + "," + (y)).getType() == BlockType.AIR){
            System.out.println("2");
            getBlockAt((x + 1) + "," + (y)).deleteBlock();
        }
        if (getBlockAt((x) + "," + (y - 1)) != null && getBlockAt((x) + "," + (y - 1)).getType() == BlockType.AIR){
            System.out.println("3");
            getBlockAt((x) + "," + (y - 1)).deleteBlock();
        }
        if (getBlockAt((x) + "," + (y + 1)) != null && getBlockAt((x) + "," + (y + 1)).getType() == BlockType.AIR){
            System.out.println("4");
            getBlockAt((x) + "," + (y + 1)).deleteBlock();
        }
        return true;
    }*/

    public void addBlockDrop(BlockDrop drop) {
        blockDrops.add(drop);
    }

    public void dibujarEstrellas(Batch b) {
        stars.dibujar(b);
    }

    public void addHologram(String msg, Vector2 position) {
        final Label label = new Label("msg", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        label.setColor(Color.RED);
        label.setPosition(position.x, position.y);
        /*label.addListener(
                new InputListener() {
                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        label.setColor(Color.WHITE);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        label.setColor(Color.BLACK);
                    }
                }
        );*/
        stage.addActor(label);
    }
}
