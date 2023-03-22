package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_medical extends BaseAdapter{

    private Context context;
    ArrayList<String> name, place, contact, hosp, spec;



    public custom_medical(Context applicationContext, ArrayList<String> name, ArrayList<String> place, ArrayList<String> contact, ArrayList<String> hosp, ArrayList<String> spec) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.name=name;
        this.place=place;
        this.contact=contact;
        this.hosp=hosp;
        this.spec=spec;
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
            gridView=inflator.inflate(R.layout.activity_custom_medical,null);
        }
        else
        {
            gridView=(View)convertView;
        }

        TextView tv_date=(TextView)gridView.findViewById(R.id.textView11);
        TextView tv_program=(TextView)gridView.findViewById(R.id.textView22);
        TextView tv_added=(TextView)gridView.findViewById(R.id.textView33);
        TextView tv_descr=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv_venue=(TextView)gridView.findViewById(R.id.textView55);

        

        tv_date.setText(name.get(position));
        tv_program.setText(place.get(position));
        tv_added.setText(contact.get(position));
        tv_descr.setText(hosp.get(position));
        tv_venue.setText(spec.get(position));


        tv_date.setTextColor(Color.BLACK);
        tv_program.setTextColor(Color.BLACK);
        tv_added.setTextColor(Color.BLACK);
        tv_descr.setTextColor(Color.BLACK);
        tv_venue.setTextColor(Color.BLACK);


        return gridView;

    }
}

