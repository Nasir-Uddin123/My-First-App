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
 * Created by mdnum on 11/24/2019.
 */

public class DriverCustom extends ArrayAdapter<Driver> {

    private Activity context;
    private List<Driver> driverList;

    public DriverCustom(Activity context,List<Driver> driverList) {
        super(context,R.layout.sample_layout2,driverList);
        this.context = context;
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout2,null,true);
        Driver driver=driverList.get(position);

        TextView t1= (TextView) view.findViewById(R.id.D1);
        TextView t2= (TextView) view.findViewById(R.id.D2);
        TextView t3= (TextView) view.findViewById(R.id.D3);
        TextView t4= (TextView) view.findViewById(R.id.D4);
        //TextView t4= (TextView) view.findViewById(R.id.TimeTextViewId);

        t1.setText("Vehical Id :"+driver.getId());
        t2.setText("Driver Name :"+driver.getName());
        t3.setText("Contact Number :"+driver.getContact());
        t4.setText("Email_Id :"+driver.getEmail());
        //t4.setText("Start Time :"+vehical.getTime());

        return view;
    }
}

