package com.example.josvlaar.trivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity implements HighscoresHelper.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Log.d("DEBUG", "Creating a new helper and getting highscores.");
        new HighscoresHelper().getHighscores(this);
    }

    @Override
    public void gotHighscores(ArrayList<Highscore> scores) {
        HighscoresAdapter adapter = new HighscoresAdapter(this, scores);
        ListView list = findViewById(R.id.scoreList);
        list.setAdapter(adapter);
    }

    @Override
    public void gotHighscoresError(String message) {
        Log.d("DEBUG", "ERROR: " + message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }
}
