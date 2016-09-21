package com.cab.alington.cab.splashscreen.FCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by User on 6/27/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    int a = 1;
    SharedPreferences count;
    String Title;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        prefs = getSharedPreferences("fcmdata", MODE_PRIVATE);
        editor = prefs.edit();
       Log.e(TAG, "From: " + remoteMessage.getData().get("type"));
        Log.e(TAG, "From: " + remoteMessage.getNotification());
        Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getTitle());
        //Calling method to generate notification
       /* String url = countercondition(remoteMessage.getData().get("type"));
        sendNotification(remoteMessage, url);
*/


    }

   /* private String countercondition(String type) {
        if (type.equalsIgnoreCase("support")) {
            editor.putInt("support", prefs.getInt("support", 0) + 1);
            editor.commit();
            Title = "Support Ticket";
            return Constants.support_ticket;
        } else if (type.equalsIgnoreCase("install")) {
            editor.putInt("installt", prefs.getInt("install", 0) + 1);
            editor.commit();
            Title = "Instalation Ticket";
            return Constants.installationTicket_url;
        } else if (type.equalsIgnoreCase("task")) {
            Title = "Task Ticket";
            editor.putInt("task", prefs.getInt("task", 0) + 1);
            editor.commit();
            return Constants.taskTicket_url;
        }

        return null;
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(RemoteMessage messageBody, String url) {
        count = getSharedPreferences("count", MODE_PRIVATE);
        SharedPreferences.Editor editor = count.edit();
        // editor.putInt("count",0);
       // Log.e("url", url);
        editor.commit();
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", Title);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(messageBody.getNotification().getTitle())
                .setContentText(messageBody.getNotification().getBody())
                .setSound(Uri.parse(messageBody.getNotification().getSound()))
                .setContentIntent(pendingIntent);
        Notification notification = new Notification();
        // Play default notification sound
        //  notification.defaults |= Notification.DEFAULT_SOUND;
        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(count.getInt("count", 0), notificationBuilder.build());
        editor.putInt("count", count.getInt("count", 0) + 1);
        editor.commit();
        Intent inten = new Intent("updatenotify");
        int count = prefs.getInt("support", 0) + prefs.getInt("install", 0) + prefs.getInt("task", 0);
        Log.e("Counte", String.valueOf(count));
        inten.putExtra("count",count);
        LocalBroadcastManager.getInstance(this).sendBroadcast(inten);
      //  Log.e("count", String.valueOf(count.getInt("count", 0)));
    }*/
}






