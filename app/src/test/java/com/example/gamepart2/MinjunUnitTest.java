package com.example.gamepart2;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class MinjunUnitTest {
    private ConfigScreen configScreen;
    @Before
    public void setUp() {
        configScreen = new ConfigScreen();
    }
    @Test
    public void testNullName() {
        assertFalse(configScreen.isNameValid(null));
    }
    @Test
    public void testCalculateHPEasy() {
        configScreen.setSelectedDifficulty("Easy");
        configScreen.calculateHP();
        assertEquals(200, configScreen.getHP());
    }
}
