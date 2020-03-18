package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button buttonDriver,buttonUser,buttonAdmin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonAdmin= (Button) findViewById(R.id.AdminId);
        buttonAdmin.setOnClickListener(this);
        buttonUser= (Button) findViewById(R.id.UserId);
        buttonUser.setOnClickListener(this);
        buttonDriver= (Button) findViewById(R.id.DriverId);
        buttonDriver.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu manu){
        getMenuInflater().inflate(R.menu.main,manu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.aboutUsId)
        {
            Intent intent=new Intent(getApplicationContext(),AboutUs.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.helpId)
        {
            Intent intent=new Intent(getApplicationContext(),Help.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.ScheduleId)
        {
            Intent intent=new Intent(getApplicationContext(),ViewVehicleDetails.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.DriverDetailsId)
        {
            Intent intent=new Intent(getApplicationContext(),ViewDriverDetails.class);
            startActivity(intent);
            return true;
        }
        return true;

    }

    @Override
    public void onClick(View view) {



            switch (view.getId()){

                case R.id.AdminId:
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.UserId:
                    Intent intent1=new Intent(getApplicationContext(),UserLogin.class);
                    startActivity(intent1);
                    break;
                case R.id.DriverId:
                    Intent intent2=new Intent(getApplicationContext(),ShowSpinner.class);
                    startActivity(intent2);
                    break;

            }


    }
}
