package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class userregistration extends AppCompatActivity {

    EditText E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11;
    Button B1,B2;
    RadioButton R1,R2;
    SharedPreferences sh;
    String FN,MN,LN,PN,EI,DOB,PLACE,DS,UN,PS,CPS,GN,url,post,phn;
    ImageView im;
    private static final int FILE_SELECT_CODE = 0;
    String res;
    String fileName="",path="";

    TextView t1;
    public static final int REQUEST_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregistration);
        E1=findViewById(R.id.editTextTextPersonName4);
        E2=findViewById(R.id.editTextTextPersonName90);
        E3=findViewById(R.id.editTextTextPersonName92);
        E4=findViewById(R.id.editTextTextPersonName20);
        E5=findViewById(R.id.editTextTextPersonName10);
        E6=findViewById(R.id.editTextTextPersonName99);


        E7=findViewById(R.id.editTextTextPersonName9);

        E9=findViewById(R.id.editTextTextPersonName12);
        E10=findViewById(R.id.editTextTextPersonName13);
        im=findViewById(R.id.imageView3);


        B1=findViewById(R.id.button11);
        B2=findViewById(R.id.button6);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //getting all types of files
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, ""), FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FN = E1.getText().toString();
                post = E2.getText().toString();
                PN = E3.getText().toString();
                phn=E4.getText().toString();
                EI = E5.getText().toString();
                PLACE = E7.getText().toString();
                UN = E9.getText().toString();
                PS = E10.getText().toString();
                DS = E6.getText().toString();



                if (FN.equals("")) {
                    E1.setError("enter first name ");
                    E1.requestFocus();
                }
                else if(!FN.matches("^[a-zA-Z]*$"))
                {
                    E1.setError("characters allowed");
                    E1.requestFocus();

                }

                else if (PLACE.equals("")) {
                    E7.setError("enter place");
                    E7.requestFocus();


                }
                else if(!PLACE.matches("^[a-zA-Z]*$"))
                {
                    E7.setError("characters allowed");
                    E7.requestFocus();

                }
                else if (post.equals("")) {
                    E2.setError("enter enter post");
                    E2.requestFocus();

                }
                else if(!post.matches("^[a-zA-Z]*$"))
                {
                    E2.setError("characters allowed");
                    E2.requestFocus();

                }else if (PN.equals("")) {
                    E3.setError("enter pin");
                    E3.requestFocus();

                }
                else if(PN.length()<6)
                {
                    E3.setError("Minimum 6 nos required");
                    E3.requestFocus();

                }else if (phn.equals("")) {
                    E4.setError("enter phone number");
                    E4.requestFocus();

                }
                else if(phn.length()<10)
                {
                    E4.setError("Minimum 10 nos required");
                    E4.requestFocus();
                }


                else if(EI.equalsIgnoreCase(""))
                {
                    E5.setError("Enter Your Email");
                    E5.requestFocus();

                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(EI).matches())
                {
                    E5.setError("Enter Valid Email");
                    E5.requestFocus();
                }


                else if (DS.equals("")) {
                    E6.setError("enter district");
                    E6.requestFocus();


                }
                else if (UN.equals("")) {
                    E9.setError("enter username");
                    E9.requestFocus();

                } else if (PS.equals("")) {
                    E10.setError("enter password");
                    E10.requestFocus();

                }
                 else {





//
//                    RequestQueue queue = Volley.newRequestQueue(userregistration.this);
//                    String url ="http://"+sh.getString("ip","")+":5000/reg";
//
//                    // Request a string response from the provided URL.
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // Display the response string.
//                            Log.d("+++++++++++++++++",response);
//                            try {
//                                JSONObject json=new JSONObject(response);
//                                String res=json.getString("task");
//
//                                if(res.equalsIgnoreCase("success"))
//                                {
//                                    startActivity(new Intent(getApplicationContext(),Login.class));
//                                }
//                                else
//                                {
//                                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
//                        }
//                    }){
//                        @Override
//                        protected Map<String, String> getParams()
//                        {
//                            Map<String, String>  params = new HashMap<String, String>();
//                            params.put("name",FN);
//                            params.put("place",PLACE);
//                            params.put("post",post);
//                            params.put("pin",PN);
//                            params.put("phno",phn);
//                            params.put("email",EI);
//                            params.put("uname",UN);
//                            params.put("pass",PS);
//                            params.put("ds",DS);
//
//
//                            return params;
//                        }
//                    };
//                    // Add the request to the RequestQueue.
//                    queue.add(stringRequest);



                    res=uploadFile(path);
                    if(res.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "success" , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),  res, Toast.LENGTH_LONG).show();

                    }





                }
            }
        });



    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        path = FileUtils.getPath(this, uri);
                        //e4.setText(path1);
                        Log.d("path", path);
                        File fil = new File(path);
                        int fln = (int) fil.length();
                        //  e2.setText(path);

                        File file = new File(path);

                        byte[] byteArray = null;
                        try {
                            InputStream inputStream = new FileInputStream(fil);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            byte[] b = new byte[fln];
                            int bytesRead = 0;

                            while ((bytesRead = inputStream.read(b)) != -1) {
                                bos.write(b, 0, bytesRead);
                            }

                            byteArray = bos.toByteArray();
                            inputStream.close();
                            Bitmap bmp= BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                            if(bmp!=null){
//
//
                                im.setVisibility(View.VISIBLE);
                                im.setImageBitmap(bmp);
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Please select suitable file", Toast.LENGTH_LONG).show();
                }
                break;


        }


    }

    public String uploadFile(String sourceFileUri) {
        try {
            fileName = sourceFileUri;
            String upLoadServerUri ="http://"+sh.getString("ip","")+":5000/reg";
            FileUpload fp = new FileUpload(fileName);
            Map params = new HashMap<String, String>();
            params.put("name",FN);
            params.put("place",PLACE);
            params.put("post",post);
            params.put("pin",PN);
            params.put("phno",phn);
            params.put("email",EI);
            params.put("uname",UN);
            params.put("pass",PS);
            params.put("ds",DS);



            String res=fp.multipartRequest(upLoadServerUri, params, fileName, "files", "application/octet-stream");

            return res;

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_LONG).show();
            return "0";
        }
    }
}