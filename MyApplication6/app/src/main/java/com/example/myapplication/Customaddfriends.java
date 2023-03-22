package com.example.myapplication;
import java.util.ArrayList;



import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Customaddfriends extends BaseAdapter{

    private Context context;
    ArrayList<String> photo,name;

SharedPreferences sh;

    public Customaddfriends(Context applicationContext, ArrayList<String> a, ArrayList<String> b) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.photo=a;
        this.name=b;
        sh=PreferenceManager.getDefaultSharedPreferences(applicationContext);
       

    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_customaddfriends,null);

        }
        else
        {
            gridView=(View)convertView;
        }
        
        TextView tv1=(TextView)gridView.findViewById(R.id.textView1);
        ImageView i1=(ImageView) gridView.findViewById(R.id.imageView1);
       


        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/static/file/"+photo.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i1.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }



        tv1.setText(name.get(position));
       

        tv1.setTextColor(Color.BLACK);
       
       

        return gridView;

    }

  



}

