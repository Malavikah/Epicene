package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class userhome extends Activity {
    Button b1,b2,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15, b_awareness, b_medical, b_meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);

        b2=(Button)findViewById(R.id.button1);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);

        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
//        b10=(Button)findViewById(R.id.button10);
        b11=(Button)findViewById(R.id.button11);
        b12=(Button)findViewById(R.id.button12);
        b13=(Button)findViewById(R.id.button13);
        b14=(Button)findViewById(R.id.button14);
        b15=(Button)findViewById(R.id.button15);
        b_awareness=(Button)findViewById(R.id.button_awareness);
        b_medical=(Button)findViewById(R.id.button_medical);
        b_meeting=(Button)findViewById(R.id.button_meetings);

        b_awareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewawareness.class);
                startActivity(i);
            }
        });

        b_medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewmedical.class);
                startActivity(i);
            }
        });

        b_meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewmeeting.class);
                startActivity(i);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Vieweduc.class);
                startActivity(i);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewjob.class);
                startActivity(i);

            }
        });


        b7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewhealth.class);
                startActivity(i);

            }
        });



        b8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewcoun.class);
                startActivity(i);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),chatwithfriends.class);
                startActivity(i);

            }
        });


        b9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewlegal.class);
                startActivity(i);

            }
        });

        b14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewhuman.class);
                startActivity(i);

            }
        });

b12.setVisibility(View.GONE);
        b12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Requestapmt.class);
                startActivity(i);

            }
        });

        b13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Viewapmt.class);
                startActivity(i);

            }
        });


        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Manageevent.class);
                startActivity(i);

            }
        });

















        b15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i= new Intent(getApplicationContext(),Login.class);
                startActivity(i);

            }
        });
    }



}
