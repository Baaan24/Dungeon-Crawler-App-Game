package com.example.gamepart2.View;

import android.content.Context;

public class Enemy4Factory implements EnemyFactory{
    private int speed;
    private Context context;

    private int screenWidth, screenHeight;

    public Enemy4Factory(int speed, int screenWidth, int screenHeight, Context context) {
        this.speed = speed;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.context = context;
    }

    @Override
    public Enemy createEnemy(int x, int y) {
        return new Enemy4(speed, x, y, screenWidth, screenHeight, context);
    }
}