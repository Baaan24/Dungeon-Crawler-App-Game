package com.example.gamepart2.View;


public class RunningMovementStrategy implements MovementStrategy {
    public float moveUp(float y) {
        return y + 30;
    }
    public float moveDown(float y) {
        return y - 30;
    }
    public float moveLeft(float x) {
        return x - 30;
    }
    public float moveRight(float x) {
        return x + 30;
    }
}
