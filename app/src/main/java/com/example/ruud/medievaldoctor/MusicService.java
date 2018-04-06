package com.example.ruud.medievaldoctor;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import static android.app.Service.START_STICKY;

/**
 * Created by issd on 05/04/2018.
 */


public class MusicService extends Service {

    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        player = MediaPlayer.create(this, R.raw.background_music);
        player.setLooping(true);
        player.start();
        return START_STICKY;

    }
}