package com.example.gamepart2.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gamepart2.R;
import com.example.gamepart2.ViewModels.GameViewModel;

public class MainActivity extends AppCompatActivity{
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.startButton);
        button2 = (Button) findViewById(R.id.endButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfigScreen();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }
    public void openConfigScreen(){
        //GameViewModel gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        //gameViewModel.resetGame();
        Intent intent = new Intent(this, ConfigScreen.class);
        startActivity(intent);
    }

}