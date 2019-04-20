package com.relmaps.magalaxy.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;
import com.relmaps.magalaxy.screen.Pantalla;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.pow;

public class Planet {

    protected List<Block> blocks = new ArrayList<Block>();
    protected HashMap<Vector2, Block> blocksPositions = new HashMap<Vector2, Block>();
    private float gravity;

    public Planet(double masa, double radio) {
        double G = 6.67 * pow(10, -11);
        radio = radio * 1000;
        gravity = new Float(G * (masa / pow(radio, 2)));
        System.out.println("Mundo creado: G=" + G + ", radio=" + radio + ", Gravedad:  " + gravity);
    }

    public void showBlocks(Stage stage) {
        for (Block block : blocks) {
            stage.addActor(block);
        }
    }
}
