package com.relmaps.magalaxy.saves;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.relmaps.magalaxy.block.BlockType;
import com.relmaps.magalaxy.entity.Constants;
import com.relmaps.magalaxy.entity.ItemStack;
import com.relmaps.magalaxy.entity.Material;
import com.relmaps.magalaxy.screen.GameScreen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HotbarState implements Serializable {
    private HashMap<Integer, String> hotbar;

    public HotbarState() {
        hotbar = new HashMap<Integer, String>();
        int amount = 0;
        String name = "";
        Material type = Material.AIR;
        for (int i = 0; i < 9; i++) {
            if (GameScreen.player.getHotbar().getItemInSlot(i) == null) {
                amount = 0;
                type = Material.AIR;
            } else {
                type = GameScreen.player.getHotbar().getItemInSlot(i).getMaterial();
                amount = GameScreen.player.getHotbar().getItemInSlot(i).getAmount();
            }
            hotbar.put(amount, type.toString());
        }
    }

    public void set(GameScreen game) {
        int i = 0;
        TextureRegion texture;
        for (Map.Entry<Integer, String> entry : hotbar.entrySet()) {
            Integer amount = entry.getKey();
            BlockType type = BlockType.valueOf(entry.getValue());
            if (type != BlockType.AIR) {
                texture = getTexture(type, game);

                ItemStack item = new ItemStack(texture, type, amount);
                GameScreen.player.getHotbar().addItem(item);
            }
        }
    }

    private TextureRegion getTexture(BlockType type, GameScreen screen) {
        int r4 = Constants.getRand(0, 4), r;
        switch (type) {
            case DIRT:
                return new TextureRegion(screen.getRecurso("blocks/dirt.png"), r4 * 8, 0, 8, 8);
            case COBBLESTONE:
                return new TextureRegion(screen.getRecurso("blocks/cobblestone.png"), r4 * 8, 0, 8, 8);
            case GRAVEL:
                return new TextureRegion(screen.getRecurso("blocks/gravel.png"), r4 * 8, 0, 8, 8);
            default:
                return new TextureRegion(screen.getRecurso("blocks/dirt.png"), r4 * 8, 0, 8, 8);
        }
    }
}
