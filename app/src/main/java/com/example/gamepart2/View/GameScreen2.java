package com.example.gamepart2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamepart2.Models.Players;
import com.example.gamepart2.R;
import com.example.gamepart2.ViewModels.GameViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen2 extends AppCompatActivity {
    private TextView userName;
    private TextView difficulty;
    private ImageView characterSprite;
    private TextView hP;
    private Button next;
    private Timer scoreTime;
    private TextView tvScore;
    private GameViewModel gameViewModel;

    private Handler handler = new Handler();
    private Enemy enemy1, enemy2, enemy3, enemy4;
    private List<Enemy> enemies = new ArrayList<>();
    private Runnable updateRunnable;
    private int playerX,playerY, screenWidth, screenHeight, character;

    private int hp;
    ImageView powerUp1;
    ImageView powerUp2;
    ImageView powerUp3;
    private int playerSpeed = 50;

    private List<PowerUp> activePowerUps = new ArrayList<>();
    private boolean isPlayerInvincible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen2);


        powerUp1 = findViewById(R.id.powerUp1);
        powerUp2 = findViewById(R.id.powerUp2);
        powerUp3 = findViewById(R.id.powerUp3);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        initializeEnemies();
        startGameLoop();

        Bundle bundle = getIntent().getExtras();
        hp = bundle.getInt("hp");
        characterSprite = (ImageView) findViewById(R.id.character);
        character = bundle.getInt("character");
        if (character == 1) {
            characterSprite.setImageResource(R.drawable.avatar1);
        } else if (character == 2) {
            characterSprite.setImageResource(R.drawable.avatar2);
        } else if (character == 3) {
            characterSprite.setImageResource(R.drawable.avatar3);
        }
        playerX = characterSprite.getLeft();
        playerY = characterSprite.getTop();

        characterSprite.setFocusable(true);
        characterSprite.setFocusableInTouchMode(true);
        characterSprite.requestFocus();



        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        userName = (TextView) findViewById(R.id.name);
        userName.setText(gameViewModel.getPlayerName());
        hP = (TextView) findViewById(R.id.health);
        hP.setText(String.valueOf(hp));
        difficulty = (TextView) findViewById(R.id.difficulty);
        difficulty.setText(gameViewModel.getPlayerDifficulty());
        gameViewModel.setPlayerScore(bundle.getInt("score"));
        tvScore = (TextView) findViewById(R.id.tv_score);
        scoreTime = new Timer();
        scoreTime.schedule(new TimerTask() {
            @Override
            public void run() {
                gameViewModel.reduceScore();
                tvScore.setText("Score: " + gameViewModel.getPlayerScore());
                if (gameViewModel.getPlayerScore() <= 0) {
                    playerLose();
                }
            }
        }, 0, 5000);

    }
    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int newSpeed) {
        this.playerSpeed = newSpeed;
    }


    public void playerLose() {
        gameViewModel.reduceScoreEnd();
        Intent inte = new Intent(GameScreen2.this, LosingEndScreen.class);
        Bundle playerinfo = new Bundle();
        playerinfo.putInt("score", gameViewModel.getPlayerScore());
        inte.putExtras(playerinfo);
        inte.putExtra("character", character);
        inte.putExtra("hp", hp);
        startActivity(inte);
        finish();
    }

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    int deltaX = 0;
    int deltaY = 0;

    switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            deltaX = -playerSpeed; // Move left
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            deltaX = playerSpeed; // Move right
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            deltaY = -playerSpeed; // Move up
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            deltaY = playerSpeed; // Move down
            break;
    }

    int newX = playerX + deltaX;
    int newY = playerY + deltaY;

    // Check for collisions and move the player accordingly
    if (isWithinScreenBoundaries(newX, newY)) {
        movePlayer(deltaX, deltaY);

        if (playerX + characterSprite.getWidth() >= screenWidth) {
            startNextGameScreen();
        }
    }

    return true;
}

    private void startNextGameScreen() {
        Intent intent = new Intent(GameScreen2.this, GameScreen3.class);
        Bundle playerinfo = new Bundle();
        playerinfo.putInt("score", gameViewModel.getPlayerScore());
        intent.putExtras(playerinfo);
        intent.putExtra("character", character);
        startActivity(intent);
    }

    private void updateHealth(Enemy enemy) {
        if (enemy.getClass() == Enemy1.class) {
            Players.getPlayer().setHealth(Players.getPlayer().getHealth() - 1);
            gameViewModel.setPlayerScore(gameViewModel.getPlayerScore() - 1);
        } else if (enemy.getClass() == Enemy2.class) {
            Players.getPlayer().setHealth(Players.getPlayer().getHealth() - 2);
            gameViewModel.setPlayerScore(gameViewModel.getPlayerScore() - 2);
        } else if (enemy.getClass() == Enemy3.class) {
            Players.getPlayer().setHealth(Players.getPlayer().getHealth() - 3);
            gameViewModel.setPlayerScore(gameViewModel.getPlayerScore() - 3);
        } else {
            Players.getPlayer().setHealth(Players.getPlayer().getHealth() - 4);
            gameViewModel.setPlayerScore(gameViewModel.getPlayerScore() - 4);
        }
    }
    private void initializeEnemies() {
        EnemyFactory enemy1Factory = new Enemy1Factory(5, screenWidth, screenHeight, this);
        enemy1 = enemy1Factory.createEnemy(300, 200);
        enemies.add(enemy1);

        EnemyFactory enemy2Factory = new Enemy2Factory(10, screenWidth, screenHeight, this);
        enemy2 = enemy2Factory.createEnemy(80, 500);
        enemies.add(enemy2);

        EnemyFactory enemy3Factory = new Enemy3Factory(8, screenWidth, screenHeight, this);
        enemy3 = enemy3Factory.createEnemy(280, 350);
        enemies.add(enemy3);

        EnemyFactory enemy4Factory = new Enemy4Factory(16, screenWidth, screenHeight, this);
        enemy4 = enemy4Factory.createEnemy(300, 600);
        enemies.add(enemy4);


        // Display enemies on the screen
        for (Enemy enemy : enemies) {
            enemy.display(findViewById(android.R.id.content));
        }
    }

    private void startGameLoop() {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                // Move all enemies
                for (Enemy enemy : enemies) {
                    enemy.move();
                    enemy.display(findViewById(android.R.id.content));

                    if (!isPlayerInvincible && checkCollision(playerX, playerY, characterSprite.getWidth(),
                            characterSprite.getHeight(), enemy.getXpos(), enemy.getYpos(),
                            enemy.getWidth(), enemy.getHeight())) {

                        updateHealth(enemy);

                        hP.setText(String.valueOf(hp));
                    }
                }
                // Repeat this process after a delay (e.g., 16 milliseconds for 60 FPS)
                handler.postDelayed(this, 16);
            }
        };
        // Start the initial update
        handler.post(updateRunnable);
    }

    private boolean checkCollision (int pX, int pY, int pWidth, int pHeight,float eX, float eY,
                                    int eWidth, int eHeight) {
        float pLeft = pX;
        float pRight = pX + pWidth;
        float pTop = pY;
        float pBottom = pY + pHeight;

        float eLeft = (int) eX;
        float eRight = (int) eX + eWidth;
        float eTop = (int) eY;
        float eBottom = (int) eY + eHeight;

        boolean collide =  (((pRight > eLeft) && (pLeft < eRight)) && ((pBottom > eTop) && (pTop < eBottom)));
        return collide;
    }

    private void movePlayer(int deltaX, int deltaY) {
        // Calculate the new player position
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;

        // Update the player's position on the screen
        characterSprite.setX(newX);
        characterSprite.setY(newY);

        // Update the player's current position
        playerX = newX;
        playerY = newY;
        collectPowerUp(powerUp1, new HealthPowerUp(hp), playerX, playerY);
        collectPowerUp(powerUp2, new SpeedPowerUp(playerSpeed), playerX, playerY);
        collectPowerUp(powerUp3, new InvincibilityPowerUp(), playerX, playerY);
    }

    private void collectPowerUp(ImageView view, PowerUp powerUp, int playerX, int playerY) {
        // Gets coordinates/location of player
        int playerLeft = playerX;
        int playerTop = playerY;
        int playerRight = playerLeft + characterSprite.getWidth();
        int playerBottom = playerTop + characterSprite.getHeight();

        // Gets coordinates/location of powerUp Sprite
        int imageViewLeft = view.getLeft();
        int imageViewTop = view.getTop();
        int otherRight = imageViewLeft + view.getWidth();
        int otherBottom = imageViewTop + view.getHeight();
        // Check for collision
        if (playerRight > imageViewLeft && playerLeft < otherRight &&
                playerBottom > imageViewTop && playerTop < otherBottom) {
            // Collision occurred

            // Add the power-up to the active list
            activePowerUps.add(powerUp);

            // Apply the power-up effects
            powerUp.applyPowerUp();

            // Update the TextView displaying health directly
            if (powerUp instanceof HealthPowerUp) {
                hP.setText(String.valueOf(((HealthPowerUp) powerUp).getModifiedAttribute()));
            } else if (powerUp instanceof SpeedPowerUp) {
                // Get the modified speed from the power-up
                int newPlayerSpeed = ((SpeedPowerUp) powerUp).getModifiedAttribute();
                // Update the player's speed
                setPlayerSpeed(newPlayerSpeed);
            } else if (powerUp instanceof InvincibilityPowerUp) {
                int invincible = ((InvincibilityPowerUp) powerUp).getModifiedAttribute();
                if (invincible == 1) {
                    isPlayerInvincible = true;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isPlayerInvincible = false;
                            // Add code to visually indicate the end of invincibility to the player
                        }
                    }, 19000);
                }
            }
            // Remove the power-up from the view
            view.setVisibility(View.GONE);
        }
    }
    private boolean isWithinScreenBoundaries(int x, int y) {
        // Check if the new position is within screen boundaries
        return x >= 0 && y >= 0 && y + characterSprite.getHeight() <= screenHeight;
    }
    public String getName() {
        return userName.toString();
    }

    public String getHp() {
        return hP.toString();
    }
}