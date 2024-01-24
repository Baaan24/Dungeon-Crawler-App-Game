package com.example.gamepart2;

import static org.junit.Assert.assertEquals;

import com.example.gamepart2.View.RunningMovementStrategy;

import org.junit.Before;
import org.junit.Test;

public class SprintThreeTests {
    private RunningMovementStrategy strategy;

    @Before
    public void setUp() {
        strategy = new RunningMovementStrategy();
    }

    @Test
    public void testMoveUp() {
        float initialY = 10.0f;
        float newY = strategy.moveUp(initialY);
        assertEquals(40.0f, newY, 0.001); // the third argument is the delta which allows for minor discrepancies in floating point calculations
    }

    @Test
    public void testMoveDown() {
        float initialY = 10.0f;
        float newY = strategy.moveDown(initialY);
        assertEquals(-20.0f, newY, 0.001);
    }

    @Test
    public void testMoveLeft() {
        float initialX = 10.0f;
        float newX = strategy.moveLeft(initialX);
        assertEquals(-20.0f, newX, 0.001);
    }

    @Test
    public void testMoveRight() {
        float initialX = 10.0f;
        float newX = strategy.moveRight(initialX);
        assertEquals(40.0f, newX, 0.001);
    }
    @Test
    public void failUpperLeftBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(UpperLeftBound);
    }
    @Test
    public void inUpperLeftBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(BottomLeftBound));
    }
    @Test
    public void failUpperRightBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(UpperRightBound));
    }
    @Test
    public void inUpperRightBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(BottomLeftBound));
    }


    @Test
    public void failBottomLeftBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(BottomLeftBound));
    }
    @Test
    public void inBottomLeftBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(BottomLeftBound));
    }
    @Test
    public void failBottomRightBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(BottomRightBound));
    }
    @Test
    public void inBottomRightBound() {
        GameScreen1 gsa1;
        gsa1.onCreate(4, 90, 400, 999);
        assertTrue(savedinstance.intersect(BottomLeftBound));
    }

}
