package com.example.josvlaar.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TriviaHelper implements Response.Listener<JSONArray>, Response.ErrorListener {

    Context context;
    Callback activity;

    public TriviaHelper(Context aContext) {
        context = aContext;
    }

    public void getQuestion(Callback aActivity) {
        activity = aActivity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest("http://jservice.io/api/random", this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            Question question = new Question();
            ArrayList<String> answers = new ArrayList<String>();
            answers.add(response.getJSONObject(0).getString("answer"));
            answers.add("Plausible answer");
            answers.add("Unlikely answer");
            answers.add("Wrong answer");

            question.setQuestion(response.getJSONObject(0).getString("question"));
            question.setCorrectAnswer(response.getJSONObject(0).getString("answer"));
            question.setAnswers(answers);
            question.setCategory(response.getJSONObject(0).getJSONObject("category").getString("title"));

            activity.gotQuestion(question);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void gotQuestion(Question questions);
        void gotMenuError(String message);
    }
}
