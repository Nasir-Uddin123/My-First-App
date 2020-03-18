package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private EditText loginEmail,loginPassword;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        this.setTitle("User Login Activity");

        mAuth = FirebaseAuth.getInstance();
        textView= (TextView) findViewById(R.id.SignUpTextViewId);
        loginEmail= (EditText) findViewById(R.id.LoginEmailId);
        loginPassword= (EditText) findViewById(R.id.LoginPasswordId);
        loginButton= (Button) findViewById(R.id.LoginId);

        textView.setOnClickListener(this);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.SignUpTextViewId:
                Intent intent=new Intent(getApplicationContext(),UserSignUp.class);
                startActivity(intent);
                finish();
                break;

            case R.id.LoginId:

                userLogin();
                break;

        }

    }

    private void userLogin() {


        String email=loginEmail.getText().toString().trim();
        String password=loginPassword.getText().toString().trim();


        if(email.isEmpty()){

            loginEmail.setError("Enter email address");
            loginEmail.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {

            loginEmail.setError("Enter valid email address");
            loginEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){

            loginPassword.setError("Enter password");
            loginPassword.requestFocus();
            return;

        }
        if(password.length()<6){

            loginPassword.setError("Minimum password length is 6");
            loginPassword.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {

                    Intent intent=new Intent(getApplicationContext(),UserActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    loginEmail.setText("");
                    loginPassword.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
