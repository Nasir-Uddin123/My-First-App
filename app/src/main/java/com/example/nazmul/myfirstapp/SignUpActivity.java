package com.example.nazmul.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private EditText signUpEmail,signUpPassword,signUpName,signUpContact;
    private Button buttonSignUp;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("SignUp Activity");

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Admin");


        signUpEmail= (EditText) findViewById(R.id.SignUpEmailId);
        signUpPassword= (EditText) findViewById(R.id.SignUpPasswordId);
        textView= (TextView) findViewById(R.id.LoginTextViewId);
        signUpName=(EditText) findViewById(R.id.SignUpNameId);
        signUpContact=(EditText) findViewById(R.id.SignUpPhoneId);
        buttonSignUp= (Button) findViewById(R.id.SignUpId);

        textView.setOnClickListener(this);
        signUpEmail.setOnClickListener(this);
        signUpPassword.setOnClickListener(this);

        buttonSignUp.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.SignUpId:
                userRegister();
                break;


            case R.id.LoginTextViewId:
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {

        String name=signUpName.getText().toString().trim();
        String contact=signUpContact.getText().toString().trim();
        String email=signUpEmail.getText().toString().trim();
        String password=signUpPassword.getText().toString().trim();




        if(name.isEmpty()){

            signUpName.setError("Enter user name");
            signUpName.requestFocus();
            return;

        }
        if(contact.isEmpty()){

            signUpContact.setError("Enter contact number");
            signUpContact.requestFocus();
            return;

        }

        if(email.isEmpty()){

            signUpEmail.setError("Enter email address");
            signUpEmail.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {

            signUpEmail.setError("Enter valid email address");
            signUpEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){

            signUpPassword.setError("Enter password");
            signUpEmail.requestFocus();
            return;

        }
        if(password.length()<6){

            signUpPassword.setError("Minimum password length is 6");
            signUpEmail.requestFocus();
            return;

        }
        databaseReference.push().setValue("name:"+name+",contact:"+contact+",email:"+email);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();

                    signUpName.setText("");
                    signUpContact.setText("");
                    signUpEmail.setText("");
                    signUpPassword.setText("");

                }
                else {
                   if (task.getException() instanceof FirebaseAuthUserCollisionException)
                   {
                       Toast.makeText(getApplicationContext(),"Admin is already registered",Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }


                }

            }
        });




    }
}
