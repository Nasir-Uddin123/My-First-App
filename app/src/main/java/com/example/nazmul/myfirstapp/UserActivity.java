package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    private Button buttonView,buttonMap,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle("User Activity");


       // buttonView= (Button) findViewById(R.id.ViewVDetailsId);
        buttonMap= (Button) findViewById(R.id.MapId);
       // b2= (Button) findViewById(R.id.ViewDDetailsId);



        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2=new Intent(getApplicationContext(),UserSpinner.class);
                startActivity(intent2);

            }
        });

    }
}
