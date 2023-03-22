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
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgotpwd extends Activity {
	EditText e1;
	Button b1;
	SharedPreferences sp;
	String ip="",url="",email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpwd);
		 e1=(EditText)findViewById(R.id.editText1);
	     b1=(Button)findViewById(R.id.button1);
	     sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	     b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				email=e1.getText().toString();
				
				if(email.equalsIgnoreCase(""))
				{
					e1.setError("Enter Your Email");
				}
				else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
				{
					e1.setError("Enter Valid Email");
					e1.requestFocus();
				}
				else
				{

				
			      // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Forgotpwd.this);
                String url ="http://"+sp.getString("ip","")+":5000/forgotpassword";

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
                            	
                       
                                startActivity(new Intent(getApplicationContext(),Login.class));
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
                       
                        params.put("email",email);

                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
          
            }
			}
        });
    
  

  
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.forgotpwd, menu);
//		return true;
//	}

}
