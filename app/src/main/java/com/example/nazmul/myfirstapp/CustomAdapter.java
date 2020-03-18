package com.example.nazmul.myfirstapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mdnum on 9/4/2019.
 */

public class CustomAdapter extends ArrayAdapter<Vehicle> {

    private Activity context;
    private List<Vehicle> vehicleList;

    public CustomAdapter(Activity context,List<Vehicle> vehicleList) {
        super(context,R.layout.sample_layout,vehicleList);
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);
        Vehicle vehical=vehicleList.get(position);

        TextView t1= (TextView) view.findViewById(R.id.NameTextViewId);
        TextView t2= (TextView) view.findViewById(R.id.IdTextViewId);
        TextView t3= (TextView) view.findViewById(R.id.TerminalTextViewId);
        TextView t4= (TextView) view.findViewById(R.id.TimeTextViewId);

        t1.setText("Vehical Id :"+vehical.getId());
        t2.setText("Starting Location :"+vehical.getsL());
        t3.setText("Ending Location :"+vehical.geteL());
        t4.setText("Start Time :"+vehical.getTime());

        return view;
    }
}
