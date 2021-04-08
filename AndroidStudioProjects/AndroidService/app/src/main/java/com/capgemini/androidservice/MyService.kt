package com.capgemini.androidservice

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.concurrent.thread

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Service Created hahaha", Toast.LENGTH_SHORT).show()
        Log.d("MyService","Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"Service Started hahaha", Toast.LENGTH_SHORT).show()
        Log.d("MyService","Service Started")
        var filename = ""
        if(intent?.hasExtra("filename")==true){
            filename = intent.getStringExtra("filename")!!
        }
        //multiple threads at a time
        //you need to create threads
        val t1= thread {
            Thread.sleep(2000)
            //execute task
            //task completed- Status notification or send broadcast
            val bIntent = Intent(SERVICE_BROADCAST_ACTION)
            bIntent.putExtra("time",Calendar.getInstance().time.toString())
            bIntent.putExtra("downloaded",filename)
            sendBroadcast(bIntent)
            stopSelf()
        }
            //status bar notification for making foreground service
        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder:Notification.Builder
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            val ch = NotificationChannel("test", "MyChannel",
                NotificationManager.IMPORTANCE_DEFAULT)
            nManager.createNotificationChannel(ch)
            builder = Notification.Builder(this,"test")
        }
        else{
            builder = Notification.Builder(this)
        }
        builder.setContentTitle("Downloading... ")
        builder.setContentText("$filename download in progress")
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)

        val i = Intent(this,MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, i, 0)
        builder.setContentIntent(pi)

        val notification = builder.build()
        startForeground(1,notification)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"ServiceDestroyed :(", Toast.LENGTH_SHORT).show()
        Log.d("MyService","Service Stopped")
    }
}

