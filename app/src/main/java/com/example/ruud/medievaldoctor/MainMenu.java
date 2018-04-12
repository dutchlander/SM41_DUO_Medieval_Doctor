package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity {

    Doctor doctor;
    TextView bitcoins;
    //FirebaseUser currentFirebaseUser;
    //DatabaseReference currentUser;

    // Get a reference to our posts
    DatabaseReference allRef, usersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        bitcoins = (TextView)findViewById(R.id.medievalBitcoins);
        //currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //currentUser = FirebaseDatabase.getInstance().getReference("users").child(currentFirebaseUser.toString());
        //doctor = new Doctor(currentUser.child("name").toString(), currentUser.child("email").toString(), Integer.parseInt(currentUser.child("str").toString()), Integer.parseInt(currentUser.child("sp").toString()), Integer.parseInt(currentUser.child("ch").toString()));
        //bitcoins.setText(doctor.startingBitcoins);
        allRef = FirebaseDatabase.getInstance().getReference();
        usersRef = allRef.child("users");

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
