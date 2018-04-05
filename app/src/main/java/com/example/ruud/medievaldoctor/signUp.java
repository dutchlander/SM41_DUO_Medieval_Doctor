package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class signUp extends AppCompatActivity implements View.OnClickListener{
    //name is the email, actual doctor name is the real doctor's name
    EditText newdoctor_name, newdoctor_password, newdoctor_actualname;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        newdoctor_name = findViewById(R.id.newdoctor_name);
        newdoctor_password = findViewById(R.id.newdoctor_password);
        newdoctor_actualname = findViewById(R.id.newdoctor_actualname);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.NewRegisterButton).setOnClickListener(this);
    }

    private void registerUser() {
        final String username = newdoctor_name.getText().toString().trim(); // email
        String password = newdoctor_password.getText().toString().trim();
        final String doctorActualName = newdoctor_actualname.getText().toString().trim();

        if (username.isEmpty()) {
            newdoctor_name.setError("What is a doctor without a name?");
            newdoctor_name.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            newdoctor_password.setError("You need a password to protect your doctor secrets");
            newdoctor_password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            newdoctor_password.setError("Your password needs to be at least 6 characters long");
            newdoctor_password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Doctor doctor = new Doctor(doctorActualName, username, 50, 50, 50);

                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(doctor.toString());
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(doctorActualName);
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(username);
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("bitcoins").setValue(doctor.startingBitcoins);
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("str").setValue(doctor.startingStrength);
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("sp").setValue(doctor.startingSpeech);
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("ch").setValue(doctor.startingCharisma);
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("level").setValue(1);





                    Toast.makeText(getApplicationContext(), "Your doctor adventure begins now...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signUp.this, MainMenu.class));
                }
            }
        }
        );
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.NewRegisterButton:
                registerUser();
            break;
        }
    }

    public void finishRegister(View view)
    {
        startActivity(new Intent(signUp.this, MainMenu.class));
    }
}
