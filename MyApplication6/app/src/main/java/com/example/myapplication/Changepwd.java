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

public class Changepwd extends Activity {

	
	EditText e1,e2,e3;
	Button b1;
	SharedPreferences sp;
	String curpwd,conpwd,newpwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepwd);
		
		 e1=(EditText)findViewById(R.id.editText1);
	     e2=(EditText)findViewById(R.id.editText2);
	     e3=(EditText)findViewById(R.id.editText3);
	   
	     b1=(Button)findViewById(R.id.button1);
	     sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	     b1.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				curpwd=e1.getText().toString();
				newpwd=e2.getText().toString();
				conpwd=e3.getText().toString();
				
			    if(curpwd.equalsIgnoreCase(""))
						{
							e1.setError("Enter Current Password");
						}
					    else if(!curpwd.matches("^[a-zA-Z]*$"))
					    {
					    	e1.setError("characters allowed");
					    }
					    else if(newpwd.equalsIgnoreCase(""))
						{
							e2.setError("Enter New Password");
						}
						else if(newpwd.length()<3)
						{
							e2.setError("more than 3 characters required");
         					e2.requestFocus();
						

						}
						else if(conpwd.equalsIgnoreCase(""))
						{
							e3.setError("Reenter Password");
						}
						else
						{
							
						
			
						
				
				 // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Changepwd.this);
                String url ="http://"+sp.getString("ip","")+":5000/changepassword";

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
                            	Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                                
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
                        params.put("lid",sp.getString("id",""));
                        params.put("newpwd",newpwd);
                        params.put("currentpwd",curpwd);
                     
                        

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
