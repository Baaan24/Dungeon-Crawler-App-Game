package com.example.gamepart2.View;

import com.example.gamepart2.Models.Players;

public interface MovementStrategy {
    float moveUp(float y);
    float moveDown(float y);
    float moveLeft(float x);
    float moveRight(float x);
}
