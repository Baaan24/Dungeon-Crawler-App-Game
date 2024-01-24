package com.example.gamepart2;

import static org.junit.Assert.assertEquals;

import android.view.KeyEvent;

import com.example.gamepart2.View.GameScreen2;
import com.example.gamepart2.View.GameScreen3;
import com.example.gamepart2.ViewModels.GameViewModel;

import org.junit.Before;
import org.junit.Test;

public class SprintFourTests {
    private GameScreen3 gameScreen;
    private GameViewModel gameViewModel;

    private GameScreen2 gameScreen2;
    @Before
    public void setUp() {
        gameScreen = new GameScreen3();
        // Initialize necessary fields
        gameScreen.playerX = 100;
        gameScreen.playerY = 100;
        // Other necessary initializations...
    }
    @Before
    public void setUp2() {
        gameScreen2 = new GameScreen2();
    }

    @Test
    public void testMovementLogic() {
        // Simulate key press
        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        assertEquals("Left movement", -50, gameScreen.deltaX);
        assertEquals("Y should remain same", 0, gameScreen.deltaY);

        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        assertEquals("Right movement", 50, gameScreen.deltaX);
        assertEquals("Y should remain same", 0, gameScreen.deltaY);

        // Similarly for UP and DOWN
    }
    @Test
    public void testScoreBasedLogic() {
        // Assuming we have a way to set the player's score and simulate reaching the screen's edge
        gameScreen.gameViewModel.setPlayerScore(1);
        gameScreen.playerX = // Set this value such that playerX + characterSprite.getWidth() >= screenWidth
                gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        // You need to find a way to check whether the winning logic is triggered

        gameScreen.gameViewModel.setPlayerScore(0);
        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        // Similarly, check whether the losing logic is triggered
    }

    @Test
    public void testMoveLeft() {
        int initialX = (int) gameScreen.characterSprite.getX();
        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        int newX = (int) gameScreen.characterSprite.getX();
        assertEquals(initialX - 50, newX);
    }

    @Test
    public void testMoveRight() {
        int initialX = (int) gameScreen.characterSprite.getX();
        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        int newX = (int) gameScreen.characterSprite.getX();
        assertEquals(initialX + 50, newX);
    }

    @Test
    public void testMoveUp() {
        int initialY = (int) gameScreen.characterSprite.getY();
        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        int newY = (int) gameScreen.characterSprite.getY();
        assertEquals(initialY - 50, newY);
    }

    @Test
    public void testMoveDown() {
        int initialY = (int) gameScreen.characterSprite.getY();
        gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
        int newY = (int) gameScreen.characterSprite.getY();
        assertEquals(initialY + 50, newY);
    }

    @Test
    public void testInvalidKey() {
        int initialX = (int) gameScreen.characterSprite.getX();
        int initialY = (int) gameScreen.characterSprite.getY();
        gameScreen.onKeyDown(KeyEvent.KEYCODE_A, null);
        int newX = (int) gameScreen.characterSprite.getX();
        int newY = (int) gameScreen.characterSprite.getY();
        assertEquals(initialX, newX);
        assertEquals(initialY, newY);
    }
    @Test
    public void testScreenBoundary() {
        int initialX = (int) gameScreen.characterSprite.getX();
        int screenWidth = gameScreen.getScreenWidth();
        int moves = screenWidth / 50 + 1; // Move to the right edge
        for (int i = 0; i < moves; i++) {
            gameScreen.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        int newX = (int) gameScreen.characterSprite.getX();
        assertEquals(screenWidth, newX);
    }
    @Test
    public void testEnemy3Initialization() {
        int speed = 5;
        float x = 100;
        float y = 200;
        int screenWidth = 800;
        int screenHeight = 600;

        Enemy3 enemy3 = new Enemy3(speed, x, y, screenWidth, screenHeight, null);

        assertEquals(speed, enemy3.getSpeed());
        assertEquals(x, enemy3.getX(), 0);
        assertEquals(y, enemy3.getY(), 0);
        assertEquals(screenWidth, enemy3.getScreenWidth());
        assertEquals(screenHeight, enemy3.getScreenHeight());
        assertEquals(R.drawable.enemy3, enemy3.getSpriteResource());
        assertNotNull(enemy3.getContext());
    }

    @Test
    public void testEnemy3Move() {
        int speed = 5;
        float x = 100;
        float y = 200;
        int screenWidth = 800;
        int screenHeight = 600;

        Enemy3 enemy3 = new Enemy3(speed, x, y, screenWidth, screenHeight, null);

        float initialX = enemy3.getX();
        float initialY = enemy3.getY();

        enemy3.move();

        assertEquals("X position should be updated", initialX + speed, enemy3.getX(), 0);
        assertEquals("Y position should be updated", initialY + speed, enemy3.getY(), 0);
    }

    @Test
    public void testEnemy3BoundaryCheck() {
        int speed = 5;
        float x = 795;
        float y = 595;
        int screenWidth = 800;
        int screenHeight = 600;

        Enemy3 enemy3 = new Enemy3(speed, x, y, screenWidth, screenHeight, null);

        enemy3.move();  // Move towards the right boundary

        assertEquals("X position should be updated", x + speed, enemy3.getX(), 0);
        assertEquals("Direction should be changed", -speed, enemy3.getSpeed(), 0);

        enemy3.move();  // Move towards the left boundary

        assertEquals("X position should be updated", x, enemy3.getX(), 0);
        assertEquals("Direction should be changed", speed, enemy3.getSpeed(), 0);
    }
}
