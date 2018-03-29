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


public class signUp extends AppCompatActivity implements View.OnClickListener{

    EditText newdoctor_name, newdoctor_password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        newdoctor_name = findViewById(R.id.newdoctor_name);
        newdoctor_password = findViewById(R.id.newdoctor_password);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.NewRegisterButton).setOnClickListener(this);
    }

    private void registerUser() {
        String username = newdoctor_name.getText().toString().trim();
        String password = newdoctor_password.getText().toString().trim();

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
                    Toast.makeText(getApplicationContext(), "Your doctor adventure begins now...", Toast.LENGTH_SHORT).show();
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
