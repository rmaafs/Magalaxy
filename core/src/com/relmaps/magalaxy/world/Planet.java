package com.relmaps.magalaxy.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;
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
        gravity = new Float(G * (masa / pow(radio, 2)));
        System.out.print("Mundo creado: G=" + G + ", radio=" + radio + ", Gravedad:  " + gravity);
    }

    public void generateBlocks(World world, Pantalla screen){
        float y, a = 0.5f, b = 0.2f, d = 1.5f;

        for (int x = 2; x < 100; x++){
            y = new Float(Math.round(a * Math.sin(b*(x + d)) * 2) / 2.0);
            blocks.add(new Block(BlockType.DIRT, world, screen, new Vector2(x * 0.5f, y)));
        }

    }

    public void showBlocks(Stage stage){
        for (Block block : blocks){
            stage.addActor(block);
        }
    }
}
