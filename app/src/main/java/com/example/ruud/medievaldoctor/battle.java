package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Random;

public class battle extends AppCompatActivity {

    private ProgressBar peasantHp;
    private ProgressBar doctorHp;

    Random rand = new Random();
    int peasantStrength = rand.nextInt(50); // Gives n such that 0 <= n < 50
    int peasantSeduce = rand.nextInt(50);
    int peasantSpeech = rand.nextInt(50);

    int doctorStrength = 35;
    int doctorSeduce = 38;
    int doctorSpeech = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        peasantHp = (ProgressBar) findViewById(R.id.enemyProgressBar);
        doctorHp = (ProgressBar) findViewById(R.id.doctorProgressBar);
        //setting the bar to 100
        peasantHp.setProgress(100);
        doctorHp.setProgress(100);
    }

    //@Override
    public void buttonPress(View view) {
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
        if(doctorHp.getProgress() < 10 || peasantHp.getProgress() < 10)
        {
            //TODO: send back to map activity
        }
        else if(doctorStrength >= peasantStrength)
        {
            peasantHp.setProgress((peasantHp.getProgress()) - 10);
        }
        else
        {
            doctorHp.setProgress((doctorHp.getProgress()) - 10);
        }
    }

    public void Seduce()
    {
        if(doctorHp.getProgress() < 10 || peasantHp.getProgress() < 10)
        {
            //TODO: send back to map activity
        }
        else if(doctorSeduce > peasantSeduce)
        {
            peasantHp.setProgress((peasantHp.getProgress()) - 10);
        }
        else
        {
            doctorHp.setProgress((doctorHp.getProgress()) - 10);
        }

    }

    public void Snub()
    {
        if(doctorHp.getProgress() < 10 || peasantHp.getProgress() < 10)
        {
            //TODO: send back to map activity
        }
        else if(doctorSpeech > peasantSpeech)
        {
            peasantHp.setProgress((peasantHp.getProgress()) - 10);
        }
        else
        {
            doctorHp.setProgress((doctorHp.getProgress()) - 10);
        }
    }


}