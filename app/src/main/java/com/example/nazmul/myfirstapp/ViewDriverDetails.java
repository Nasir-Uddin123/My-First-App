package com.example.nazmul.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDriverDetails extends AppCompatActivity {


    private ListView listView;
    private List<Driver> driverList;
    private DriverCustom driverCustom;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_driver_details);
        this.setTitle("Driver Details");


            databaseReference= FirebaseDatabase.getInstance().getReference("Driver");
            driverList=new ArrayList<>();
            driverCustom=new DriverCustom(ViewDriverDetails.this,driverList);

            listView= (ListView) findViewById(R.id.DListViewId);
        }

        @Override
        protected void onStart() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    driverList.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Driver driver=dataSnapshot1.getValue(Driver.class);
                        driverList.add(driver);

                    }

                    listView.setAdapter(driverCustom);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            super.onStart();
        }
    }
