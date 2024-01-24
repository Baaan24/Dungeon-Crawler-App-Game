package com.example.gamepart2.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.gamepart2.R;

public class Enemy1 implements Enemy {
    private int speed;
    private int spriteResource;
    private float x, y;
    private int screenWidth, screenHeight;
    private Context context;
    private ImageView imageView;

    public Enemy1(int speed, float x, float y, int screenWidth, int screenHeight, Context context) {
        this.speed = speed;
        this.spriteResource = R.drawable.enemy1;
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.context = context;
        imageView = new ImageView(context);
        imageView.setImageResource(spriteResource);
        imageView.setX(x);
        imageView.setY(y);
    }

    @Override
    public void display(View view) {
        ImageView enemyView = (ImageView) view.findViewById(R.id.enemy1View);
        if (enemyView != null) {
            enemyView.setX(x);
            enemyView.setY(y);
            enemyView.setImageResource(spriteResource);
            this.imageView = enemyView;
        }
    }

    @Override
    public void move() {
        // Update the x coordinate to move from left to right
        x += speed;
        Log.d("Enemy1", "Moving. x: " + x + ", y: " + y);
        // Get the width of the enemy sprite
        int spriteWidth = 100;
        // Check if the enemy has reached the screen boundaries
        if (x + spriteWidth >= screenWidth || x <= 0) {
            // Change direction when reaching the left or right border
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

    private Bitmap getMapfromRes(Context context, int spriteResource) {
        Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, spriteResource);
    }

    public View getView() {
        return imageView;
    }
}
