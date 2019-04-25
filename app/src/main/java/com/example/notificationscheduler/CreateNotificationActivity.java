package com.example.notificationscheduler;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CreateNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText hours = findViewById(R.id.time_hours);
                EditText minutes = findViewById(R.id.time_minutes);
                EditText title = findViewById(R.id.notification_title);
                DatePicker datePicker = findViewById(R.id.datepicker);

                String sHours = hours.getText().toString();
                String sMinutes = minutes.getText().toString();
                String sTitle = title.getText().toString();

                if (sHours.matches("")) {
                    Toast.makeText(CreateNotificationActivity.this, "No hour selected.", Toast.LENGTH_SHORT).show();
                }
                else if (sMinutes.matches("")) {
                    Toast.makeText(CreateNotificationActivity.this, "No minute selected.", Toast.LENGTH_SHORT).show();
                }
                else if (sTitle.matches("")) {
                    Toast.makeText(CreateNotificationActivity.this, "No Title entered.", Toast.LENGTH_SHORT).show();
                }
                else if (!sMinutes.matches("") && !sHours.matches("") && !sTitle.matches("")) {
                    SaveNotification(
                            sTitle,
                            sHours,
                            sMinutes,
                            datePicker.getYear(),
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth());
                }
                else {
                    Snackbar.make(view, "Something went wrong!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void SaveNotification(String notificationTitle, String hours, String minutes, int year, int month, int day) {
        Log.d("Create Notification", "*************Notification Created************");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, Integer.valueOf(hours), Integer.valueOf(minutes));

        Intent newIntent = new Intent(CreateNotificationActivity.this, ScheduleNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(CreateNotificationActivity.this, 0, newIntent, 0);


        setResult(Activity.RESULT_OK);
        Toast.makeText(CreateNotificationActivity.this, "Notification Created!", Toast.LENGTH_LONG).show();
        finish();
    }

}
