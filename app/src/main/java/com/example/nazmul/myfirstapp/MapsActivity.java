package com.example.nazmul.myfirstapp;

import android.content.Context;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Double lat,lon;
    String str,str2;
    Double dlat,dlat2;
    Double dlong,dlong2;
    private Button b1,b2;
    private TextView t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        b1= (Button) findViewById(R.id.disId);
        b2= (Button) findViewById(R.id.timeid);
        t1= (TextView) findViewById(R.id.dis2id);
        t2= (TextView) findViewById(R.id.time2id);

        /* lat=getIntent().getDoubleExtra("LAT",1);
        lon=getIntent().getDoubleExtra("LON",1);  */


        //driver location
        str=getIntent().getExtras().getString("LOCVAL");
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        String[] separate=str.split(",");
        String lati=separate[0].trim();
        String logi=separate[1].trim();
        dlat=Double.parseDouble(lati);
        dlong=Double.parseDouble(logi);

        str2=getIntent().getExtras().getString("n");
        //Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        String[] sepa=str2.split(",");
        String lati2=sepa[0].trim();
        String logi2=sepa[1].trim();
        dlat2=Double.parseDouble(lati2);
        dlong2=Double.parseDouble(logi2);



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(dlat, dlong);
        LatLng cumilla = new LatLng(dlat2, dlong2);

        PolylineOptions polylineOptions=new PolylineOptions();
        polylineOptions.add(sydney);
        polylineOptions.add(cumilla);
        polylineOptions.color(Color.GREEN);
        polylineOptions.width(20);
        mMap.addPolyline(polylineOptions);

        // Add a marker in Sydney and move the camera

        String address = getAddress(this,dlat,dlong);
        MarkerOptions marker=new MarkerOptions().position(sydney).title("Driver:"+address);
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.car));
        mMap.addMarker(marker);
        CameraPosition cameraposition=new CameraPosition.Builder().target(sydney).zoom(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraposition));



        String address2 = getAddress2(this,dlat2, dlong2);
        MarkerOptions marker2=new MarkerOptions().position(cumilla).title("User:"+address2);
        marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.userff));
        mMap.addMarker(marker2);
        CameraPosition cameraposition2=new CameraPosition.Builder().target(cumilla).zoom(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraposition2));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float result[]=new float[10];
                Location.distanceBetween(dlat,dlong,dlat2,dlong2,result);

                int d1=(int)(result[0]/1000);
                int d2=(int)(result[0]%1000);
                t1.setText("Distance="+d1+" km "+d2+" m");



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float result[]=new float[10];
                Location.distanceBetween(dlat,dlong,dlat2,dlong2,result);

                double r1=(result[0]*0.1253164557)/3600;
                int r2=(int)r1;
                double r3=(result[0]*0.1253164557)%3600;
                int r4=(int)(r3/60);

                double r5=(result[0]*0.1093670886)/3600;
                int r6=(int)r5;
                double r7=(result[0]*0.1093670886)%3600;
                int r8=(int)(r7/60);


                t2.setText("Car=" +r2+"hr"+" "+r4+" min"+"\n"+"Bike=" +r6+"hr"+" "+r8+" min");







            }
        });
    }

    private String getAddress2(Context context2, double lat2, double lon2) {

        String address2=null;
        try{
            Geocoder geocoder2=new Geocoder(context2, Locale.getDefault());
            List<Address> addresses2=geocoder2.getFromLocation(lat2,lon2,1);
            if(addresses2.size()>0)
            {
                Address address3=addresses2.get(0);
                address2=address3.getAddressLine(0);

                // String location=address1.getLocality();
                //String zip=address1.getPostalCode();
                //String country=address1.getCountryCode();
            }

        }catch(IOException ex2)
        {
            ex2.printStackTrace();
        }


        return address2;
    }

    public String getAddress(Context context, double lat, double lon)
    {
        String address=null;
        try{
            Geocoder geocoder=new Geocoder(context, Locale.getDefault());
            List<Address> addresses=geocoder.getFromLocation(lat,lon,1);
            if(addresses.size()>0)
            {
                Address address1=addresses.get(0);
                address=address1.getAddressLine(0);

                // String location=address1.getLocality();
                //String zip=address1.getPostalCode();
                //String country=address1.getCountryCode();
            }

        }catch(IOException ex)
        {
            ex.printStackTrace();
        }


        return address;
    }
}
