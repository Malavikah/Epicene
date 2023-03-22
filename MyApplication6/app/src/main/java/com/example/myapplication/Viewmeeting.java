package com.example.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
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

public class Viewmeeting extends Activity{
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String> date, place, desr, added;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewmeeting);
		l1=(ListView)findViewById(R.id.list);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


	        url ="http://"+sp.getString("ip","")+":5000/and_view_meeting";

	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Viewmeeting.this);
	        // Request a string response from the provided URL.
	        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
	            @Override
	            public void onResponse(String response) {
	                // Display the response string.
	                Log.d("+++++++++++++++++",response);
	                try {

	                    JSONArray ar=new JSONArray(response);

	                   
	                    date=new ArrayList<String>();
	                    place=new ArrayList<String>();
	                    added=new ArrayList<String>();
						desr=new ArrayList<String>();

	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);
	             
	                        date.add(jo.getString("meeting_datetime"));
	                        place.add(jo.getString("place"));
	                        added.add(jo.getString("NAME"));
							desr.add(jo.getString("description"));
						}
	                    l1.setAdapter(new custom_meeting(Viewmeeting.this, date,place, desr, added));
	                   

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
