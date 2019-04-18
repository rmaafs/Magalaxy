package com.relmaps.magalaxy.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.screen.Pantalla;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Planet {

    private List<Block> blocks = new ArrayList<Block>();
    private float gravity;

    public Planet(double masa, double radio){
        double G = 6.67 * pow(10, -11);
        radio = radio * 1000;
        gravity = new BigDecimal(G * (masa / pow(radio, 2))).floatValue();
        System.out.print("Mundo creado: G=" + G + ", radio=" + radio + ", Gravedad:  " + gravity);
    }

    public void generateBlocks(World world, Pantalla screen){
        for (float i = 1f; i < 20f; i+=0.5f){
            blocks.add(new Block(Block.BlockType.DIRT, world, screen, new Vector2(i, 1)));
        }
        blocks.add(new Block(Block.BlockType.DIRT, world, screen, new Vector2(4f, 1.5f)));
        blocks.add(new Block(Block.BlockType.DIRT, world, screen, new Vector2(4.5f, 2f)));
        blocks.add(new Block(Block.BlockType.DIRT, world, screen, new Vector2(5f, 2.5f)));
    }

    public void showBlocks(Stage stage){
        for (Block block : blocks){
            stage.addActor(block);
        }
    }
}
