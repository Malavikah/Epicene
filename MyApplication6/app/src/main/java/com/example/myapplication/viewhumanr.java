package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class viewhumanr extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhumanr);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    }
}