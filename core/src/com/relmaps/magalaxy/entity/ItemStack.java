package com.relmaps.magalaxy.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.relmaps.magalaxy.block.BlockType;

public class ItemStack {
    public static float size = 26f;
    private TextureRegion texture;
    private Material material;
    private int amount;

    public ItemStack(TextureRegion texture, BlockType material, int amount) {
        this.texture = texture;
        this.material = Material.valueOf(material.toString());
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
