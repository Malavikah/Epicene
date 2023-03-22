package com.example.myapplication;
import java.util.ArrayList;



import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Customlegal extends BaseAdapter{

    private Context context;
    ArrayList<String> a,b,c;



    public Customlegal(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;

    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return c.size();
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
            gridView=inflator.inflate(R.layout.activity_customlegal,null);

        }
        else
        {
            gridView=(View)convertView;
        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView1);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView2);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView3);

        tv1.setText(a.get(position));
        tv2.setText(b.get(position));
        tv3.setText(c.get(position));

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);

        return gridView;

    }

    private ContentResolver getContentResolver() {
        // TODO Auto-generated method stub
        return null;
    }




}
