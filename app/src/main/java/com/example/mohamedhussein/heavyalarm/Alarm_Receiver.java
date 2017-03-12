package com.example.mohamedhussein.heavyalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class Alarm_Receiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {

        Log.e("Alarm_Receiver Is ","Great Job");

        String get_your_string = intent.getExtras().toString("extra");


        Intent service_Intent = new Intent(context,Ring_tone.class);

        context.startService(service_Intent);
    }

}
