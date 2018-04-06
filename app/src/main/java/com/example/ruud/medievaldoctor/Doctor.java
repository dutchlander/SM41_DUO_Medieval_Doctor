package com.example.ruud.medievaldoctor;

import java.util.Random;

/**
 * Created by Ruud on 5-4-2018.
 */

public class Doctor {
    public String doctorName;
    public String doctorEmail;
    public int startingStrength;
    public int startingSpeech;
    public int startingCharisma;
    public int startingBitcoins;

    private int startingLevel = 1;

    public Doctor(String name, String email, int str, int spe, int chari) {
        Random rand = new Random();
        this.doctorName = name;
        this.doctorEmail = email;
        this.startingStrength = rand.nextInt(str);
        this.startingSpeech = rand.nextInt(spe);
        this.startingCharisma = rand.nextInt(chari);
        this.startingBitcoins = 10;
    }
}
