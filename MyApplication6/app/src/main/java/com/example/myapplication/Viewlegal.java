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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Viewlegal extends Activity implements AdapterView.OnItemClickListener {
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String>name,place,no,post,pin,email,lid;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewlegal);
		l1=(ListView)findViewById(R.id.listView1);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


	        url ="http://"+sp.getString("ip","")+":5000/viewlegal";


	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Viewlegal.this);
	        // Request a string response from the provided URL.
	        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
	            @Override
	            public void onResponse(String response) {
	                // Display the response string.
	                Log.d("+++++++++++++++++",response);
	                try {

	                    JSONArray ar=new JSONArray(response);

	                   
	                    name=new ArrayList<String>();
	                    place=new ArrayList<String>();
	                    no=new ArrayList<String>();
						post=new ArrayList<String>();
						pin=new ArrayList<String>();
						email=new ArrayList<String>();
						lid=new ArrayList<String>();


	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);
							lid.add(jo.getString("LOGIN_ID"));

							name.add(jo.getString("NAME"));
	                        place.add(jo.getString("PLACE"));
	                        no.add(jo.getString("CONTACTNO"));
							post.add(jo.getString("POST"));
							pin.add(jo.getString("PIN"));
							email.add(jo.getString("EMAIL"));


						}
	                    l1.setAdapter(new Customlegal(Viewlegal.this,name,place,no));
	                   
						l1.setOnItemClickListener(Viewlegal.this);
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


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SharedPreferences.Editor ed=sp.edit();
		ed.putString("leid",lid.get(position));
		ed.commit();


		Intent i=new Intent(getApplicationContext(),Viewlegal1.class);
		startActivity(i);


	}
}
