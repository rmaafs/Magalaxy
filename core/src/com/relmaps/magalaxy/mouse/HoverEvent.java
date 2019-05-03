package com.relmaps.magalaxy.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;

public class HoverEvent extends ClickListener {

    private Block block;

    public HoverEvent(Block block){
        this.block = block;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        if (Gdx.input.isTouched()){
            block.setType(BlockType.AIR);
        } else {
            block.setHoverMouse(true);
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        block.setHoverMouse(false);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        block.setType(BlockType.AIR);
    }
}
