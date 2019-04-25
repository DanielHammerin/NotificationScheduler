package com.example.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Notification> notificationsList;
    ListView notificationListView;

    public static String getPrimaryChannelId() {
        return PRIMARY_CHANNEL_ID;
    }

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    //private NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createNotificationChannel();

        notificationListView = findViewById(R.id.notification_list);
        notificationsList = new ArrayList<>();

        notificationsList.add(new Notification("Kvällsfodring", "2019-04-11", "20.15"));
        notificationsList.add(new Notification("Utsläpp", "2019-04-15", "06.15"));
        notificationsList.add(new Notification("Insläpp", "2019-04-20", "19.00"));
        notificationsList.add(new Notification("Utsläpp", "2019-04-26", "06.15"));
        notificationsList.add(new Notification("Insläpp", "2019-05-03", "19.00"));
        notificationsList.add(new Notification("Kvällsfodring", "2019-05-06", "20.15"));
        notificationsList.add(new Notification("Kvällsfodring", "2019-05-20", "20.15"));
        notificationsList.add(new Notification("Utsläpp", "2019-06-11", "06.15"));
        notificationsList.add(new Notification("Utsläpp", "2019-06-15", "06.15"));

        NotificationListAdapter adapter = new NotificationListAdapter(notificationsList, getApplicationContext());
        notificationListView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNotificationActivity.class);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            //notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Scheduler");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
    */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification_Scheduler";
            String description = "Notification channel for Scheduler";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(PRIMARY_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
