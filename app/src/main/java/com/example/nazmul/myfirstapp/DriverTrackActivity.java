package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverTrackActivity extends AppCompatActivity {


    private Button b1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView tv1,tv2;
    private static final int REQUEST_CODE_PERMISSION=2;
    String mpermission= android.Manifest.permission.ACCESS_FINE_LOCATION;
    String st,st1,st3,st4,st5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_track);
        this.setTitle("User Activity");

        tv1= (TextView) findViewById(R.id.DTtextId);
        tv2= (TextView) findViewById(R.id.ULid);
        b1= (Button) findViewById(R.id.BtDTid);

        try{
            if(ActivityCompat.checkSelfPermission(this,mpermission)!= MockPackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{mpermission},REQUEST_CODE_PERMISSION);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
         st3= getIntent().getExtras().getString("U");
        String[] separate=st3.split(",");
        String lati=separate[0].trim();
        String logi=separate[1].trim();
         tv2.setText("Latitude   :"+lati+"\nLongitude :"+logi);


        st1=  getIntent().getExtras().getString("Vid");
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("VehicleId"+st1);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                st=dataSnapshot.getValue(String.class);
                String[] separate=st.split(",");
                String latitude=separate[0].trim();
                String longitude=separate[1].trim();
                tv1.setText("Latitude   :"+latitude+"\nLongitude :"+longitude);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       //st4=tv2.getText().toString();




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st5=tv1.getText().toString();//driver location

                if(st5.isEmpty())
                {
                    Toast.makeText(DriverTrackActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    Intent intent = new Intent(DriverTrackActivity.this, MapsActivity.class);
                    intent.putExtra("LOCVAL", st);
                    intent.putExtra("n", st3);
                    startActivity(intent);
                }

               /* String string1=tv1.getText().toString();

                if(string1.equals(" "))
                {
                    Toast.makeText(DriverTrackActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(DriverTrackActivity.this,MapsActivity.class);
                    intent.putExtra("LOCVAL",st);
                    startActivity(intent);
                }

               st4=tv2.getText().toString();
                if(st4.equals(" "))
                {
                    Toast.makeText(DriverTrackActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent1=new Intent(DriverTrackActivity.this,MapsActivity.class);
                    intent1.putExtra("L",st4);
                    startActivity(intent1);
                }*/

            }
        });

    }
}
