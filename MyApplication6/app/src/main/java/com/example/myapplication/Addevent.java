package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addevent extends Activity {
	EditText e1,e2,e3,e4,e5;
	Button b1;
	SharedPreferences sp;
	String ip="",url="",event="",des="",venue="",date="",time="",id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addevent);
		 e1=(EditText)findViewById(R.id.editText1);
	     e2=(EditText)findViewById(R.id.editText2);
	     e3=(EditText)findViewById(R.id.editText3);
	     e4=(EditText)findViewById(R.id.editText4);  
	     e5=(EditText)findViewById(R.id.editText5); 
	     b1=(Button)findViewById(R.id.button1);
	     sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	     b1.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				event=e1.getText().toString();
				des=e2.getText().toString();
				venue=e3.getText().toString();
				date=e4.getText().toString();
				time=e5.getText().toString();
				
			    if(event.equalsIgnoreCase(""))
						{
							e1.setError("Enter event name");
						}
					    else if(!event.matches("^[a-zA-Z]*$"))
					    {
					    	e1.setError("characters allowed");
					    }
						else if(des.equalsIgnoreCase(""))
						{
							e2.setError("Enter event description");
						}
						else if(venue.equalsIgnoreCase(""))
						{
							e3.setError("Enter venue");
						}
						else if(date.equalsIgnoreCase(""))
						{
							e4.setError("Enter date of event");
						}
						else if(time.equalsIgnoreCase(""))
						{
							e5.setError("Enter time of event");
						}
						else
						{
					    

				 // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Addevent.this);
                String url ="http://"+sp.getString("ip","")+":5000/addevent";

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
                        params.put("lid",sp.getString("lid",""));
                        params.put("event",event);
                        params.put("description",des);
                        params.put("venue",venue);
                        params.put("date",date);
                        params.put("time",time);
                        
                        

                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
			
            }
			}
                

				
			
		});
	}



}
