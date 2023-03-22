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
import android.widget.EditText;
import android.widget.Toast;

public class Viewjob1 extends Activity {
	EditText e1,e2,e3,e4,e5,e6;
	String ip="",url="";
	String jid="";
	SharedPreferences sp;
	ArrayList<String>jobtype,post,place,pin,email,phno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewjob1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);



      


                        
                        e1.setText(getIntent().getStringExtra("type"));
                        e2.setText(getIntent().getStringExtra("post"));
                        e3.setText(getIntent().getStringExtra("place"));
                        e4.setText(getIntent().getStringExtra("pin"));
                        e5.setText(getIntent().getStringExtra("email"));
                        e6.setText(getIntent().getStringExtra("phn"));
             
                        




	}




}
