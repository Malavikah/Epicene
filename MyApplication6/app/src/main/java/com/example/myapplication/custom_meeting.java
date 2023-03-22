package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_meeting extends BaseAdapter{

    private Context context;
    ArrayList<String> date, place, desr, added;



    public custom_meeting(Context applicationContext, ArrayList<String> date, ArrayList<String> place, ArrayList<String> descr, ArrayList<String> added) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.date=date;
        this.place=place;
        this.desr=descr;
        this.added=added;
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return date.size();
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
            gridView=inflator.inflate(R.layout.activity_custommeeting,null);

        }
        else
        {
            gridView=(View)convertView;
        }

        TextView tv_date=(TextView)gridView.findViewById(R.id.textView11);
        TextView tv_place=(TextView)gridView.findViewById(R.id.textView22);
        TextView tv_added=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv_descr=(TextView)gridView.findViewById(R.id.textView33);

        

        tv_date.setText(date.get(position));
        tv_place.setText(place.get(position));
        tv_added.setText(added.get(position));
        tv_descr.setText(desr.get(position));


        tv_date.setTextColor(Color.BLACK);
        tv_added.setTextColor(Color.BLACK);
        tv_descr.setTextColor(Color.BLACK);
        tv_place.setTextColor(Color.BLACK);


        return gridView;

    }
}

