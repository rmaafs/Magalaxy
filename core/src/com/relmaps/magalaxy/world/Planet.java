package com.relmaps.magalaxy.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.paisaje.GrassPaisaje;
import com.relmaps.magalaxy.paisaje.TimerBackground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.pow;

public class Planet {

    protected List<Block> blocks = new ArrayList<Block>();
    protected HashMap<String, Block> blocksPositions = new HashMap<String, Block>();
    private float gravity;

    protected Stage stage;
    protected GrassPaisaje paisaje;
    protected TimerBackground sun;

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

    public void limpiarActores(){
        for (Block block : blocks) {
            block.refresh();
        }
        paisaje.limpiarActores();
    }

    public float getTime(){
        return sun.getTime();
    }

    public void addTime(float time){
        sun.addTime(time);
    }

    public float getGravity() {
        return gravity;
    }

    public int getTotalBlocks(){ return blocks.size(); }

    public void removeBlock(String positionPath){
        Block b = blocksPositions.get(positionPath);
        blocksPositions.remove(positionPath);
        blocks.remove(b);
    }

    public void addHologram(String msg, Vector2 position){
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
