package com.example.gamepart2.View;

public class SpeedPowerUp implements PowerUp{
    private static final int SPEED_INCREASE_AMOUNT = 40;
    private int playerSpeed;
    public SpeedPowerUp(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    @Override
    public void applyPowerUp() {
        playerSpeed += SPEED_INCREASE_AMOUNT;
    }

    @Override
    public int getModifiedAttribute() {
        return playerSpeed;
    }
}
