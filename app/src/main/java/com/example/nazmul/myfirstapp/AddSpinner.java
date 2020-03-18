package com.example.nazmul.myfirstapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.nazmul.myfirstapp.R.id.EditTextVid;

public class AddSpinner extends AppCompatActivity {


    private EditText editText;
    private Button button,b2;
    String string;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spinner);

        this.setTitle("Update Vehicle Id");

        editText= (EditText) findViewById(EditTextVid);
        button= (Button) findViewById(R.id.IdAddbtn);
        b2= (Button) findViewById(R.id.IdDeletebtn);

        databaseReference= FirebaseDatabase.getInstance().getReference("Spinner");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=editText.getText().toString().trim();
                databaseReference.child(string).setValue(string).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        editText.setText("");

                        Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=editText.getText().toString().trim();

                DatabaseReference delete=FirebaseDatabase.getInstance().getReference("Spinner").child(string);
                delete.removeValue();
                Toast.makeText(getApplicationContext(),"Id Deleted",Toast.LENGTH_SHORT).show();
                editText.setText("");


            }
        });

    }
}
