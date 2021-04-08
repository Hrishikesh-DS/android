package com.capgemini.androidreceiver

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

//custom broadcast action
val MY_BROADCAST_ACTION = "com.capgemini.androidreceiver.action.mybroadcast"
val MY_BROADCAST_SCHEDULE_ACTION  = "com.capgemini.androidreceiver.action.scheduled"
val MY_BOOT_INTENT=Intent.ACTION_USER_PRESENT
val MY_SMS_RECEIVER = Telephony.Sms.Intents.SMS_RECEIVED_ACTION

class MainActivity : AppCompatActivity() {

    val receiver=MyReceiver()
    val lReceiver=LocalReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //system generated broadcast
        val filter = IntentFilter(Intent.ACTION_USER_PRESENT)
        filter.addAction(Intent.ACTION_BATTERY_LOW)//adding multiple actions to same filter
        registerReceiver(receiver,filter)

//        val filter1 = IntentFilter(Intent.ACTION_BATTERY_LOW)
//        registerReceiver(receiver,filter1)
        val filter1 = IntentFilter(MY_BROADCAST_ACTION)
        filter1.addAction(MY_BROADCAST_SCHEDULE_ACTION)
        filter1.addAction(MY_BOOT_INTENT)
        filter1.addAction(MY_SMS_RECEIVER)
        registerReceiver(lReceiver,filter1)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        unregisterReceiver(lReceiver)
    }

    //user defined broadcast
    fun buttonClick(view: View) {
        when(view.id){
            R.id.sendB->{
                val i = Intent(MY_BROADCAST_ACTION)
                i.putExtra("time",Calendar.getInstance().time.toString())
                sendBroadcast(i)
            }
            R.id.scheduleB->{
                //scheduling the broadcast event after 10 sec(alarm manager)
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

//                val bIntent = Intent(this, LocalReceiver::class.java)//doesnt work in inner class
                val bIntent = Intent(MY_BROADCAST_SCHEDULE_ACTION)
                val pi = PendingIntent.getBroadcast(this,0,bIntent,0)

//                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+10_000, pi)
//                alarmManager.cancel(pi) - to cancel the above action(make a button)
                alarmManager.setInexactRepeating(AlarmManager.RTC,
                    System.currentTimeMillis()+10000,3000, pi)
            }
        }
    }

    fun sendNotification(){
        val nManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        lateinit var builder: Notification.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("test", "Reminder",
                NotificationManager.IMPORTANCE_DEFAULT)

            nManager.createNotificationChannel(channel)

            builder = Notification.Builder(this,"test")
        }
        else{
            builder = Notification.Builder(this)
        }


        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("Bootup done")
        builder.setContentText("Phone was switched on now")
        builder.setAutoCancel(true)

        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0,intent,0)

        builder.setContentIntent(pi)

        val myNotification = builder.build()

        nManager.notify(1,myNotification)
    }

    //private class inside activity(to access the text)
    //make it inner to use the xml stuff
    inner class LocalReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                MY_BROADCAST_ACTION->{
                    val time = intent?.getStringExtra("time")
                    Toast.makeText(context,"Time: $time",Toast.LENGTH_LONG).show()
                    tv.text=time
                }
                MY_BOOT_INTENT->{
                    sendNotification()
                }
                MY_BROADCAST_SCHEDULE_ACTION->{
                    Toast.makeText(context,"10 seconds delay over",Toast.LENGTH_LONG).show()
                    tv.text = "Welcome To My App"
                }
                MY_SMS_RECEIVER->{
                    val receivedSms = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                    for(msg in receivedSms){
                        val sender = msg.displayOriginatingAddress
                        val body = msg.messageBody
                        smsT.text="Received from: $sender: $body"

                    }
                }


            }


        }

    }
}