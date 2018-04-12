package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class battle extends AppCompatActivity implements SensorEventListener {

    private boolean isPeasantDead = false;

    TextView doctorname;

    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 3200;
    private SensorManager sensorMgr;
    private boolean isShaked = false;

    private ProgressBar peasantHp;
    private ProgressBar doctorHp;

    Random rand = new Random();

    int peasantStrength;
    int peasantSeduce;
    int peasantSpeech;

    int doctorStrength;
    int doctorSeduce;
    int doctorSpeech;
<<<<<<< Updated upstream

=======
    int doctorBitcoin;
>>>>>>> Stashed changes
    String doctorName;


    private FirebaseUser user;
    private String uid;
    private List<String> itemlist;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        doctorname = findViewById(R.id.tv_doctorName);

        peasantHp = (ProgressBar) findViewById(R.id.enemyProgressBar);
        doctorHp = (ProgressBar) findViewById(R.id.doctorProgressBar);

        peasantHp.setProgress(100);
        doctorHp.setProgress(100);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        itemlist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

<<<<<<< Updated upstream
                String name = dataSnapshot.child("users").child(uid).child("name").getValue(String.class);
                Integer strength = dataSnapshot.child("users").child(uid).child("str").getValue(Integer.class);
                Integer seduce = dataSnapshot.child("users").child(uid).child("ch").getValue(Integer.class);
                Integer speech = dataSnapshot.child("users").child(uid).child("sp").getValue(Integer.class);
=======
                String name=dataSnapshot.child("users").child(uid).child("name").getValue(String.class);
                Integer strength=dataSnapshot.child("users").child(uid).child("str").getValue(Integer.class);
                Integer seduce=dataSnapshot.child("users").child(uid).child("ch").getValue(Integer.class);
                Integer speech=dataSnapshot.child("users").child(uid).child("sp").getValue(Integer.class);
                Integer bitcoin=dataSnapshot.child("users").child(uid).child("bitcoins").getValue(Integer.class);
>>>>>>> Stashed changes

                doctorName = name;
                doctorname.setText(doctorName);

                doctorStrength = strength;

                doctorSeduce = seduce;

                doctorSpeech = speech;

                doctorBitcoin = bitcoin;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorMgr == null) {
            sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
            boolean accelSupported = sensorMgr.registerListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

            if (!accelSupported) {
                // on accelerometer on this device
                sensorMgr.unregisterListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z)
                        / diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    // yes, this is a shake action! Do something about it!
                    isShaked = true;
                    //you have beaten the peasant
                    if(isPeasantDead)
                    {
                        //you have beaten the peasant to dead continue to the next activity
                        startActivity(new Intent(battle.this, afterText.class));

                    }
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    protected void onPause() {
        if (sensorMgr != null) {
            sensorMgr.unregisterListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            sensorMgr = null;
        }
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void DeathCheck()
    {
        if (isPeasantDead == true)
        {
            doctorBitcoin = doctorBitcoin + 5;
            databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("bitcoins").setValue(doctorBitcoin);
        }
    }

    //@Override
    public void buttonPress(View view) {
        peasantStrength = rand.nextInt(50); // Gives n such that 0 <= n < 50
        peasantSeduce = rand.nextInt(50);
        peasantSpeech = rand.nextInt(50);
        switch(view.getId()){
            case R.id.beatButton:
                Beat();
                break;
            case R.id.seduceButton:
                Seduce();
                break;
            case R.id.snubButton:
                Snub();
                break;
        }
    }

    public void Beat()
    {
        DeathCheck();

        if(doctorHp.getProgress() < 10 || peasantHp.getProgress() < 10)
        {
            if(doctorHp.getProgress() < 10)
            {
                //TODO: peasant won
                //TODO: popup 1 level setback
                //TODO: lose some bitcoin
            }
            else
            {
                Toast.makeText(this, "Shake your phone to beat the shit out of this peasant and grab his medieval bitcoin", Toast.LENGTH_LONG).show();
                isPeasantDead = true;
            }
        }
        else if(doctorStrength >= peasantStrength)
        {
            peasantHp.setProgress((peasantHp.getProgress()) - 10);
        }
        else
        {
            doctorHp.setProgress((doctorHp.getProgress()) - 10);
        }

        MediaPlayer beatSound = MediaPlayer.create(this, R.raw.hit);
        beatSound.start();
    }

    public void Seduce()
    {
        DeathCheck();

        if(doctorHp.getProgress() < 10 || peasantHp.getProgress() < 10)
        {
            if(doctorHp.getProgress() < 10)
            {
                //TODO: peasant won
                //TODO: popup 1 level setback
                //TODO: lose some bitcoin
            }
            else
            {
                Toast.makeText(this, "Shake your phone to beat the shit out of this peasant and grab his medieval bitcoin", Toast.LENGTH_LONG).show();
                isPeasantDead = true;

            }
        }
        else if(doctorSeduce > peasantSeduce)
        {
            peasantHp.setProgress((peasantHp.getProgress()) - 10);
        }
        else
        {
            doctorHp.setProgress((doctorHp.getProgress()) - 10);
        }

        MediaPlayer beatSound = MediaPlayer.create(this, R.raw.hit);
        beatSound.start();

    }

    public void Snub()
    {
        DeathCheck();

        if(doctorHp.getProgress() < 10 || peasantHp.getProgress() < 10)
        {
            //TODO: send back to map activity
            if(doctorHp.getProgress() < 10)
            {
                //TODO: peasant won
                //TODO: popup 1 level setback
                //TODO: lose some bitcoin
            }
            else
            {
                Toast.makeText(this, "Shake your phone to beat the shit out of this peasant and grab his medieval bitcoin", Toast.LENGTH_LONG).show();
                isPeasantDead = true;
            }
        }
        else if(doctorSpeech > peasantSpeech)
        {
            peasantHp.setProgress((peasantHp.getProgress()) - 10);
        }
        else
        {
            doctorHp.setProgress((doctorHp.getProgress()) - 10);
        }
        MediaPlayer beatSound = MediaPlayer.create(this, R.raw.hit);
        beatSound.start();
    }

}