package com.example.gamepart2.View;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gamepart2.R;
import com.example.gamepart2.Models.Leaderboard;
import com.example.gamepart2.ViewModels.GameViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.view.KeyEvent;

public class GameScreen extends AppCompatActivity{
    private TextView userName;
    private TextView difficulty;
    private ImageView characterSprite;

    private TextView hP;
    private Button next;
    private int playerX, playerY;
    private Date dateTime;
    private Runnable countdownRunnable;
    private int screenWidth;
    private int screenHeight;
    private int startTime = 500;
    private int score = 0;
    private String name;
    private TextView scoreDisplay;
    private int character, hp;
    private Handler handler = new Handler();
    ImageView powerUp1;
    ImageView powerUp2;
    ImageView powerUp3;
    private int playerSpeed = 50;

    private List<PowerUp> activePowerUps = new ArrayList<>();

    private Enemy enemy1, enemy2, enemy3, enemy4;
    private List<Enemy> enemies = new ArrayList<>();
    private Runnable updateRunnable;
    private boolean isPlayerInvincible = false;

    private GameViewModel gameViewModel;
    private boolean enemyOneAttacked = false;
    private boolean enemyTwoAttacked = false;
    private boolean enemyOneStop = false;
    private boolean enemyTwoStop = false;
    private Handler attack;
    private int spriteNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.setSpriteNum(character);
        powerUp1 = findViewById(R.id.powerUp1);
        powerUp2 = findViewById(R.id.powerUp2);
        powerUp3 = findViewById(R.id.powerUp3);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int spriteNum = extras.getInt("characterSprite", -1);
            gameViewModel.setSpriteNum(spriteNum);
        }
        // Initialize screen boundaries
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        initializeEnemies();
        startGameLoop();
        scoreDisplay = findViewById(R.id.scoreDisplay);
        dateTime = new Date();
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        hp = bundle.getInt("HP");
        String diff = "";
        if (hp == 200) {

            diff = "Easy";
        } else if (hp == 150) {

            diff = "Medium";
        } else if (hp == 100) {

            diff = "Hard";
        } else {
            diff = "Default";
        }
        character = bundle.getInt("characterSprite");
        userName = (TextView) findViewById(R.id.name);
        userName.setText(name);
        difficulty = (TextView) findViewById(R.id.difficulty);
        difficulty.setText(diff);
        hP = (TextView) findViewById(R.id.health);
        hP.setText(String.valueOf(hp));
        characterSprite = (ImageView) findViewById(R.id.characterSprite);
        if (character == 1) {
            characterSprite.setImageResource(R.drawable.avatar1);
        } else if (character == 2) {
            characterSprite.setImageResource(R.drawable.avatar2);
        } else if (character == 3) {
            characterSprite.setImageResource(R.drawable.avatar3);
        }
        startCountdownTimer();

        // Initial player position
        playerX = characterSprite.getLeft();
        playerY = characterSprite.getTop();

        // Set focus and key listener for characterSprite
        characterSprite.setFocusable(true);
        characterSprite.setFocusableInTouchMode(true);


    }
    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int newSpeed) {
        this.playerSpeed = newSpeed;
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

    private void updateHealth(Enemy enemy) {
        if (enemy.getClass() == Enemy1.class) {
            hp -= 1;
            startTime -= 1;
        } else if (enemy.getClass() == Enemy2.class) {
            hp -= 2;
            startTime -= 2;
        } else if (enemy.getClass() == Enemy3.class) {
            hp -= 3;
            startTime -= 3;
        } else {
            hp -= 4;
            startTime -= 4;
        }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                playerAttack();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                movePlayer(-playerSpeed, 0); // Move left
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                movePlayer(playerSpeed, 0); // Move right
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                movePlayer(0, -playerSpeed); // Move up
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                movePlayer(0, playerSpeed); // Move down
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    private void movePlayer(int deltaX, int deltaY) {
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;

        if (isWithinScreenBoundaries(newX, newY)) {
            playerX = newX;
            playerY = newY;

            characterSprite.setX(playerX);
            characterSprite.setY(playerY);

            collectPowerUp(powerUp1, new HealthPowerUp(hp), playerX, playerY);
            collectPowerUp(powerUp2, new SpeedPowerUp(playerSpeed), playerX, playerY);
            collectPowerUp(powerUp3, new InvincibilityPowerUp(), playerX, playerY);

            if (playerX + characterSprite.getWidth() >= screenWidth) {
                handlePlayerReachedEnd();
            }
        }
    }

    public void playerAttack() {
        if (gameViewModel.getSpriteNum() == 1) {
            characterSprite.setImageResource(R.drawable.avatar1attack);
            attack = new Handler();
            attack.postDelayed(new Runnable() {
                public void run() {
                    characterSprite.setImageResource(R.drawable.avatar1);
                }
            }, 500);
        } else if (gameViewModel.getSpriteNum() == 2) {
            characterSprite.setImageResource(R.drawable.avatar2attack);
            attack = new Handler();
            attack.postDelayed(new Runnable() {
                public void run() {
                    characterSprite.setImageResource(R.drawable.avatar2);
                }
            }, 500);
        } else if (gameViewModel.getSpriteNum() == 3) {
            characterSprite.setImageResource(R.drawable.avatar3attack);
            attack = new Handler();
            attack.postDelayed(new Runnable() {
                public void run() {
                    characterSprite.setImageResource(R.drawable.avatar3);
                }
            }, 500);
        }
        if (enemyOneAttacked) {
            enemy1.getView().animate().alpha(0f).setDuration(1000);
            enemyOneAttacked = false;
            enemyOneStop = true;
            gameViewModel.attackScoreIncrease();
        }
        if (enemyTwoAttacked) {
            enemy2.getView().animate().alpha(0f).setDuration(1000);
            enemyTwoAttacked = false;
            enemyTwoStop = true;
            gameViewModel.attackScoreIncrease();
        }
    }

    private void handlePlayerReachedEnd() {
        Intent intent = new Intent(GameScreen.this, GameScreen1.class);
        handler.removeCallbacks(countdownRunnable);
        score = startTime;
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addScoreEntry(new ScoreEntry(name, score, dateTime));
        intent.putExtra("hp", hp);
        intent.putExtra("character", character);
        intent.putExtra("userName", name);
        intent.putExtra("dateTime", dateTime);
        intent.putExtra("score", score);
        startActivity(intent);
    }
    private void startCountdownTimer() {
        countdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (startTime > 0) {
                    startTime--;
                    scoreDisplay.setText("Score " + String.valueOf(startTime));
                    handler.postDelayed(this, 1000);
                } else {
                    if (gameViewModel.getPlayerScore() <= 0) {
                        playerLose();;
                    }
                    handler.removeCallbacks(this);
                }
            }
        };
        handler.postDelayed(countdownRunnable, 1000);
    }

    private void playerLose() {
        gameViewModel.reduceScoreEnd();
        Intent inte = new Intent(GameScreen.this, LosingEndScreen.class);
        Bundle playerinfo = new Bundle();
        playerinfo.putInt("score", gameViewModel.getPlayerScore());
        inte.putExtras(playerinfo);
        inte.putExtra("character", character);
        startActivity(inte);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(countdownRunnable);
    }

}