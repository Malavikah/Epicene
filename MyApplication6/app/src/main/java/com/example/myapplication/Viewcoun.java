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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Viewcoun extends Activity implements OnItemClickListener  {
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String>date,pgmname,venue,des,time,phone;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewcoun);
		l1=(ListView)findViewById(R.id.listView1);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		


	        url ="http://"+sp.getString("ip","")+":5000/viewcoun";
	        l1.setOnItemClickListener(this);


	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Viewcoun.this);
	        // Request a string response from the provided URL.
	        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
	            @Override
	            public void onResponse(String response) {
	                // Display the response string.
	                Log.d("+++++++++++++++++",response);
	                try {

	                    JSONArray ar=new JSONArray(response);

	                   
	                    date=new ArrayList<String>();
	                    pgmname=new ArrayList<String>();
	                    venue=new ArrayList<String>();
	                    time=new ArrayList<String>();
						phone=new ArrayList<String>();
						des=new ArrayList<String>();



	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);
	             
	                        date.add(jo.getString("date"));
	                        pgmname.add(jo.getString("counsellingprogram"));
	                        venue.add(jo.getString("place"));
	                        time.add(jo.getString("time"));
							phone.add(jo.getString("contactno"));
							des.add(jo.getString("description"));


						}
	                    l1.setAdapter(new Customcoun(Viewcoun.this,date,pgmname,venue));
	                   

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
//		getMenuInflater().inflate(R.menu.viewcoun, menu);
//		return true;
//	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		Intent i=new Intent(getApplicationContext(),Viewcoun1.class);
		i.putExtra("pname",pgmname.get(arg2));
		i.putExtra("date",date.get(arg2));
		i.putExtra("time",time.get(arg2));
		i.putExtra("des",des.get(arg2));
		i.putExtra("venue",venue.get(arg2));
		i.putExtra("phone",phone.get(arg2));
		startActivity(i);
		
	}

}
