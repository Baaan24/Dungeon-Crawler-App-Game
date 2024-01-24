package com.example.gamepart2;
import org.junit.Test;
import static org.junit.Assert.*;
import com.example.gamepart2.Models.Score;
import com.example.gamepart2.ViewModels.GameViewModel;
public class JasonPUnitTests {
    @Test
    public void startScore() {
        Score temp = new Score();
        temp.setScore(500);
        assertEquals(500,temp.getScore());
    }

    @Test
    public void isScoreNegative() {
        GameViewModel gvm = new GameViewModel();
        gvm.setPlayerScore(1000);
        for (int i = 0; i < 100 ; i++) {
            gvm.reduceScore();
        }
        assertEquals(0,gvm.getPlayerScore());
    }
}
