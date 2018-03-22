package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void continueLogin(View view)
    {
        //TODO: check login
        //TODO: if correct send them to main menu
        //TODO: if incorrect send them back to login screen
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
