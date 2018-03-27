package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.RegisterButton:

                startActivity(new Intent(this, signUp.class));
        }
    }

    public void continueLogin(View view)
    {
        //TODO: check login
        //TODO: if correct send them to main menu
        //TODO: if incorrect send them back to login screen

        findViewById(R.id.RegisterButton).setOnClickListener(this);

        boolean correctLogin = true;

        if(correctLogin)
        {
            //new intent
            startActivity(new Intent(login.this, MainMenu.class));
        }
        else
        {
            //send back
        }
    }


}
