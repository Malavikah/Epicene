package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class reqapment extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqapment);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=findViewById(R.id.editTextTextPersonName27);
        e2=findViewById(R.id.editTextTextPersonName28);
        e3=findViewById(R.id.editTextTextPersonName29);
        e4=findViewById(R.id.editTextTextPersonName30);
        e5=findViewById(R.id.editTextTextPersonName31);
        e6=findViewById(R.id.editTextTextPersonName32);
        e7=findViewById(R.id.editTextTextPersonName34);

        final String type=e1.getText().toString();
        final String name=e2.getText().toString();
        final String place=e3.getText().toString();
        final String post=e4.getText().toString();
        final String pin=e5.getText().toString();
        final String phoneno=e6.getText().toString();
        final String email=e7.getText().toString();



        RequestQueue queue = Volley.newRequestQueue(reqapment.this);
        String url ="http://"+sh.getString("ip","")+":5000/login";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
                    JSONObject json=new JSONObject(response);
                    String res=json.getString("task");

                    if(res.equalsIgnoreCase("failed"))
                    {
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    }
                    else
                    {

                        SharedPreferences.Editor ed1=sh.edit();
                        ed1.putString("lid",res);
                        ed1.commit();

                        startActivity(new Intent(getApplicationContext(),userhome.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error"+error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("type",type);
                params.put("name",name);
                params.put("place",place);
                params.put("post",post);
                params.put("pin",pin);
                params.put("phoneno",phoneno);
                params.put("email",email);



                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);





    }
}