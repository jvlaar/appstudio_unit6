package com.example.josvlaar.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(score + "");

        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new OnButtonClickListener());
    }

    private class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            TextView nameInput = findViewById(R.id.name);
            String name = nameInput.getText() + "";

            Highscore highscore = new Highscore(name, score);
            new HighscoresHelper().postNewHighscore(highscore);

            Intent intent = new Intent(GameOverActivity.this, HighScoreActivity.class);
            startActivity(intent);
        }
    }
}
