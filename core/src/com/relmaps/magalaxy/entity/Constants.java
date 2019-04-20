package com.relmaps.magalaxy.entity;

import java.util.Random;

public class Constants {
    public static final float PIXELS_IN_METER = 64f;
    public static final float PLAYER_SPEED = 5f;
    public static final float PLAYER_JUMP_SPEED = 30f;
    public static final float PLAYER_SPEED_SHIFT = 2f;

    public static int getRand(int min, int max){
        return new Random().nextInt((max - min) + 1) + min;
    }
}
