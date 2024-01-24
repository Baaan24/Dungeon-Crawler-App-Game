package com.example.gamepart2;

import static org.junit.Assert.assertEquals;

import com.example.gamepart2.Models.Players;
import com.example.gamepart2.View.Enemy;
import com.example.gamepart2.View.Enemy1;
import com.example.gamepart2.View.Enemy2;
import com.example.gamepart2.View.Enemy3;
import com.example.gamepart2.View.Enemy4;
import com.example.gamepart2.View.GameScreen1;
import com.example.gamepart2.View.GameScreen2;
import com.example.gamepart2.View.GameScreen3;
import com.example.gamepart2.ViewModels.GameViewModel;

import org.junit.Before;
import org.junit.Test;

public class SprintFiveTests {
    private GameScreen1 gameScreen;
    private GameViewModel gameViewModel = null;
    private GameScreen2 gameScreen2;
    private GameScreen3 gameScreen3;

    @Before
    public void setUp() {
        gameScreen = new GameScreen1();
        // Initialize any necessary game state here
    }
    @Before
    public void setUp2() {
        gameScreen2 = new GameScreen2();
        // Initialize any necessary game state here
    }
    @Before
    public void setUp3() {
        gameScreen3 = new GameScreen3();
        // Initialize any necessary game state here
    }

    @Test
    public void testUpdateHealthWithEnemy1() {
        // Create an Enemy1 instance
        Enemy enemy = new Enemy1(); // Adjust this based on your actual constructor

        // Record the initial health and score
        int initialHealth = Players.getPlayer().getHealth();
        int initialScore = gameViewModel.getPlayerScore();

        // Trigger the public method that indirectly calls updateHealth
        gameScreen.handleEnemyInteraction(enemy);

        // Assert the health and score are updated correctly
        assertEquals("Health should decrease by 1", initialHealth - 1, Players.getPlayer().getHealth());
        assertEquals("Score should decrease by 1", initialScore - 1, gameViewModel.getPlayerScore());
    }
    @Test
    public void testUpdateHealthWithEnemy1P() {
        // Create an Enemy1 instance
        Enemy enemy = new Enemy1(); // Adjust this based on your actual constructor

        // Record the initial health and score
        int initialHealth = Players.getPlayer().getHealth();
        int initialScore = gameViewModel.getPlayerScore();

        // Trigger the public method that indirectly calls updateHealth
        gameScreen.handleEnemyInteraction(enemy);

        // Assert the health and score are updated correctly
        assertEquals("Health should increase by 1", initialHealth + 1, Players.getPlayer().getHealth());
        assertEquals("Score should increase by 1", initialScore + 1, gameViewModel.getPlayerScore());
    }

    @Test
    public void testUpdateHealthWithEnemy2() {
        // Create an Enemy1 instance
        Enemy enemy = new Enemy2(); // Adjust this based on your actual constructor


        // Record the initial health and score
        int initialHealth = Players.getPlayer().getHealth();
        int initialScore = gameViewModel.getPlayerScore();


        // Trigger the public method that indirectly calls updateHealth
        gameScreen.handleEnemyInteraction(enemy);


        // Assert the health and score are updated correctly
        assertEquals("Health should decrease by 2", initialHealth - 2, Players.getPlayer().getHealth());
        assertEquals("Score should decrease by 2", initialScore - 2, gameViewModel.getPlayerScore());
    }

    @Test
public void testUpdateHealthWithEnemy2P() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy2(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should increase by 2", initialHealth + 2, Players.getPlayer().getHealth());
   assertEquals("Score should increase by 2", initialScore + 2, gameViewModel.getPlayerScore());
}

    @Test
public void testUpdateHealthWithEnemy3() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy3(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should decrease by 3", initialHealth - 3, Players.getPlayer().getHealth());
   assertEquals("Score should decrease by 3", initialScore - 3, gameViewModel.getPlayerScore());
}

    @Test
public void testUpdateHealthWithEnemy3P() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy3(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should increase by 3", initialHealth + 3, Players.getPlayer().getHealth());
   assertEquals("Score should increase by 3", initialScore + 3, gameViewModel.getPlayerScore());
}

    @Test
public void testUpdateHealthWithEnemy4() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy4(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should decrease by 4", initialHealth - 4, Players.getPlayer().getHealth());
   assertEquals("Score should decrease by 4", initialScore - 4, gameViewModel.getPlayerScore());
}

@Test
public void testUpdateHealthWithEnemy4P() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy4(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should increase by 4", initialHealth + 4, Players.getPlayer().getHealth());
   assertEquals("Score should increase by 4", initialScore + 4, gameViewModel.getPlayerScore());
}

    @Test
public void testUpdateHealthWithEnemy1G2() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy1(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen2.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should decrease by 1", initialHealth - 1, Players.getPlayer().getHealth());
   assertEquals("Score should decrease by 1", initialScore - 1, gameViewModel.getPlayerScore());
}

    @Test
public void testUpdateHealthWithEnemy1G2P() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy1(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen2.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should increase by 1", initialHealth + 1, Players.getPlayer().getHealth());
   assertEquals("Score should increase by 1", initialScore + 1, gameViewModel.getPlayerScore());
}

    @Test
public void testUpdateHealthWithEnemy2G2() {
   // Create an Enemy1 instance
   Enemy enemy = new Enemy2(); // Adjust this based on your actual constructor


   // Record the initial health and score
   int initialHealth = Players.getPlayer().getHealth();
   int initialScore = gameViewModel.getPlayerScore();


   // Trigger the public method that indirectly calls updateHealth
   gameScreen2.handleEnemyInteraction(enemy);


   // Assert the health and score are updated correctly
   assertEquals("Health should decrease by 2", initialHealth - 2, Players.getPlayer().getHealth());
   assertEquals("Score should decrease by 2", initialScore - 2, gameViewModel.getPlayerScore());
}

}
