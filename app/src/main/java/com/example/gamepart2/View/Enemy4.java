package com.example.gamepart2.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;

import com.example.gamepart2.R;

public class Enemy4 implements Enemy {
    private int screenHeight;
    private int speed;
    private int spriteResource;
    private float x, y;
    private int screenWidth;
    private Context context;
    private boolean movingRight = true;
    private boolean movingDown = true;
    public Enemy4(int speed, float x, float y, int screenWidth, int screenHeight, Context context) {
        this.speed = speed;
        this.spriteResource = R.drawable.enemy4;
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.context = context;
    }

    @Override
    public void display(View view) {
        ImageView enemyView = view.findViewById(R.id.enemy4View);
        enemyView.setX(x); // Set X position
        enemyView.setY(y); // Set Y position
        enemyView.setImageResource(R.drawable.enemy4);
    }

    @Override
    public void move() {
        // Move side to side
        if (movingRight) {
            x += speed; // Move right
            if (x + speed > screenWidth) {
                x = screenWidth - speed;
                movingRight = false;
            }
        } else {
            x -= speed; // Move left
            if (x - speed < 0) {
                x = 0;
                movingRight = true;
            }
        }

        // Move up and down
        if (movingDown) {
            y += speed; // Move down
            if (y + speed > screenHeight) {
                y = screenHeight - speed;
                movingDown = false;
            }
        } else {
            y -= speed; // Move up
            if (y - speed < 0) {
                y = 0;
                movingDown = true;
            }
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