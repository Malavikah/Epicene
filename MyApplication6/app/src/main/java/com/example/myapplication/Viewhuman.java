package com.example.myapplication;

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
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Viewhuman extends Activity  {
    ListView l1;
    Spinner s1;
    SharedPreferences sp;

    String hrtype = "";
    String ip = "", url = "";
    ArrayList<String> des,hid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhuman);
        l1 = (ListView) findViewById(R.id.listView1);


        RequestQueue queue = Volley.newRequestQueue(Viewhuman.this);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = "http://" + sp.getString("ip", "") + ":5000/viewhuman";


        // Request a string response from the provided URL.

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);


                    des = new ArrayList<String>();
                   hid=new ArrayList<String>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);

                        des.add("HUMAN RIGHT:"+jo.getString("hr_human_rights")+"\nDESCRIPTION:"+jo.getString("hr_description"));




                    }
                    l1.setAdapter(new Customhuman(Viewhuman.this, des));


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error" + e, Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("hr_human_rights", hrtype);


                return params;
            }


        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


}




