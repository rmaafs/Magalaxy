package com.relmaps.magalaxy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.relmaps.magalaxy.entity.ItemStack;
import com.relmaps.magalaxy.mouse.ScrollEvent;

import java.util.HashMap;

public class Hotbar extends Actor {

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private ScrollEvent scroll;

    private TextureRegion hotbar;
    private TextureRegion itemSelect;

    private HashMap<Integer, ItemStack> items;
    private int currentItem = 0;

    private int sizeItemSelect;

    public Hotbar(Texture texture) {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        scroll = new ScrollEvent(this);
        hotbar = new TextureRegion(texture, 0, 0, 182, 22);
        itemSelect = new TextureRegion(texture, 182, 0, 24, 24);
        sizeItemSelect = 24 * 2;
        setSize(hotbar.getRegionWidth() * 2, hotbar.getRegionHeight() * 2);

        items = new HashMap<Integer, ItemStack>();
    }

    public void scroll(boolean derecha) {
        if (derecha) {
            if (++currentItem > 8) currentItem = 0;
        } else {
            if (--currentItem < 0) currentItem = 8;
        }
    }

    public void setScroll(int i) {
        currentItem = i;
    }

    public void addItem(ItemStack item){
        int it = containsItemStack(item);
        if (it != -1) {
            items.get(it).setAmount(items.get(it).getAmount() + item.getAmount());
            System.out.println("Agregue en slot " + it + ") " + item.getMaterial() + ", amount: " + items.get(it).getAmount());
        } else {
            for (int i = 0; i < 9; i++){
                if (items.get(i) == null) {
                    items.put(i, item);
                    break;
                }
            }
        }
    }

    private int containsItemStack(ItemStack item){
        for (int i = 0; i < 9; i++){
            if (items.get(i) != null && items.get(i).getMaterial() == item.getMaterial()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        batch.begin();
        batch.draw(hotbar, Gdx.graphics.getHeight() + 15, Gdx.graphics.getHeight() - getHeight() - 5, getWidth(), getHeight());

        for (int i = 0; i < 9; i++){
            if (items.get(i) != null) {
                batch.draw(items.get(i).getTexture(), Gdx.graphics.getHeight() + ItemStack.size + (i * 20 * 2) - 2, Gdx.graphics.getHeight() - ItemStack.size - 14, ItemStack.size, ItemStack.size);
            }
        }

        batch.draw(itemSelect, Gdx.graphics.getHeight() + 13 + (currentItem * 20 * 2), Gdx.graphics.getHeight() - sizeItemSelect - 3, sizeItemSelect, sizeItemSelect);
        batch.end();
    }
}
