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

public class Vieweduc extends Activity implements OnItemClickListener{
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String>name,place,no,eid,email,doc;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewedu);
		l1=(ListView)findViewById(R.id.list);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


	        url ="http://"+sp.getString("ip","")+":5000/viewedu";
	        l1.setOnItemClickListener(this);


	        // Instantiate the RequestQueue.
	        RequestQueue queue = Volley.newRequestQueue(Vieweduc.this);
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


						email=new ArrayList<String>();
						doc=new ArrayList<String>();

	                    for(int i=0;i<ar.length();i++)
	                    {
	                        JSONObject jo=ar.getJSONObject(i);
	             
	                        name.add(jo.getString("educationinstitutename"));
	                        place.add(jo.getString("place")+"\n"+jo.getString("post")+"\n"+jo.getString("pin"));
	                        no.add(jo.getString("contactno"));

							email.add(jo.getString("email"));
							doc.add(jo.getString("document"));


						}
	                    l1.setAdapter(new Customedu(Vieweduc.this,name,place,no));
	                   

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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		Intent i=new Intent(getApplicationContext(),Vieweduc1.class);

		i.putExtra("name",name.get(arg2));
		i.putExtra("place",place.get(arg2));
		i.putExtra("phone",no.get(arg2));
		i.putExtra("email",email.get(arg2));
		i.putExtra("doc",doc.get(arg2));

		startActivity(i);
		
		
	}

}
