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
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Viewevent extends Activity {
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String>event,venue,date,time;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewevent);
		l1=(ListView)findViewById(R.id.listView1);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


	        url ="http://"+sp.getString("ip","")+":5000/viewevent";


	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Viewevent.this);
	        // Request a string response from the provided URL.
	        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
	            @Override
	            public void onResponse(String response) {
	                // Display the response string.
	                Log.d("+++++++++++++++++",response);
	                try {

	                    JSONArray ar=new JSONArray(response);

	                   
	                    event=new ArrayList<String>();
	                    venue=new ArrayList<String>();
	                    date=new ArrayList<String>();
	                    time=new ArrayList<String>();


	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);
	             
	                        event.add(jo.getString("et_event_name"));
	                        venue.add(jo.getString("et_venue"));
	                        date.add(jo.getString("et_date"));
	                        time.add(jo.getString("et_date"));
	                

	                    }
//	                    l1.setAdapter(new Customevent(Viewevent.this,event,venue,date,time));
	                   

	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }


	            }
	        }, new Response.ErrorListener() {
	            @Override
	            public void onErrorResponse(VolleyError error) {

	                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
	            }
	        }){
	        	@Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    




                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.viewevent, menu);
//		return true;
//	}

}
