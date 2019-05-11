package com.relmaps.magalaxy.entity;

import java.util.Random;

public class Constants {
    public static final float PIXELS_IN_METER = 64f;
    public static final float PLAYER_SPEED = 5f;
    public static final float PLAYER_JUMP_SPEED = 30f;
    public static final float PLAYER_SPEED_SHIFT = 2f;
    public static final int PLAYER_VISIBILITY_X = 16;
    public static final int PLAYER_VISIBILITY_Y = 10;
    public static final float PLAYER_PICKUP_DROP_DISTANCE = 120;
    public static final float BLOCK_DROP_JUMP_SPEED = 0.5f;
    public static final float BLOCK_DROP_FLOATING_SPEED = 0.2f;

    public static int getRand(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static float getRandFloat(float min, float max) {
        return min + new Random().nextFloat() % (max - min);
    }
}
