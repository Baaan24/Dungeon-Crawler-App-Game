package com.example.gamepart2.View;

public class HealthPowerUp implements PowerUp {
    private static final int HEALTH_INCREASE_AMOUNT = 150;
    private int playerHealth;

    public HealthPowerUp(int playerHealth) {
        this.playerHealth = playerHealth;

    }
    @Override
    public void applyPowerUp() {
        playerHealth += HEALTH_INCREASE_AMOUNT;
    }

    @Override
    public int getModifiedAttribute() {
        return playerHealth;
    }
}