package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsideVupdate extends AppCompatActivity {

    private Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_vupdate);

        this.setTitle("Update Vehicle Details");

        b1= (Button) findViewById(R.id.VSchudleId);

        b2= (Button) findViewById(R.id.VupdateId);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UpdateVehicleActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddSpinner.class);
                startActivity(intent);
            }
        });

    }
}
