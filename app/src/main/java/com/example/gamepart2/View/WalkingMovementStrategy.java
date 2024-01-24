package com.example.gamepart2.View;


public class WalkingMovementStrategy implements MovementStrategy {
    public float moveUp(float y) {
        return y + 10;
    }
    public float moveDown(float y) {
        return y - 10;
    }
    public float moveLeft(float x) {
        return x - 10;
    }
    public float moveRight(float x) {
        return x + 10;
    }
}
