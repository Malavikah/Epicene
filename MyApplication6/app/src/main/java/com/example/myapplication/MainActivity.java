package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText e1;
Button b1;
SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1 = findViewById(R.id.editTextTextPersonName);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String setip = e1.getText().toString();


                if(setip.equals(""))
                {
                    e1.setError("enter ip");
                    e1.requestFocus();
                }
                else {
                    SharedPreferences.Editor ed1=sh.edit();
                    ed1.putString("ip",setip);
                    ed1.commit();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }

            }
        });
    }

}




