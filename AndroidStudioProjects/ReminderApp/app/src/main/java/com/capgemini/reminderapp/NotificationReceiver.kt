package com.capgemini.reminderapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

class NotificationReceiver : BroadcastReceiver() {
    lateinit var context:Context
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        this.context=context
        sendNotification(1)

    }
    private fun sendNotification(i: Int) {

        val nManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        lateinit var builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "test", "Reminder",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            nManager.createNotificationChannel(channel)

            builder = Notification.Builder(context, "test")
        } else {
            builder = Notification.Builder(context)
        }


        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        if (i == 0) {
            builder.setContentTitle("Reminder")
            builder.setContentText("New Reminder Added")
        }
        if (i == 1) {
            builder.setContentTitle(titleV)
            builder.setContentText(desV)
        }
        builder.setAutoCancel(true)

        val intent = Intent(context, RemainderView::class.java)
        val pi = PendingIntent.getActivity(context, 0, intent, 0)

        builder.setContentIntent(pi)

        val myNotification = builder.build()

        nManager.notify(1, myNotification)

    }

}