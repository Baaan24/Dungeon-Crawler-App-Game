package com.example.gamepart2.View;

import android.graphics.Canvas;
import android.view.View;

import com.example.gamepart2.Models.Players;
import com.example.gamepart2.R;

public interface Enemy {
    void display(View view);
    void move();

    float getXpos();
    
    float getYpos();

    int getWidth();

    int getHeight();

    View getView();
}
