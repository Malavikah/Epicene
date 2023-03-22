package com.example.myapplication;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
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

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Vieweduc1 extends Activity {
	EditText e1,e2,e3,e4,e5,e6;
	String ip="",url="";
	String eid="";
	SharedPreferences sp;
	TextView t1;
	ArrayList<String>instname,place,post,pin,no,email;

	ProgressDialog mProgressDialog;
	private PowerManager.WakeLock mWakeLock;
	static final int DIALOG_DOWNLOAD_PROGRESS = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vieweduc1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);

		t1=(TextView) findViewById(R.id.textView);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);

		sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


       
        e1.setEnabled(false);
        e2.setEnabled(false);

        e5.setEnabled(false);
        e6.setEnabled(false);
        
       



                        
                        e1.setText(getIntent().getStringExtra("name"));
                        e2.setText(getIntent().getStringExtra("place"));

                        t1.setText(getIntent().getStringExtra("doc"));
						e5.setText(getIntent().getStringExtra("phone"));
						e6.setText(getIntent().getStringExtra("email"));
             
                        

t1.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		SharedPreferences.Editor ed=sp.edit();
		ed.putString("orginal",getIntent().getStringExtra("doc"));
		ed.commit();

		startDownload(getIntent().getStringExtra("doc"));

	}
});




	}

	private void startDownload(String fn) {
		String url = "http://"+sp.getString("ip", "")+":5000/static/upload/"+fn;

		new Vieweduc1.DownloadFileAsync().execute(url);
	}
	class DownloadFileAsync extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
					getClass().getName());
			mWakeLock.acquire();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}
		@Override
		protected String doInBackground(String... aurl) {
			int count;

			try {
				Log.d("aurllll",aurl[0]);

				URL url = new URL(aurl[0]);
				URLConnection conexion = url.openConnection();
				conexion.connect();

				int lenghtOfFile = conexion.getContentLength();
				Log.d("ANDRO_ASYNC", "Length of file: " + lenghtOfFile);

//	String filename = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date())+"ticket.html";
				InputStream input = new BufferedInputStream(url.openStream());
				File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
				File myFile = null;
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
					myFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+sp.getString("orginal", ""));
				}
//                File myFile = new File(storageDir, "icon.jpg");
//                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + sh.getString("orginal", ""));
				OutputStream output =new FileOutputStream(myFile);
				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress(""+(int)((total*100)/lenghtOfFile));
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC",progress[0]);
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
		}
	}


	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DIALOG_DOWNLOAD_PROGRESS:
				mProgressDialog = new ProgressDialog(this);
				mProgressDialog.setMessage("Downloading File...");
				mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
				return mProgressDialog;
		}
		return null;
	}
}

