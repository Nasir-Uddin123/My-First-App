package com.example.nazmul.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateVehicleActivity extends AppCompatActivity {



    private EditText vehicleId,startLocation,endingLocation,startTime,deleteId;
    private Button addButton,deleteButton;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vehicle);

        this.setTitle("Update Vehicle Details");

        vehicleId= (EditText) findViewById(R.id.VehicleNameId);
        startLocation= (EditText) findViewById(R.id.VehicleId);
        endingLocation= (EditText) findViewById(R.id.VTerminalId);
        startTime= (EditText) findViewById(R.id.StartTimeId);
        deleteId= (EditText) findViewById(R.id.DVehicleId);

        addButton= (Button) findViewById(R.id.AddButtonId);
        deleteButton= (Button) findViewById(R.id.DeleteButtonId);

        databaseReference= FirebaseDatabase.getInstance().getReference("Vehicle");


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               saveData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=deleteId.getText().toString().trim();
                DatabaseReference delete=FirebaseDatabase.getInstance().getReference("Vehicle").child(id);
                delete.removeValue();
                Toast.makeText(getApplicationContext(),"Id Deleted",Toast.LENGTH_SHORT).show();
                deleteId.setText("");

            }
        });



    }
     private void saveData(){

         String id=vehicleId.getText().toString().trim();
         String sL=startLocation.getText().toString().trim();
         String eL=endingLocation.getText().toString().trim();
         String time=startTime.getText().toString().trim();

         if (id.isEmpty())
         {

             vehicleId.setError("Enter id");
             vehicleId.requestFocus();
             return;
         }
         if (sL.isEmpty())
         {

             startLocation.setError("Enter start location");
             startLocation.requestFocus();
             return;
         }
         if (eL.isEmpty())
         {

             endingLocation.setError("Enter ending location");
             endingLocation.requestFocus();
             return;
         }
         if (time.isEmpty())
         {

             startTime.setError("Enter time");
             startTime.requestFocus();
             return;
         }


         //String key=databaseReference.push().getKey();



         Vehicle vehicle=new Vehicle(id,sL,eL,time);

         databaseReference.child(id).setValue(vehicle);
         Toast.makeText(getApplicationContext(),"Add successful",Toast.LENGTH_SHORT).show();

         vehicleId.setText("");
         startLocation.setText("");
         endingLocation.setText("");
         startTime.setText("");
     }
}
