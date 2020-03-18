package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserSpinner extends AppCompatActivity {
    Spinner spinner;
    private Button button;
    private TextView textView;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    Handler mHandler;
    FirebaseDatabase firebaseDatabase;
    Double latitude,longitude;
    String string,s;
    private static final int REQUEST_CODE_PERMISSION=2;
    String mpermission= android.Manifest.permission.ACCESS_FINE_LOCATION;
    GPStracker gps;

    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_spinner);

        this.setTitle("User Activity");

        spinner= (Spinner) findViewById(R.id.UserSpinnerid);
        button= (Button) findViewById(R.id.SelectBtU);
        textView= (TextView) findViewById(R.id.ToastId);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, 5000);
        try{
            if(ActivityCompat.checkSelfPermission(this,mpermission)!= MockPackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{mpermission},REQUEST_CODE_PERMISSION);
            }


        }catch (Exception e){
            e.printStackTrace();
        }


        databaseReference= FirebaseDatabase.getInstance().getReference("Spinner");

        spinnerDataList=new ArrayList<>();
        adapter=new ArrayAdapter<String>(UserSpinner.this,android.R.layout.simple_spinner_dropdown_item,spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=textView.getText().toString();
                if(s.isEmpty())
                {
                    Toast.makeText(UserSpinner.this, "Please wait", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(text.isEmpty())
                {
                    Toast.makeText(UserSpinner.this, "Please wait", Toast.LENGTH_SHORT).show();
                    return;
                }

               else {
                    Intent intent = new Intent(getApplicationContext(), DriverTrackActivity.class);
                    intent.putExtra("Vid", text);
                    intent.putExtra("U", s);
                    startActivity(intent);
                }


            }
        });




    }

    public void retrieveData(){
        listener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    spinnerDataList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private final Runnable m_Runnable = new Runnable() {
        public void run()

        {

            gps= new GPStracker(UserSpinner.this);

            if(gps.cangetlocation()){
                latitude = gps.latitude();
                longitude = gps.longitude();
                textView.setText(latitude+","+longitude);

               /* databaseReference.setValue(latitude+","+longitude).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserSpinner.this, "Location Saved", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UserSpinner.this, "Location Not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/


            }
            else {
                gps.showalert();
            }


            Toast.makeText(UserSpinner.this, "Location Updating", Toast.LENGTH_SHORT).show();

            UserSpinner.this.mHandler.postDelayed(m_Runnable, 5000);
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
