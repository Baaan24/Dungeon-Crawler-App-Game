package com.example.gamepart2.Models;

import com.example.gamepart2.View.ScoreEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private static Leaderboard instance;
    private List<ScoreEntry> scoreEntries;
    private ScoreEntry latestScore;

    private Leaderboard() {
        scoreEntries = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void addScoreEntry(ScoreEntry scoreEntry) {
        scoreEntries.add(scoreEntry);
        latestScore = scoreEntry;
        // Sort the leaderboard in descending order based on scores
        Collections.sort(scoreEntries, Collections.reverseOrder());
    }

    public List<ScoreEntry> getTopScores() {
        int maxAmount = 5;
        int count = Math.min(scoreEntries.size(), maxAmount);
        return scoreEntries.subList(0, count);
    }

    public ScoreEntry getLatestScore() {
        return latestScore;
    }


 }