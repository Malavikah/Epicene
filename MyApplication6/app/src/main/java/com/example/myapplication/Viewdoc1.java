package com.example.myapplication;

import java.util.ArrayList;
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
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class Viewdoc1 extends Activity {
	EditText e1,e2,e3,e4,e5;
	String ip="",url="";
	String mid="";
	SharedPreferences sp;
	ArrayList<String>name,specialization,place,no,email;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewdoc1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);









                        
                        e1.setText(getIntent().getStringExtra("m_name"));
                        e2.setText(getIntent().getStringExtra("m_specialization"));
                        e3.setText(getIntent().getStringExtra("m_place"));
                        e4.setText(getIntent().getStringExtra("m_phno"));
                        e5.setText(getIntent().getStringExtra("m_email"));
                       
             
                        




	}



}
