package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Emergency extends  BroadcastReceiver{

	SharedPreferences sp;
	public static String ur="";
	public static ArrayList<String> contact ,message;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		sp=PreferenceManager.getDefaultSharedPreferences(context);
		
		try
	    {
	    	if(android.os.Build.VERSION.SDK_INT > 9)
	    	{
	    		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    		StrictMode.setThreadPolicy(policy);
	    	}
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    int volume = (Integer)intent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_VALUE");
		System.out.println(volume+"lllllllllllllllllllllll");
		if(volume!=0)
	    {
			Log.e("In on receive", "In Method:  volumeeeeeeeeeeeeeeeeeeeeee");
			RequestQueue queue = Volley.newRequestQueue(context);
			String url = "http://" + sp.getString("ip", "") + ":5000/uhelp";

			// Request a string response from the provided URL.
			StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					// Display the response string.
					Log.d("+++++++++++++++++", response);
					try {
						JSONObject json = new JSONObject(response);
						String res = json.getString("task");




					} catch (JSONException e) {

						e.printStackTrace();
					}


				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {


				}
			}) {
				@Override
				protected Map<String, String> getParams() {
					Map<String, String> params = new HashMap<String, String>();
					params.put("latitude", ""+LocationService.lati);
					params.put("longitude", ""+LocationService.longi);


					return params;
				}
			};
			// Add the request to the RequestQueue.
			queue.add(stringRequest);
	            
	    }

	}

}
