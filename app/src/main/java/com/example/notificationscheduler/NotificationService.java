package com.example.notificationscheduler;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends IntentService {

    private NotificationManager alarmNotificationManager;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void SendNotification(Notification notification) {
        Log.d("AlarmService", "Preparing to send notification...: ");
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MainActivity.getPrimaryChannelId())
                .setContentTitle("Alarm")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        builder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, builder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}
