package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {

    private TextView doctorName;
    private TextView doctorPassword;

    private ImageButton loginButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        doctorName = (EditText) findViewById(R.id.doctor_name);
        doctorPassword = (EditText) findViewById(R.id.doctor_password);

        loginButton = (ImageButton) findViewById(R.id.loginButton);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    // user already logged in
                    //TODO: fix this...somehow does not work yet
                    //TODO: this could work if it's in onStart method....try that maybe
                    //startActivity(new Intent(login.this, MainMenu.class));
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.RegisterButton:

                startActivity(new Intent(this, signUp.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    public void startSignIn()
    {
        String name = doctorName.getText().toString();
        String password = doctorPassword.getText().toString();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password))
        {
            Toast.makeText(login.this, "Please give us all your doctor information", Toast.LENGTH_LONG).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        //incorrect info
                        Toast.makeText(login.this, "We cannot authenticate your doctor", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        //correct login, send to menu!
                        startActivity(new Intent(login.this, MainMenu.class));
                    }
                }
            });
        }
    }

    public void GoToSignUp(View view)
    {
        startActivity(new Intent(login.this, signUp.class));
    }

}
