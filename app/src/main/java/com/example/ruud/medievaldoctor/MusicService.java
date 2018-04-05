package com.example.ruud.medievaldoctor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

import static android.app.Service.START_STICKY;

/**
 * Created by issd on 05/04/2018.
 */

public class MusicService extends MainActivity {

    private MediaPlayer player;

    public int onStartCommand(Intent intent, int flags, int startId)    {
        player = MediaPlayer.create(this, R.raw.background_music);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        player.stop();
    }
}
