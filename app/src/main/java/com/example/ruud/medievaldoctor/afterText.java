package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class afterText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_text);
    }

    public void map(View view)
    {
        startActivity(new Intent(afterText.this, map.class));

    }
}

