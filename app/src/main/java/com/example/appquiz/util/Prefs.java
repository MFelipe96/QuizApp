package com.example.appquiz.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

public class Prefs {

    private SharedPreferences preferences;
    private static final String MESSAGE_ID = "message_prefes";

    public Prefs(Activity activity) {
        this.preferences = activity.getSharedPreferences(MESSAGE_ID, activity.MODE_PRIVATE);
    }

    public void saveScore(int score){
        int scoreAtual = score;

        int ultimoScore = preferences.getInt("message", 0);

        if(scoreAtual > ultimoScore){
            preferences.edit().putInt("message", scoreAtual).apply();
        }
    }

    public int getHighScore(){
        int value = 0;
        if(String.valueOf(value) != null)
            return preferences.getInt("message", 0);
        else
            return value;
    }

}


