package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class WelcomeBar extends AppCompatActivity {


    private ProgressBar progressBar;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_bar);

        progressBar = (ProgressBar) findViewById(R.id.nasirId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();

    }

    public void doWork() {

        for (progress = 20; progress <= 100; progress = progress + 20) {

            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    public void startApp(){

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

    }
}
