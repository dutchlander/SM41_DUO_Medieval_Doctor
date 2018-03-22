package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void goToMap(View view)
    {
        startActivity(new Intent(MainMenu.this, map.class));
    }

    public void goToStore(View view)
    {
        startActivity(new Intent(MainMenu.this, store.class));
    }
}
