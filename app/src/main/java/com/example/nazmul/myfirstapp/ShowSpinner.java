package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowSpinner extends AppCompatActivity {

    Spinner spinner;
    private Button button;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spinner);
        this.setTitle("Driver Activity");

        spinner= (Spinner) findViewById(R.id.mySpinnerid);
        button= (Button) findViewById(R.id.SelectBt);

        databaseReference= FirebaseDatabase.getInstance().getReference("Spinner");

        spinnerDataList=new ArrayList<>();
        adapter=new ArrayAdapter<String>(ShowSpinner.this,android.R.layout.simple_spinner_dropdown_item,spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               text=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.isEmpty())
                {
                    Toast.makeText(ShowSpinner.this, "Please wait", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    Intent intent = new Intent(ShowSpinner.this, DriverLocationActivity.class);

                    intent.putExtra("VID", text);
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



}
