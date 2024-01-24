package com.example.gamepart2;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.gamepart2.View.GameScreen1;
import com.example.gamepart2.View.GameScreen2;
import com.example.gamepart2.View.ScoreEntry;
import com.example.gamepart2.Models.Leaderboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void testForValidNames() {
        // Test cases for valid names
        assertTrue(isNameValid("Alice"));
        assertTrue(isNameValid("Bob"));
        assertTrue(isNameValid("John Doe"));
    }
    @Test
    public void testForInvalidNames(){
        // Test cases for invalid names
        assertFalse(isNameValid(null));
        assertFalse(isNameValid(""));
        assertFalse(isNameValid("  "));
        assertFalse(isNameValid("Error"));
    }
    public boolean isNameValid(String name) {
        if (name == null || name.trim().isEmpty()|| "Error".equals(name)){
            return false;
        } else {
            return true;
        }
    }




    @Test
    public void leaderboard_isCorrect(){
        List<ScoreEntry> logged_scores = new ArrayList<>();
        logged_scores.add(new ScoreEntry("A",480, new Date())); //2
        logged_scores.add(new ScoreEntry("B", 369, new Date()));
        logged_scores.add(new ScoreEntry("J", 221, new Date()));
        logged_scores.add(new ScoreEntry("R", 420, new Date())); //3
        logged_scores.add(new ScoreEntry("N", 387, new Date())); //5
        logged_scores.add(new ScoreEntry("P", 166, new Date()));
        logged_scores.add(new ScoreEntry("Q", 490, new Date())); //1
        logged_scores.add(new ScoreEntry("W", 47, new Date()));
        logged_scores.add(new ScoreEntry("C", 235, new Date()));
        logged_scores.add(new ScoreEntry("O", 380, new Date()));
        logged_scores.add(new ScoreEntry("K", 412, new Date())); //4
        logged_scores.add(new ScoreEntry("I", 57, new Date()));
        logged_scores.add(new ScoreEntry("J", 10, new Date()));
        logged_scores.add(new ScoreEntry("B", 0, new Date()));
        Leaderboard lead = Leaderboard.getInstance();
        for (ScoreEntry score : logged_scores) {
            lead.addScoreEntry(score);
        }
        List<ScoreEntry> results = lead.getTopScores();

        //check if leaderboard has top5 scores
        assertEquals("Q", results.get(0).getPlayerName());
        assertEquals(490, results.get(0).getScore());

        assertEquals("A", results.get(1).getPlayerName());
        assertEquals(480, results.get(1).getScore());

        assertEquals("R", results.get(2).getPlayerName());
        assertEquals(420, results.get(2).getScore());

        assertEquals("K", results.get(3).getPlayerName());
        assertEquals(412, results.get(3).getScore());

        assertEquals("N", results.get(4).getPlayerName());
        assertEquals(387, results.get(4).getScore());
    }
    @Test
    public void currentScore_isCorrect() {
        List<ScoreEntry> logged_scores = new ArrayList<>();
        logged_scores.add(new ScoreEntry("Player1",480, new Date()));
        logged_scores.add(new ScoreEntry("Player2", 369, new Date()));
        logged_scores.add(new ScoreEntry("Player3", 221, new Date()));
        logged_scores.add(new ScoreEntry("Player4", 420, new Date()));
        logged_scores.add(new ScoreEntry("Player5", 387, new Date()));
        logged_scores.add(new ScoreEntry("Player6", 166, new Date()));
        Leaderboard lead = Leaderboard.getInstance();
        for (ScoreEntry score : logged_scores) {
            lead.addScoreEntry(score);
        }
        ScoreEntry currScore = lead.getLatestScore();

        // check to make sure its latest score entry
        assertEquals("Player6", currScore.getPlayerName());
        assertEquals(166, currScore.getScore());
    }

    //Vaibhav Test cases for GS1
    private GameScreen1 gs1;
    public void setupGS1() {
        gs1 = new GameScreen1();
    }

    public void testUNText() {
        gs1.onCreate(null);
        assertEquals("Player Name", gs1.getName());
    }

    public void testHealth() {
        gs1.onCreate(null);
        assertEquals("100", gs1.getHp());
    }

}