package com.example.mohamedhussein.heavyalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class Ring_tone extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand (Intent intent,int flags , int startId){

        mp = MediaPlayer.create(this,R.raw.tornado_siren);
        mp.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "On Destroy Ring Tone !", Toast.LENGTH_SHORT).show();
    }
}
