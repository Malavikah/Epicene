package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
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



public class Viewmeet extends Activity {
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String> time,place,description;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewmeet);
		l1=(ListView)findViewById(R.id.list);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		

//		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        url ="http://"+sp.getString("ip","")+":5000/viewmeet";

        RequestQueue queue = Volley.newRequestQueue(Viewmeet.this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                   
                    time=new ArrayList<String>();
                    place=new ArrayList<String>();
                    description=new ArrayList<String>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
             
                        time.add(jo.getString("mt_meeting_time"));
                        place.add(jo.getString("mt_place"));
                        description.add(jo.getString("mt_description"));
                

                    }
                    l1.setAdapter(new Custommeet(Viewmeet.this,time,place,description));
                   

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


}
