package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Viewawareness extends Activity{
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String> date, venue, program, desr, added;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewawareness);
		l1=(ListView)findViewById(R.id.list);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


	        url ="http://"+sp.getString("ip","")+":5000/and_view_awareness";

	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Viewawareness.this);
	        // Request a string response from the provided URL.
	        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
	            @Override
	            public void onResponse(String response) {
	                // Display the response string.
	                Log.d("+++++++++++++++++",response);
	                try {

	                    JSONArray ar=new JSONArray(response);

	                   
	                    date=new ArrayList<String>();
	                    venue=new ArrayList<String>();
	                    added=new ArrayList<String>();
						program=new ArrayList<String>();
						desr=new ArrayList<String>();

	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);
	             
	                        date.add(jo.getString("date")+ "  " +jo.getString("time"));
	                        venue.add(jo.getString("venue"));
	                        added.add(jo.getString("name"));

							program.add(jo.getString("program_name"));
							desr.add(jo.getString("description"));
						}
	                    l1.setAdapter(new customawareness(Viewawareness.this, date,venue, program, desr, added));
	                   

	                } catch (JSONException e) {
	                	Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_LONG).show();
	                    e.printStackTrace();
	                }
	            }
	        }, new Response.ErrorListener() {
	            @Override
	            public void onErrorResponse(VolleyError error) {
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.vieweduc, menu);
		return true;
	}
}
