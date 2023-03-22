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

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Addfriends extends Activity implements OnItemClickListener {
	ListView l1;
	ImageView i1;
	SharedPreferences sp;
	
	String ip="",url="";
	ArrayList<String>photo,name,lid;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addfriends);
		
		l1=(ListView)findViewById(R.id.listView1);
		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		


        url ="http://"+sp.getString("ip","")+":5000/addfriends";
        l1.setOnItemClickListener(this);

if(Build.VERSION.SDK_INT>9)
{
	StrictMode.ThreadPolicy polocy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	StrictMode.setThreadPolicy(polocy);
}
        RequestQueue queue = Volley.newRequestQueue(Addfriends.this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                   
                    photo=new ArrayList<String>();
                   
                    name=new ArrayList<String>();
                    lid=new ArrayList<String>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
             
                        photo.add(jo.getString("u_photo"));
                       
                        name.add(jo.getString("u_name"));
                        lid.add(jo.getString("l_id"));
                

                    }
             l1.setAdapter(new Customaddfriends(Addfriends.this,photo,name));
                   

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_LONG).show();
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
                params.put("id",sp.getString("lid",""));
                




                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		Intent i= new Intent(getApplicationContext(),Chatting.class);
		i.putExtra("c_fromid", lid.get(arg2));
		i.putExtra("name",name.get(arg2) );
		startActivity(i);
		
	}

}
