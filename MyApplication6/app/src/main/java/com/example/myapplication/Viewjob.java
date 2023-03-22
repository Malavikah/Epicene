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

import android.R.string;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Viewjob extends Activity implements OnItemSelectedListener {
	ListView l1;
	Spinner s1;
	SharedPreferences sp;
	String ar[]={"Part time","Full time"};
	String jtype="";
	String ip="",url="";
	ArrayList<String>place,post,no,jid,types,jemail,pin;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewjob);
		l1=(ListView)findViewById(R.id.listView1);

		s1=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> ad=new ArrayAdapter<String>(Viewjob.this, android.R.layout.simple_list_item_1,ar);
		s1.setAdapter(ad);
		s1.setOnItemSelectedListener(this);
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		jtype=ar[arg2];
		RequestQueue queue = Volley.newRequestQueue(Viewjob.this);
        String ip = null;
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sp.getString("ip","")+":5000/viewjob";


		

        // Request a string response from the provided URL.
        
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                   
                    place=new ArrayList<String>();
                    post=new ArrayList<String>();
                    no=new ArrayList<String>();
                    jid=new ArrayList<String>();
                    types=new ArrayList<String>();
                    jemail=new ArrayList<String>();
                    pin=new ArrayList<String>();



                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
             
                        place.add(jo.getString("place"));
                        post.add(jo.getString("post"));
                        no.add(jo.getString("contactno"));




                    }
                    l1.setAdapter(new Customjob(Viewjob.this,place,post,no));
                   

                } catch (JSONException e) {
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
                params.put("j_type",jtype);
                
                




                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
		
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		String id=jid.get(arg2);
//		// TODO Auto-generated method stub
//		Intent i=new Intent(getApplicationContext(),Viewjob1.class);
//		i.putExtra("type",types.get(arg2));
//        i.putExtra("post",post.get(arg2));
//        i.putExtra("place",place.get(arg2));
//        i.putExtra("pin",pin.get(arg2));
//        i.putExtra("email",jemail.get(arg2));
//        i.putExtra("phn",no.get(arg2));
//
//        startActivity(i);
//
//
//
//
//
//
//	}

	


}
