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

public class ViewVehicleDetails extends AppCompatActivity {


    private ListView listView;
    private List<Vehicle>vehicleList;
    private CustomAdapter customAdapter;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle_details);
        this.setTitle("Vehicle Details");


        databaseReference= FirebaseDatabase.getInstance().getReference("Vehicle");
        vehicleList=new ArrayList<>();
        customAdapter=new CustomAdapter(ViewVehicleDetails.this,vehicleList);

        listView= (ListView) findViewById(R.id.VListViewId);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vehicleList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Vehicle vehicle=dataSnapshot1.getValue(Vehicle.class);
                    vehicleList.add(vehicle);

                }

                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
