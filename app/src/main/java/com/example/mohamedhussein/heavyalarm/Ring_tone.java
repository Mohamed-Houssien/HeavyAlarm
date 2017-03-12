package com.example.mohamedhussein.heavyalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class Ring_tone extends Service {

    MediaPlayer mp;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand (Intent intent,int flags , int startId){

        String state = intent.getExtras().getString("extra");

        assert state !=null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!isRunning && startId == 1)
        {
            mp = MediaPlayer.create(this,R.raw.tornado_siren);
            mp.start();

            isRunning=true;
//            startId = 0;
        }
        else if (isRunning && startId == 0)
        {
            mp.stop();

            isRunning=false;
//            startId=0;

        }
        /*
        else if (!isRunning && startId == 0)
        {
            isRunning=false;
        }
        else if (isRunning && startId == 1)
        {
            isRunning=true;
        }

        else
        {

        }*/

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
//        Toast.makeText(this, "On Destroy Ring Tone  !", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        isRunning=false;
    }
}
