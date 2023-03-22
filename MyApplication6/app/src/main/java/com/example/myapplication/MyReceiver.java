package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyReceiver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;
    SharedPreferences sh;
int cnt=0;
    @Override
    public void onReceive(final Context context, final Intent intent) {
        sh= PreferenceManager.getDefaultSharedPreferences(context);
        Log.e("LOB","onReceive");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            cnt++;
            // do whatever you need to do here
            wasScreenOn = false;
            Log.e("LOB","wasScreenOn"+cnt);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            cnt++;
            wasScreenOn = true;
            Log.e("LOB","wasScreenOn"+cnt);

        }

        if(cnt>=3){
            cnt=0;
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://" + sh.getString("ip", "") + ":5000/uhelp";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the response string.
                    Log.d("+++++++++++++++++", response);
                    try {
                        JSONObject json = new JSONObject(response);
                        String res = json.getString("task");




                    } catch (JSONException e) {

                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("latitude", ""+LocationService.lati);
                    params.put("longitude", ""+LocationService.longi);
                    params.put("lid", sh.getString("lid",""));


                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
//            Log.e("LOB","userpresent");
//            Log.e("LOB","wasScreenOn"+wasScreenOn);
//            String url = "http://www.stackoverflow.com";
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setData(Uri.parse(url));
//            context.startActivity(i);
        }
    }
}


