package com.example.josvlaar.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HighscoresHelper {
    private Context context;
    private FirebaseDatabase database;

    public HighscoresHelper() {
        Log.d("DEBUG", "A new helper has been created!");
        database = FirebaseDatabase.getInstance();
    }

    public void postNewHighscore(Highscore score) {
        Log.d("DEBUG", "Posting a new highscore!");
        database.getReference("scores").child(score.getName()).setValue(score);
    }

    public void getHighscores(final Callback activity){
        Log.d("DEBUG", "Getting highscores!");
        ValueEventListener scoreListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                ArrayList<Highscore> scores = new ArrayList<Highscore>();

                for (DataSnapshot entry : dataSnapshot.getChildren()) {
                    Highscore score = entry.getValue(Highscore.class);
                    scores.add(score);
                }
                activity.gotHighscores(scores);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("DEBUG", "loadPost:onCancelled", databaseError.toException());
                activity.gotHighscoresError(databaseError.getMessage());
            }
        };

        Log.d("DEBUG", "Adding single event listener!");
        database.getReference("scores").addListenerForSingleValueEvent(scoreListener);
    }

    public interface Callback {
        void gotHighscores(ArrayList<Highscore> scores);
        void gotHighscoresError(String message);
    }
}
