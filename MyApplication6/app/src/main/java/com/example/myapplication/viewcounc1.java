package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class viewcounc1 extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcounc1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=findViewById(R.id.editTextTextPersonName12);
        e2=findViewById(R.id.editTextTextPersonName13);
        e3=findViewById(R.id.editTextTextPersonName22);
        e4=findViewById(R.id.editTextTextPersonName24);
        e5=findViewById(R.id.editTextTextPersonName25);
        e6=findViewById(R.id.editTextTextPersonName26);


        final String programname=e1.getText().toString();
        final String venue=e2.getText().toString();
        final String date=e3.getText().toString();
        final String time=e4.getText().toString();
        final String contactno=e5.getText().toString();
        final String description=e6.getText().toString();
    }
}