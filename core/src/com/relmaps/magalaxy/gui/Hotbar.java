package com.relmaps.magalaxy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.relmaps.magalaxy.entity.ItemStack;
import com.relmaps.magalaxy.mouse.ScrollEvent;
import com.relmaps.magalaxy.screen.GameScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hotbar extends Actor {

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private ScrollEvent scroll;

    private TextureRegion hotbar;
    private TextureRegion itemSelect;

    private HashMap<Integer, ItemStack> items;
    private List<Label> labels;
    private int currentItem = 0;

    private int sizeItemSelect;

    public Hotbar(GameScreen screen) {
        Texture texture = screen.getRecurso("gui/hotbar.png");
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        scroll = new ScrollEvent(this);
        hotbar = new TextureRegion(texture, 0, 0, 182, 22);
        itemSelect = new TextureRegion(texture, 182, 0, 24, 24);

        sizeItemSelect = 24 * 2;
        setSize(hotbar.getRegionWidth() * 2, hotbar.getRegionHeight() * 2);

        items = new HashMap<Integer, ItemStack>();
        labels = new ArrayList<Label>();
        for (int i = 0; i < 9; i++) {
            Label label = new Label("" + i, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            label.setAlignment(Align.right);
            label.setPosition(Gdx.graphics.getHeight() + ItemStack.size + (i * 20 * 2) + ItemStack.size - ItemStack.size / 3, Gdx.graphics.getHeight() - ItemStack.size - 17);
            labels.add(label);
        }
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

    public void addItem(ItemStack item) {
        int it = containsItemStack(item);
        if (it != -1) {
            items.get(it).setAmount(items.get(it).getAmount() + item.getAmount());
            System.out.println("Agregue en slot " + it + ") " + item.getMaterial() + ", amount: " + items.get(it).getAmount());
        } else {
            for (int i = 0; i < 9; i++) {
                if (items.get(i) == null) {
                    items.put(i, item);
                    break;
                }
            }
        }
    }

    public void removeItem(ItemStack item, int amount) {
        int it = containsItemStack(item);
        if (it != -1) {
            items.get(it).setAmount(items.get(it).getAmount() - amount);
            if (items.get(it).getAmount() == 0) {
                items.remove(it);
            }
        }
    }

    private int containsItemStack(ItemStack item) {
        for (int i = 0; i < 9; i++) {
            if (items.get(i) != null && items.get(i).getMaterial() == item.getMaterial()) {
                return i;
            }
        }
        return -1;
    }

    public ItemStack getItemInHand() {
        return items.get(currentItem);
    }

    public ItemStack getItemInSlot(int slot) {
        return items.get(slot);
    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        batch.begin();
        batch.draw(hotbar, Gdx.graphics.getHeight() + 15, Gdx.graphics.getHeight() - getHeight() - 5, getWidth(), getHeight());

        for (int i = 0; i < 9; i++) {
            if (items.get(i) != null) {
                batch.draw(items.get(i).getTexture(), Gdx.graphics.getHeight() + ItemStack.size + (i * 20 * 2) - 2, Gdx.graphics.getHeight() - ItemStack.size - 14, ItemStack.size, ItemStack.size);
                labels.get(i).setText("" + items.get(i).getAmount());
                if (items.get(i).getAmount() > 1) labels.get(i).draw(batch, 1);
            }
        }

        batch.draw(itemSelect, Gdx.graphics.getHeight() + 13 + (currentItem * 20 * 2), Gdx.graphics.getHeight() - sizeItemSelect - 3, sizeItemSelect, sizeItemSelect);
        batch.end();
    }
}
