package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

public class Viewlegal1 extends Activity implements AdapterView.OnItemClickListener {
	ListView l1;
	SharedPreferences sp;
	String ip="",url="",ssid;
	ArrayList<String> ftime,date,ttime,shid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewlegal1);

		l1=(ListView)findViewById(R.id.listView1);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


		url ="http://"+sp.getString("ip","")+":5000/viewschedule";


		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(Viewlegal1.this);
		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				// Display the response string.
				Log.d("+++++++++++++++++",response);
				try {

					JSONArray ar=new JSONArray(response);


					date=new ArrayList<String>();
					ftime=new ArrayList<String>();
					ttime=new ArrayList<String>();
					shid=new ArrayList<String>();



					for(int i=0;i<ar.length();i++)
					{
						JSONObject jo=ar.getJSONObject(i);
						date.add(jo.getString("date"));

						ftime.add(jo.getString("fromtime"));
						ttime.add(jo.getString("totime"));

						shid.add(jo.getString("schedule_id"));



					}
					l1.setAdapter(new Customlegal(Viewlegal1.this,date,ftime,ttime));

l1.setOnItemClickListener(Viewlegal1.this);
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
				params.put("leid",sp.getString("leid",""));





				return params;
			}
		};
		// Add the request to the RequestQueue.
		queue.add(stringRequest);



	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

ssid=shid.get(position);
		RequestQueue queue = Volley.newRequestQueue(Viewlegal1.this);
		String url ="http://"+sp.getString("ip","")+":5000/apmtrqst";

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				// Display the response string.
				Log.d("+++++++++++++++++",response);
				try {
					JSONObject json=new JSONObject(response);
					String res=json.getString("task");

					if(res.equalsIgnoreCase("success"))
					{
						Toast.makeText(getApplicationContext(),"booking success",Toast.LENGTH_LONG).show();

						startActivity(new Intent(getApplicationContext(),Viewlegal.class));

					}
					else
					{



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
				params.put("u_id",sp.getString("lid", ""));
				params.put("s_id",ssid);

				return params;
			}
		};
		// Add the request to the RequestQueue.
		queue.add(stringRequest);

	}
}
