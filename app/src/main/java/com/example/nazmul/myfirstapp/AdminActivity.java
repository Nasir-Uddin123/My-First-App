package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {


    private Button button,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.setTitle("Admin Activity");

        button= (Button) findViewById(R.id.Vupdate);
        b2= (Button) findViewById(R.id.DupdateId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),InsideVupdate.class);
                startActivity(intent);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DriverDetails.class);
                startActivity(intent);
            }
        });
    }
}
