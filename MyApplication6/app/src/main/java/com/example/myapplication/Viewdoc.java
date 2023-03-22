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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Viewdoc extends Activity implements OnItemSelectedListener,OnItemClickListener {
	ListView l1;
	Spinner s1;
	SharedPreferences sp;
	String ar[]={"Gyna","dentist","ortho","psychatrist","pedia"};
	String spec="";
	String ip="",url="";
	ArrayList<String>name,place,no,mid,email,spl;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewdoc);
		l1=(ListView)findViewById(R.id.listView1);
		s1=(Spinner)findViewById(R.id.spinner1);
		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		ArrayAdapter<String> ad=new ArrayAdapter<String>(Viewdoc.this, android.R.layout.simple_list_item_1,ar);
		s1.setAdapter(ad);
		s1.setOnItemSelectedListener(this);
	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		spec=ar[arg2];
		RequestQueue queue = Volley.newRequestQueue(Viewdoc.this);
     
        
        url ="http://"+sp.getString("ip","")+":5000/viewdoc";

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
                    mid=new ArrayList<String>();
                    email=new ArrayList<String>();
                    spl=new ArrayList<String>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
             
                        name.add(jo.getString("m_name"));
                        place.add(jo.getString("m_place"));
                        no.add(jo.getString("m_contactno"));
                        mid.add(jo.getString("m_id"));
                        email.add(jo.getString("m_email"));
                        spl.add(jo.getString("m_specialization"));



                    }
                    l1.setAdapter(new Customdoc(Viewdoc.this,name,place,no));
                   l1.setOnItemClickListener(Viewdoc.this);

                } catch (JSONException e) {
                	 Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Errorsss"+error,Toast.LENGTH_LONG).show();
            }
        }){
        	@Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("specialization",spec);
                




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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		Intent i=new Intent(getApplicationContext(),Viewdoc1.class);

        i.putExtra("m_name",name.get(arg2));
        i.putExtra("m_specialization",spl.get(arg2));
        i.putExtra("m_place",place.get(arg2));
        i.putExtra("m_phno",no.get(arg2));
        i.putExtra("m_email",email.get(arg2));

        startActivity(i);
		
		
	}


}
