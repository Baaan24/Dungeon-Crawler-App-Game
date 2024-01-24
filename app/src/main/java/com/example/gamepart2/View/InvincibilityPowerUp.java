package com.example.gamepart2.View;

import android.os.Handler;

public class InvincibilityPowerUp implements PowerUp{

    private boolean isInvincible;

    public InvincibilityPowerUp() {
        this.isInvincible = false;
    }

    @Override
    public void applyPowerUp() {
        // Enable invincibility
        isInvincible = true;
    }

    @Override
    public int getModifiedAttribute() {
        return isInvincible ? 1 : 0; // Return 1 if invincible, 0 otherwise
    }
}