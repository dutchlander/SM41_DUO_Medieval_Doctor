package com.example.ruud.medievaldoctor;

import java.util.Random;

/**
 * Created by Ruud on 5-4-2018.
 */

public class Doctor {
    private String doctorName;
    private String doctorEmail;
    private int startingStrength;
    private int startingSpeech;
    private int startingCharisma;
    private int startingBitcoins = 10;


    private int startingLevel = 1;

    public Doctor(String name, String email, int str, int spe, int chari) {
        Random rand = new Random();
        this.doctorName = name;
        this.doctorEmail = email;
        this.startingStrength = rand.nextInt(str);
        this.startingSpeech = rand.nextInt(spe);
        this.startingCharisma = rand.nextInt(chari);
    }
}
