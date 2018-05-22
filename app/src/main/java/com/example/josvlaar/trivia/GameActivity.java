package com.example.josvlaar.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements TriviaHelper.Callback {

    private int score = 0;
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new TriviaHelper(this).getQuestion(this);
    }

    @Override
    public void gotQuestion(Question question) {

        currentQuestion = question;

        TextView questionView = findViewById(R.id.question);
        TextView categoryView = findViewById(R.id.category);

        questionView.setText(question.getQuestion());
        categoryView.setText(question.getCategory());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.answer_list_item, question.getAnswers());
        ListView listView = findViewById(R.id.answers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnListItemClick(this));
    }

    @Override
    public void gotMenuError(String message) {
        Log.d("DEBUG", "ERROR: " + message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class OnListItemClick implements AdapterView.OnItemClickListener {

        private GameActivity context;

        OnListItemClick(Context aContext) {
            context = (GameActivity) aContext;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String answer = (String) parent.getItemAtPosition(position);

            if(answer.equals(currentQuestion.getCorrectAnswer())) {
                score += 1;
                new TriviaHelper(context).getQuestion(context);
            } else {
                // end the round
                Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        }
    }
}
