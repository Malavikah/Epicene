package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Chatting extends Activity {
    LinearLayout lt;
    EditText ed;
    Button b1;
    TextView t1;
    SharedPreferences sh;

    String url="";
  
    String id="";
    String toid="";
    String msg="";
	String date="";
	
    Handler hd;
    static String prv="";

    String lastid;
   
    public static String ur="",ip="";
    TelephonyManager tm;
    SharedPreferences  sp;

    public static ArrayList<String> fromid ,tooid,mesg,datee;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        t1=(TextView)findViewById(R.id.textView1);
        //t2=(TextView)findViewById(R.id.textView2);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");

        t1.setText(getIntent().getStringExtra("name"));

        try{
            if(android.os.Build.VERSION.SDK_INT>9){
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        }catch(Exception e){

        }
        lastid="0";

        ed=(EditText)findViewById(R.id.editText1);
        lt=(LinearLayout)findViewById(R.id.linear1);
        b1=(Button)findViewById(R.id.button1);


        toid=getIntent().getStringExtra("c_fromid");
        id=sh.getString("id", "");

        hd=new Handler();
        hd.post(r);


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
            	RequestQueue queue = Volley.newRequestQueue(Chatting.this);

                String message=ed.getText().toString();
                ur="http://"+sh.getString("ip", "")+":5000/insertchat";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, ur,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {
                            JSONObject json=new JSONObject(response);
                            String res=json.getString("task");
                           
                            if(res.equalsIgnoreCase("success"))
                            {
                                
                                startActivity(new Intent(getApplicationContext(),Addfriends.class));
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
                        params.put("id",sh.getString("id",""));
                       
                        params.put("c_toid",toid);
//                        params.put("c_date",date);
                        params.put("c_text",ed.getText().toString());
                       
                        
                        

                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
			
            }
			 
                

        });


    }


    public Runnable r=new Runnable() {

        @Override
        public void run() {
        	 
            url="http://"+ip+":5000/viewchat";
            Log.d("url===========",url);
            
            
            RequestQueue queue = Volley.newRequestQueue(Chatting.this);
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the response string.
                    Log.d("+++++++++++++++++",response);
                    try {

                        JSONArray ar=new JSONArray(response);

                       
                        fromid=new ArrayList<String>();
                        tooid=new ArrayList<String>();

                        mesg=new ArrayList<String>();
                        datee=new ArrayList<String>();
                        // uid=new ArrayList<String>();

                        lt.removeAllViews();



                        for(int i=0;i<ar.length();i++)
                        {
                            JSONObject c=ar.getJSONObject(i);
                 
                            fromid.add(c.getString("c_fromid"));
                            tooid.add(c.getString("c_toid"));
                            mesg.add(c.getString("c_text"));
                            datee.add(c.getString("c_date"));


                            Log.d("+++++++++++",c+"");


                            TextView tv=new TextView(getApplicationContext());
                            TextView tv1=new TextView(getApplicationContext());
                            if(!c.getString("c_date").equals(prv))
                            {
                                //Toast.makeText(getApplicationContext(), "result is"+prv, Toast.LENGTH_LONG).show();
                                tv1.setText(c.getString("c_date"));
                                tv1.setGravity(Gravity.CENTER);
                                prv=c.getString("c_date");
                            }

                            if(fromid.get(i).equalsIgnoreCase(id)){
                                tv.setTextColor(Color.RED);
                                tv.setText("Me"+": "+mesg.get(i));
                                tv.setGravity(Gravity.RIGHT);

                                tv.setBackgroundColor(Color.WHITE);

                                //tv1.setTextColor(Color.RED);
                                //tv1.setText(date.get(i)+"");


                                tv1.setBackgroundColor(Color.WHITE);



                            }
                            else{
                                tv.setTextColor(Color.BLUE);
                                tv.setText(mesg.get(i));
                                tv.setGravity(Gravity.LEFT);

                                tv.setBackgroundColor(Color.WHITE);

                                //tv1.setTextColor(Color.BLACK);
                                //tv1.setText(date.get(i));
                                //tv1.setGravity(Gravity.CENTER);

                                tv1.setBackgroundColor(Color.WHITE);
                            }

                            lt.addView(tv);
                            lt.addView(tv1);



                        }
                
                       

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
                    
                    params.put("l_id",id);
                    params.put("c_fromid",toid);



                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
            
            
               


                

            
            hd.postDelayed(r, 4000);
        }

    };
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		hd.removeCallbacks(r);
	}



}
