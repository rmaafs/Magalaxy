package com.relmaps.magalaxy.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.relmaps.magalaxy.block.Block;
import com.relmaps.magalaxy.block.BlockType;
import com.relmaps.magalaxy.screen.GameScreen;

public class HoverEvent extends ClickListener {

    private Block block;

    public HoverEvent(Block block) {
        this.block = block;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        block.setHoverMouse(true);
        if (Gdx.input.isTouched()) {
            if (block.getType() != BlockType.AIR && block.isVisible()) {
                block.setDiging(true);
            } else if (GameScreen.player.getItemInHand() != null && block.getType() == BlockType.AIR) {
                block.setType(BlockType.valueOf(GameScreen.player.getItemInHand().getMaterial().toString()), GameScreen.player.getItemInHand().getTexture());
                GameScreen.player.getHotbar().removeItem(GameScreen.player.getItemInHand(), 1);
            }
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        block.setHoverMouse(false);
    }
}
