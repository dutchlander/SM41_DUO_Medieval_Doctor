package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {


    TextView tvbitcoins;

    private FirebaseUser user;
    private String uid;
    private List<String> itemlist;
    private DatabaseReference databaseReference;

    String doctorBitcoins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvbitcoins = (TextView)findViewById(R.id.medievalBitcoins);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        itemlist = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Integer bitcoins=dataSnapshot.child("users").child(uid).child("bitcoins").getValue(Integer.class);

                doctorBitcoins = Integer.toString(bitcoins);
                tvbitcoins.setText(doctorBitcoins);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
