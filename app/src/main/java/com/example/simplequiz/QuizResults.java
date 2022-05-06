package com.example.simplequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {
    TextView TxtScore;
    //TextView TxtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        TxtScore = findViewById(R.id.txtscore);
       // TxtStatus = findViewById(R.id.txtStatus);
        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));

        TxtScore.setText(scores);
        //TxtStatus.setText(scores);
       // audio.start();
    }

    public void goToHome(View v){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();

    }
}