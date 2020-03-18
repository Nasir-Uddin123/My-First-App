package com.example.nazmul.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverDetails extends AppCompatActivity {

    private EditText vehicleId,driverName,contactNum,driverEmail,deleteId;
    private Button addButton,deleteButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        this.setTitle("Driver Details");
        vehicleId= (EditText) findViewById(R.id.DVId);
        driverName= (EditText) findViewById(R.id.DnameId);
        contactNum= (EditText) findViewById(R.id.DContactId);
        driverEmail= (EditText) findViewById(R.id.DEmailId);
        addButton= (Button) findViewById(R.id.DAddButtonId);

        deleteId= (EditText) findViewById(R.id.DDVehicleId);
        deleteButton= (Button) findViewById(R.id.DeleteDriverId);

        databaseReference= FirebaseDatabase.getInstance().getReference("Driver");


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
                DatabaseReference delete=FirebaseDatabase.getInstance().getReference("Driver").child(id);
                delete.removeValue();
                Toast.makeText(getApplicationContext(),"Id Deleted",Toast.LENGTH_SHORT).show();
                deleteId.setText("");

            }
        });



    }
    private void saveData(){

        String id=vehicleId.getText().toString().trim();
        String name=driverName.getText().toString().trim();
        String contact=contactNum.getText().toString().trim();
        String email=driverEmail.getText().toString().trim();

        if (id.isEmpty())
        {

            vehicleId.setError("Enter id");
            vehicleId.requestFocus();
            return;
        }
        if (name.isEmpty())
        {

            driverName.setError("Enter driver name");
            driverName.requestFocus();
            return;
        }
        if (contact.isEmpty())
        {

            contactNum.setError("Enter contact number");
            contactNum.requestFocus();
            return;
        }
        if (email.isEmpty())
        {

            driverEmail.setError("Enter email");
            driverEmail.requestFocus();
            return;
        }



        //String key=databaseReference.push().getKey();



        Driver driver=new Driver(id,name,contact,email);

        databaseReference.child(id).setValue(driver);
        Toast.makeText(getApplicationContext(),"Add successful",Toast.LENGTH_SHORT).show();

        vehicleId.setText("");
        driverName.setText("");
        contactNum.setText("");
        driverEmail.setText("");

    }
}

