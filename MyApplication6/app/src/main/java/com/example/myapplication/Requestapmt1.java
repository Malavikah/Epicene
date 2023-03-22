package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class Requestapmt1 extends Activity {
	EditText e1,e2,e3,e4,e5,e6,e7;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestapmt1);
		e1=(EditText)findViewById(R.id.editText1);
	    e2=(EditText)findViewById(R.id.editText2);
	    e3=(EditText)findViewById(R.id.editText3);
	    e4=(EditText)findViewById(R.id.editText4);
	    e5=(EditText)findViewById(R.id.editText5);
	    e6=(EditText)findViewById(R.id.editText6);
	    e7=(EditText)findViewById(R.id.editText7);
	    b1=(Button)findViewById(R.id.button1);
	    
	    
	}


}
