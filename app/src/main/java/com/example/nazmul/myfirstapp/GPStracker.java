package com.example.nazmul.myfirstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

/**
 * Created by mdnum on 9/19/2019.
 */

public class GPStracker extends Service implements LocationListener {

    private  final Context mcontext;
    boolean isgpsenable=false;
    boolean isnetnable=false;
    boolean cangetlocation=false;
    Location location;
    double latitude;
    double longitude;
    private  static final long mindis=0;
    private  static final long mintime=100*60*1;
    protected LocationManager locationmanager;

    public GPStracker(Context context){
        mcontext=context;
        getlocation();

    }
    public  Location getlocation() {
        try {
            locationmanager = (LocationManager) mcontext.getSystemService(LOCATION_SERVICE);
            isgpsenable = locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isnetnable = locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isnetnable && !isgpsenable) {

            }

            else {
                this.cangetlocation = true;
                if (isnetnable) {
                    if (ActivityCompat.checkSelfPermission((Activity)mcontext, android.Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                            ((Activity)mcontext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return null;
                    }
                    locationmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mintime, mindis, this);
                    if (locationmanager != null) {

                        location=locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location!=null){
                            latitude=location.getLatitude();
                            longitude=location.getLongitude();
                        }
                    }

                }
                if(isgpsenable){
                    if(location==null) {
                        locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,mintime,mindis,this);
                        if(locationmanager!=null){


                            location=locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(location!=null)
                            {
                                latitude=location.getLatitude();
                                longitude=location.getLongitude();

                            }
                        }
                    }
                }

            }




        } catch (Exception e) {

            e.printStackTrace();
        }

        return  location;
    }
    public void stopusinggps(){

        if(locationmanager!=null){
            if (ActivityCompat.checkSelfPermission((Activity)mcontext, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                    ((Activity)mcontext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return ;
            }
            locationmanager.removeUpdates(GPStracker.this);


        }



    }
    public double latitude(){
        if(location!=null)
        {
            latitude=location.getLatitude();

        }
        return  latitude;
    }
    public double longitude(){
        if(location!=null)
        {
            longitude=location.getLongitude();
        }
        return  longitude;
    }

    public  boolean cangetlocation(){
        return  this.cangetlocation;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public  void  showalert(){


        AlertDialog.Builder alert= new AlertDialog.Builder(mcontext);
        alert.setTitle("GPS iS settings");
        alert.setMessage("GPS is not Enable.Do you want?");
        alert.setPositiveButton("Settings",new DialogInterface.OnClickListener(){
            @Override

            public  void onClick(DialogInterface dialogInterface, int which){
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mcontext.startActivity(intent);

            }
        });
        alert.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                dialog.cancel();
            }


        });




        alert.show();


    }




    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


