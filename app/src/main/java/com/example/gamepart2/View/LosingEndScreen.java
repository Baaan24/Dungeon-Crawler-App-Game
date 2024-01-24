package com.example.gamepart2.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.gamepart2.R;
import com.example.gamepart2.Models.Leaderboard;

public class LosingEndScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing_end_screen);

        TextView leaderBoardScoresTextView = findViewById(R.id.leaderBoardScoresText);
        TextView currentScoreTextView = findViewById(R.id.currentScoreText);
        Button restartButton = (Button) findViewById(R.id.restart);
        Button exitButton = (Button) findViewById(R.id.exit);

        // Get the top 5 scores from the leaderboard
        Leaderboard leaderboard = Leaderboard.getInstance();
        List<ScoreEntry> topScores = leaderboard.getTopScores();

        // Set the leaderboard and current score text views
        leaderBoardScoresTextView.setText(buildLeaderboardText(topScores));
        currentScoreTextView.setText(buildCurrentGameText(leaderboard.getLatestScore()));

        restartButton.setOnClickListener(view -> openMainActivity());
        exitButton.setOnClickListener(view -> {
            finishAffinity();
            System.exit(0);
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public String buildLeaderboardText(List<ScoreEntry> topScores) {
        StringBuilder leaderboardSText = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < topScores.size(); i++) {
            ScoreEntry entry = topScores.get(i);
            String playerName = entry.getPlayerName();
            int score = entry.getScore();
            String dateTime = dateFormat.format(entry.getDateTime());

            leaderboardSText.append("Rank ").append(i + 1).append(": ")
                    .append("Player: ").append(playerName).append(", ")
                    .append("Score: ").append(score).append(", ")
                    .append("Date/Time: ").append(dateTime).append("\n");
        }
        return leaderboardSText.toString();
    }
    public String buildCurrentGameText(ScoreEntry currentGameScore) {
        StringBuilder currentPlayerText = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentPlayerName = currentGameScore.getPlayerName();
        int currentPlayerScore = currentGameScore.getScore();
        String currentPlayerDateTime = dateFormat.format(currentGameScore.getDateTime());
        currentPlayerText.append("Player: ").append(currentPlayerName).append(", ")
                .append("Score: ").append(currentPlayerScore).append(", ")
                .append("Date/Time: ").append(currentPlayerDateTime);
        return currentPlayerText.toString();
    }
}