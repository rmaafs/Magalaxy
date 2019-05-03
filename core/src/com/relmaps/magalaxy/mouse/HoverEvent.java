package com.relmaps.magalaxy.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.relmaps.magalaxy.block.Block;

public class HoverEvent extends ClickListener {

    private Block block;

    public HoverEvent(Block block) {
        this.block = block;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        block.setHoverMouse(true);
        if (Gdx.input.isTouched()) {
            block.setDiging(true);
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        block.setHoverMouse(false);
    }
}
