package com.example.canozel.hatirlatici;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by canozel on 14.12.2015.
 */
public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;
    MainActivity main;

    public AlarmService() {
        super("AlarmService");
    }
    @Override
    public void onHandleIntent(Intent intent) {
        main = new MainActivity();
        String msg = "Hatırlatıcı çalıştı.";

        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_stat_name)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
    }
}
