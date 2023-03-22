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

public class Viewcoun1 extends Activity {
	EditText e1,e2,e3,e4,e5,e6;
	String ip="",url="";
	String cid="";
	SharedPreferences sp;
	ArrayList<String>pgmname,venue,date,time,no,des;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewcoun1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);
		


       
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        e6.setEnabled(false);
        
       



                        
                        e1.setText(getIntent().getStringExtra("pname"));
                        e2.setText(getIntent().getStringExtra("venue"));
                        e3.setText(getIntent().getStringExtra("date"));
                        e4.setText(getIntent().getStringExtra("time"));
                        e5.setText(getIntent().getStringExtra("phone"));
                        e6.setText(getIntent().getStringExtra("des"));
             
                        




	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.viewcoun1, menu);
//		return true;
//	}

}
