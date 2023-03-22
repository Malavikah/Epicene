package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customawareness extends BaseAdapter{

    private Context context;
    ArrayList<String> date, venue, program, desr, added;



    public customawareness(Context applicationContext, ArrayList<String> date, ArrayList<String> venue, ArrayList<String> program, ArrayList<String> descr, ArrayList<String> added) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.date=date;
        this.venue=venue;
        this.program=program;
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
            gridView=inflator.inflate(R.layout.activity_customawareness,null);

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

        

        tv_date.setText(date.get(position));
        tv_program.setText(program.get(position));
        tv_added.setText(added.get(position));
        tv_descr.setText(desr.get(position));
        tv_venue.setText(venue.get(position));


        tv_date.setTextColor(Color.BLACK);
        tv_program.setTextColor(Color.BLACK);
        tv_added.setTextColor(Color.BLACK);
        tv_descr.setTextColor(Color.BLACK);
        tv_venue.setTextColor(Color.BLACK);


        return gridView;

    }
}

