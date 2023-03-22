package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class userchangepassword extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchangepassword);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=findViewById(R.id.editTextTextPersonName15);
        e2=findViewById(R.id.editTextTextPersonName40);
        e3=findViewById(R.id.editTextTextPersonName41);
        b1=findViewById(R.id.button5);




        b1.setOnClickListener(new View.OnClickListener()


        {
            @Override
            public void onClick(View view) {


                final String currentpassword=e1.getText().toString();
                final String newpassword=e2.getText().toString();
                final String conformpassword=e3.getText().toString();


            }
        });
    }
}
