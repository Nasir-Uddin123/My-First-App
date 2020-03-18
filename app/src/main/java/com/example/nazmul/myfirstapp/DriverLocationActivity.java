package com.example.nazmul.myfirstapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLocationActivity extends AppCompatActivity {


    Handler mHandler;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Double latitude,longitude;


    private TextView tv1,tv;
    private TextView tv2;

    String string,string1;
    private static final int REQUEST_CODE_PERMISSION=2;
    String mpermission= android.Manifest.permission.ACCESS_FINE_LOCATION;
    GPStracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_location);
        this.setTitle("Driver Location");



        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, 5000);

        tv= (TextView) findViewById(R.id.tv);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);


       // string1=getIntent().getExtras().getString("VNID");
      //  tv.setText("Vehicle Name :"+string1);
        string=getIntent().getExtras().getString("VID");
        tv1.setText("Vehicle Id :"+string);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("VehicleId"+string);


        try{
            if(ActivityCompat.checkSelfPermission(this,mpermission)!= MockPackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{mpermission},REQUEST_CODE_PERMISSION);
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }



    private final Runnable m_Runnable = new Runnable() {
        public void run()

        {

            gps= new GPStracker(DriverLocationActivity.this);

            if(gps.cangetlocation()){
                 latitude = gps.latitude();
                 longitude = gps.longitude();
                tv2.setText(latitude+","+longitude);
                databaseReference.setValue(latitude+","+longitude).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DriverLocationActivity.this, "Location Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DriverLocationActivity.this, "Location Not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
            else {
                gps.showalert();
            }

            Toast.makeText(DriverLocationActivity.this, "Location Updating", Toast.LENGTH_SHORT).show();

            DriverLocationActivity.this.mHandler.postDelayed(m_Runnable, 5000);
           /* button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                    intent.putExtra("LAT",latitude);
                    intent.putExtra("LON",longitude);
                    startActivity(intent);
                }
            });*/




        }




    };
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(m_Runnable);
    }

    }

