package com.karetolabs.cinemapp

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeStream
import android.graphics.Movie.decodeStream
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import com.karetolabs.cinemapp.login.LoginActivity
import retrofit2.http.Url
import java.io.InputStream
import java.net.URL


class MovieMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //Enviamos datos para actualizar
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("MovieMessagingService", "RemoteMessage"+ message.data)
        super.onMessageReceived(message)


//      val style = NotificationCompat.BigPictureStyle()
        val style = NotificationCompat.BigTextStyle()
        style.setBigContentTitle(message.data["title"])
        style.setSummaryText(message.data["body"])



        val imageNotification = BitmapFactory.decodeStream(
            URL("https://1.bp.blogspot.com/-dwL58chu7wo" +
                    "/WvD1RrHln3I/AAAAAAAAFUg/cRTc0IZga_wM" +
                    "PTWr3CI53IZ5BwtnZMeYACLcBGAs/s1600/Scre" +
                    "en%2BShot%2B2018-05-05%2Bat%2B11.49.30%2BAMimage1.png").content as InputStream
        )
        //style.bigPicture(imageNotification)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("body", message.data["body"])

        val pendingIntent = PendingIntent.getActivity(
            this,
            201,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        val notificationBuilder = NotificationCompat.Builder(this, "cinemapp")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["body"])
            .setStyle(style)
            //.setDefaults(Notification.DEFAULT_VIBRATE)
            //.setDefaults(Notification.DEFAULT_LIGHTS)
            //.setDefaults(Notification.DEFAULT_SOUND)
            .setDefaults(Notification.DEFAULT_ALL)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_add, "Ver", pendingIntent)
            .setContentIntent(pendingIntent) // Evento en la notificacion

        val notification = NotificationManagerCompat.from(this)
        notification.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())


        val intentAction = Intent("Notification_Action")
        intentAction.putExtra("bodyAction", message.data["body"])

        LocalBroadcastManager.getInstance(this).sendBroadcast(intentAction)

    }

}