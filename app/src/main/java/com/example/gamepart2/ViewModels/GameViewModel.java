package com.example.gamepart2.ViewModels;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.ViewModel;

import com.example.gamepart2.Models.Difficulty;
import com.example.gamepart2.Models.Players;
import com.example.gamepart2.Models.Score;
import com.example.gamepart2.View.MovementStrategy;
import com.example.gamepart2.View.RunningMovementStrategy;
import com.example.gamepart2.View.WalkingMovementStrategy;

public class GameViewModel extends ViewModel {
    private Players player;
    private Difficulty difficulty;
    private Score scoreTrack;
    private MovementStrategy walk;
    private MovementStrategy run;
    private int spriteNum;

    public GameViewModel() {
        player = Players.getPlayer();
        difficulty = new Difficulty();
        scoreTrack = new Score();
        walk = new WalkingMovementStrategy();
        run = new RunningMovementStrategy();
        player.setMovementStrategy(walk);
    }
    //public void resetGame() {
        // Reset the game's state to initial values
        //setPlayerScore(500); // Assuming this sets the player's score
        // Add other resets as needed, like player health, etc.
    //}

    public void reduceScoreEnd() {
        scoreTrack.setScore(0);
    }
    public String getPlayerName() {
        return player.getName();
    }
    public void setPlayerName(String name) {
        player.setName(name);
    }
    public int getPlayerHealth() {
        return player.getHealth();
    }
    public void setPlayerHealth(int health) {
        player.setHealth(health);
    }
    public Drawable getPLayerSprite() {
        return player.getSprite();
    }
    public void setPlayerSprite(Drawable sprite) {
        player.setSprite(sprite);
    }
    public String getPlayerDifficulty() {
        return difficulty.getDif();
    }
    public void setPLayerDifficulty(String diff) {
        difficulty.setDif(diff);
    }
    public int getPlayerScore() {
        return scoreTrack.getScore();
    }
    public void setPlayerScore(int score) {
        scoreTrack.setScore(score);
    }
    public void reduceScore() {
        if (scoreTrack.getScore() > 0) {
            scoreTrack.setScore(scoreTrack.getScore() - 50);
        }
    }
    public float up (float y) {
        return player.playerMoveUp(y);
    }
    public float down (float y) {
        return player.playerMoveDown(y);
    }
    public float left (float x) {
        return player.playerMoveLeft(x);
    }
    public float right (float x) {
        return player.playerMoveRight(x);
    }
    public int getSpriteNum() {
       return spriteNum;
    }
    public void setSpriteNum(int num) {
        this.spriteNum = num;
    }
    public void attackScoreIncrease() {
        scoreTrack.setScore(scoreTrack.getScore() + 50);
    }
}