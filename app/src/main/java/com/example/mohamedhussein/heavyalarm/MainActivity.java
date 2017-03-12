package com.example.mohamedhussein.heavyalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView update_text;
    Button alarm_on,alarm_of;
    Calendar calendar;
    PendingIntent pendingIntent;
    Intent alarm_intent;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context=this;

        initial_views();
        on_click();

        alarm_intent = new Intent(this.context,Alarm_Receiver.class);

    }

    public void initial_views ()
    {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        update_text = (TextView) findViewById(R.id.update_text);
        alarm_on = (Button)findViewById(R.id.alarm_on);
        alarm_of = (Button)findViewById(R.id.alarm_of);
        calendar = Calendar.getInstance();
    }

    public void updateStatues(String update_statues)
    {
        update_text.setText(update_statues);
    }

    public void on_click ()
    {
        alarm_on.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.set(calendar.HOUR,timePicker.getCurrentHour());
                calendar.set(calendar.MINUTE,timePicker.getCurrentMinute());

                int hour = timePicker.getCurrentHour();
                int minute= timePicker.getCurrentMinute();

                String minutes = String.valueOf(minute);
                String am_pm;

                if (hour >= 12)
                    am_pm="PM";
                else
                    am_pm="AM";

                if (hour > 12)
                {
                    hour = hour-12;
                }

                if (minute < 10)
                {
                    minutes= "0"+minutes;
                }

                updateStatues("ALARM is on "+hour+":"+minutes+" "+am_pm);

                alarm_intent.putExtra("extra","alarm on");

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,alarm_intent,PendingIntent.FLAG_UPDATE_CURRENT );

                alarmManager .set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        });

        alarm_of.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatues("ALARM is off");

                alarmManager.cancel(pendingIntent);

                alarm_intent.putExtra("extra","alarm off");

                sendBroadcast(alarm_intent);

            }
        });
    }
}
