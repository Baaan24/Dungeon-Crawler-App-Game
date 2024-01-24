package com.example.gamepart2.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamepart2.R;

public class ConfigScreen extends AppCompatActivity {
    private String selectedDifficulty;
    private EditText playerName;
    private RadioGroup difficultyGroup;
    RadioButton easy;
    RadioButton medium;
    RadioButton hard;
    RadioButton radioButton;
    private ImageButton imageButton;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private Button continueButton;
    private TextView summaryTextView; // TextView for displaying the summary
    private ImageView characterSprite;
    private String diff;
    private int HP;

    private int selectedCharacter;
    private boolean enteredNameCorrectly;
    private String name;
    int image;
    private TextView summaryTextView2;
    public int spriteNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        characterSprite = findViewById(R.id.characterSprite);
        playerName = findViewById(R.id.playerName);

        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        continueButton = findViewById(R.id.nextButton);
        summaryTextView = findViewById(R.id.summaryTextView1);
        Button enterBtn = findViewById(R.id.enterBtn);
        summaryTextView2 = findViewById(R.id.summaryTextView2);

        difficultyGroup = findViewById(R.id.selectDifficulty);
        easy = findViewById(R.id.optionEasy);
        medium = findViewById(R.id.optionMedium);
        hard = findViewById(R.id.optionHard);

        characterSprite = findViewById(R.id.characterSprite);

        // Set OnClickListener for each ImageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the image in the ImageView when ImageButton1 is clicked
                image = 1;
                characterSprite.setImageResource(R.drawable.avatar1);
                spriteNum = 1;
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the image in the ImageView when ImageButton2 is clicked
                image = 2;
                characterSprite.setImageResource(R.drawable.avatar2);
                spriteNum = 2;
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the image in the ImageView when ImageButton3 is clicked
                image = 3;
                characterSprite.setImageResource(R.drawable.avatar3);
                spriteNum = 3;
            }
        });

        enterBtn.setOnClickListener(v -> {
            name = playerName.getText().toString();
            if (name == null) {
                playerName.setText("Error");
            } else if (name.trim().isEmpty()) {
                playerName.setText("Error");
            } else {
                name = playerName.getText().toString();
                String nameText = "Player Name: " + name;
                summaryTextView.setText(nameText);
                enteredNameCorrectly = true;
            }
        });

        difficultyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkDifficulty();
            }
        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start a new activity or perform other actions here
                // For example, you can pass the calculated HP to the next activity using an Intent.

                if (!isNameValid(name)) {
                    Toast.makeText(ConfigScreen.this, "Please enter a valid name.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if no character sprite is selected (assuming 'image' should be non-zero)
                if (image == 0) {
                    Toast.makeText(ConfigScreen.this, "Please select a character sprite.", Toast.LENGTH_SHORT).show();
                    return; // Exit the method to prevent moving to the next screen
                }

                calculateHP();
                Intent intent = new Intent(ConfigScreen.this, GameScreen.class);
                intent.putExtra("HP", HP);
                intent.putExtra("name",name);
                intent.putExtra("characterSprite", image);
                intent.putExtra("spriteNumber", spriteNum);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean isNameValid(String name) {
        if (name == null || name.trim().isEmpty()|| "Error".equals(name)){
            return false;
        } else {
            return true;
        }
    }



    private void checkDifficulty() {
        int radioId = difficultyGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioId);
        String diffText = "Selected Difficulty: " + radioButton.getText();
        summaryTextView2.setText(diffText);
    }


    public void calculateHP() {
        int selectedId = difficultyGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.optionEasy) {
            HP = 200;
        } else if (selectedId == R.id.optionMedium) {
            HP = 150;
        } else if (selectedId == R.id.optionHard) {
            HP = 100;
        } else {
            HP = 50;
        }
    }

    public void setSelectedDifficulty(String difficulty) {
        this.selectedDifficulty = difficulty;
    }
    
    public int getHP() {
        return HP;
    }

    public void checkDifficulty(View v){
        int radioId = difficultyGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String diffText = "Selected Difficulty: " + radioButton.getText();
        summaryTextView2.setText(diffText);
    }

    public int getSpriteNumCon() {
        return this.spriteNum;
    }
}
