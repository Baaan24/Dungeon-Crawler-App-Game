package com.example.gamepart2.Models;

import android.graphics.drawable.Drawable;

import com.example.gamepart2.View.MovementStrategy;
import com.example.gamepart2.View.WalkingMovementStrategy;

public class Players {
    private double x, y;
    private MovementStrategy movementStrategy;
    private String name;
    private int health;
    private Drawable sprite;
    private int spriteNum;
    private static volatile Players player;
    private Players() {
        this.name = name;
        this.health = health;
        this.sprite = sprite;
    }
    public Players(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
    }
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
    public static Players getPlayer() {
        if (player == null) {
            synchronized (Players.class) {
                if (player == null) {
                    player = new Players();
                }
            }
        }
        return player;
    }
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    public Drawable getSprite() {
        return sprite;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void setSprite(Drawable sprite) {
        this.sprite = sprite;
    }

    public float playerMoveUp(float y) {
        return movementStrategy.moveUp(y);
    }
    public float playerMoveDown(float y) {
        return movementStrategy.moveDown(y);
    }
    public float playerMoveLeft(float x) {
        return movementStrategy.moveLeft(x);
    }
    public float playerMoveRight(float x) {
        return movementStrategy.moveRight(x);
    }

    public int getWidth() {
        if (sprite != null) {
            return sprite.getBounds().width();
        } else {
            return 0;
        }
    }

    public int getHeight() {
        if (sprite != null) {
            return sprite.getBounds().height();
        } else {
            return 0;
        }
    }

    public int getSpriteNum() {
        return this.spriteNum;
    }
    public void setSpriteNum(int num) {
        this.spriteNum = num;
    }
}