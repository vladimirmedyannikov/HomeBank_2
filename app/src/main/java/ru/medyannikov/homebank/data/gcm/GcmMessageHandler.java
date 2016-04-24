package ru.medyannikov.homebank.data.gcm;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import ru.medyannikov.homebank.R;

/**
 * Created by vladimir on 21.04.16.
 */
public class GcmMessageHandler extends GcmListenerService {
    private static final int MESSAGE_NOTIFICATION_ID = 123456;

    @Override
    public void onMessageReceived(String from, Bundle data) {

        Bundle notification = (Bundle)data.get("notification");
        String message = notification.getString("body");
        String title  = notification.getString("title");
        Log.d("Bundle data", data.toString());
        createNotification(title, message);
    }

    private void createNotification(String title,String message) {
        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }
}
