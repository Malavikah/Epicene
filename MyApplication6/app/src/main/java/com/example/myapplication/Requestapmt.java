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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Requestapmt extends Activity implements OnItemClickListener {
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String>name,place,no,laid;
	String lid="";
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestapmt);
		l1=(ListView)findViewById(R.id.list);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


	        url ="http://"+sp.getString("ip","")+":5000/apmtlegal";
	        l1.setOnItemClickListener(this);



	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Requestapmt.this);
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
	                    laid=new ArrayList<String>();


	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);

							laid.add(jo.getString("LOGIN_ID"));

							name.add(jo.getString("NAME"));
							place.add(jo.getString("PLACE"));
							no.add(jo.getString("CONTACTNO"));
	                

	                    }
	                    l1.setAdapter(new Customrqstapmt(Requestapmt.this,name,place,no));
	                   

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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		 lid=laid.get(arg2);
		 RequestQueue queue = Volley.newRequestQueue(Requestapmt.this);
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
                         Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
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
                 params.put("la_id",lid);

                 return params;
             }
         };
         // Add the request to the RequestQueue.
         queue.add(stringRequest);
	
		
	}

}
