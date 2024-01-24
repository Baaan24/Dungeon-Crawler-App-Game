package com.example.gamepart2.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;

import com.example.gamepart2.R;

public class Enemy2 implements Enemy {
    private int screenHeight;
    private int speed;
    private int spriteResource;
    private float x, y;
    private int screenWidth;
    private Context context;

    public Enemy2(int speed, float x, float y, int screenWidth, int screenHeight, Context context) {
        this.speed = speed;
        this.spriteResource = R.drawable.enemy2;
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.context = context;
    }

    @Override
    public void display(View view) {
        ImageView enemyView = view.findViewById(R.id.enemy2View);
        enemyView.setX(x); // Set X position
        enemyView.setY(y); // Set Y position
        enemyView.setImageResource(R.drawable.enemy2);
    }

    @Override
    public void move() {
        y += speed;

        // Get the height of the enemy sprite
        int spriteHeight = 100;

        // Check if the enemy has reached the screen boundaries vertically
        if (y + spriteHeight >= screenHeight || y <= 0) {
            // Change direction when reaching the top or bottom border
            speed = -speed;
        }
    }

    public float getXpos(){
        return x;
    }

    public float getYpos(){
        return y;
    }

    public int getWidth(){
        return getMapfromRes(context, spriteResource).getWidth();
    }

    public int getHeight(){
        return getMapfromRes(context, spriteResource).getHeight();
    }

    @Override
    public View getView() {
        return null;
    }

    private Bitmap getMapfromRes(Context context, int spriteResource) {
        Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, spriteResource);
    }
}
