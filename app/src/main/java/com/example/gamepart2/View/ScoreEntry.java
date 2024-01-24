package com.example.gamepart2.View;

import java.util.Date;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private String playerName;
    private int score;
    private Date dateTime;
    public ScoreEntry(String playerName, int score, Date dateTime) {
        this.playerName = playerName;
        this.score = score;
        this.dateTime = dateTime;
    }

    // Getter method for playerName
    public String getPlayerName() {
        return playerName;
    }

    // Getter method for score
    public int getScore() {
        return score;
    }

    // Getter method for dateTime
    public Date getDateTime() {
        return dateTime;
    }

    @Override
    public int compareTo(ScoreEntry scoreEntry) {
        return Integer.compare(this.score, scoreEntry.getScore());
    }
}