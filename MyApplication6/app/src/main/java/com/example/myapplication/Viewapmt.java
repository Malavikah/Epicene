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
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Viewapmt extends Activity implements OnItemClickListener{
	ListView l1;
	SharedPreferences sp;
	String ip="",url="";
	ArrayList<String>date,time,name;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewapmt);
		l1=(ListView)findViewById(R.id.listView1);
		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        url ="http://"+sp.getString("ip","")+":5000/viewapmt";
        l1.setOnItemClickListener(this);


        RequestQueue queue = Volley.newRequestQueue(Viewapmt.this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                   
                    date=new ArrayList<String>();
                    time=new ArrayList<String>();
                    name=new ArrayList<String>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
             
                        date.add(jo.getString("booking_date"));
                        time.add(jo.getString("status"));
                        name.add(jo.getString("NAME"));
                

                    }
             l1.setAdapter(new Customapmt(Viewapmt.this,date,time,name));
                   

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
//
//@Override
//public boolean onCreateOptionsMenu(Menu menu) {
//	// Inflate the menu; this adds items to the action bar if it is present.
//	getMenuInflater().inflate(R.menu.viewapmt, menu);
//	return true;
//}

@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	
}

}
