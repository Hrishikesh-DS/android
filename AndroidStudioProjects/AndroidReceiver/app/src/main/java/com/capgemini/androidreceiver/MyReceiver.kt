package com.capgemini.androidreceiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val action = intent.action
        Log.d("MyReceiver","Action: $action")
        when(action){
            Intent.ACTION_USER_PRESENT->{
//                Toast.makeText(context,"Welcome back Hrishi ...",Toast.LENGTH_SHORT).show()
//                Log.d("MyReceiver","unlock event received")
            }
            Intent.ACTION_BATTERY_LOW->{
                Toast.makeText(context,"Low battery!!!",Toast.LENGTH_SHORT).show()
            }
        }
    }

}