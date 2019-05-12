package com.relmaps.magalaxy.mouse;

import com.relmaps.magalaxy.gui.Hotbar;
import com.relmaps.magalaxy.input.InputGame;

public class ScrollEvent extends InputGame {

    private Hotbar hotbar;
    private char hotbarCasillas[] = "123456789".toCharArray();

    public ScrollEvent(Hotbar hotbar) {
        this.hotbar = hotbar;
    }

    @Override
    public boolean scrolled(int amount) {
        hotbar.scroll(amount == 1);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (contains(hotbarCasillas, character)) {
            int key = ((int) character) - 49;
            hotbar.setScroll(key);
        }
        return false;
    }

    private boolean contains(char array[], char c) {
        for (char letra : array) {
            if (letra == c) return true;
        }
        return false;
    }
}
