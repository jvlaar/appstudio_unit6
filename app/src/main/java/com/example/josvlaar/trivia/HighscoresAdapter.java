package com.example.josvlaar.trivia;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HighscoresAdapter extends ArrayAdapter {

    ArrayList<Highscore> items;

    public HighscoresAdapter(Context context, ArrayList<Highscore> array) {
        super(context, R.layout.score_item, array);
        items = array;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.score);
        Log.d("DEBUG", name + "");
        Log.d("DEBUG",  price + "");

        name.setText(items.get(position).getName());
        price.setText(items.get(position).getScore() + "");

        return convertView;
    }
}

