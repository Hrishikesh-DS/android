package com.capgemini.andriodui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    var isDataEntered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
//        submitB.setOnClickListener(this)
//        cancelB.setOnClickListener(this)
        userIdE.setText("Demo",TextView.BufferType.NORMAL)

//        passwordE.setOnKeyListener { v, keyCode, event ->  }
    }

    override fun onResume() {
        super.onResume()
        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nManager.cancel(1)
    }

    fun cancelClick(view:View){
        Toast.makeText(this,"Cancel Button clicked", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun submitClick(view:View){
        val userId=userIdE.text.toString()
        val password = passwE.text.toString()

        //do validation on data
        if(userId.isNotEmpty() && password.isNotEmpty()){
            Toast.makeText(this,"You entered \nUserID: $userId \nPassword: $password",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Enter all the data", Toast.LENGTH_LONG).show()
        }


    }

    override fun onPause() {
        super.onPause()
        if(!isDataEntered){
            //send status bar notification
            sendNotification()
        }

    }

    private fun sendNotification() {
        //1. Get the reference of notification manager
        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        //NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE) in java


        //2. Create Notification
        lateinit var builder: Notification.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("test", "AndroidUI",
                    NotificationManager.IMPORTANCE_DEFAULT)

            nManager.createNotificationChannel(channel)//register channel

            builder = Notification.Builder(this,"test")
        }
        else{
            builder = Notification.Builder(this)
        }


        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("Authentication")
        builder.setContentText("Please complete the Sign-in procedure")
        builder.setAutoCancel(true)

        val tryIntent = Intent(this, MainActivity::class.java)
        val resumeIntent = PendingIntent.getService(this, 0,tryIntent,0)
        val resumeAction = Notification.Action.Builder(android.R.drawable.ic_dialog_info,"Try Again",resumeIntent).build()
        builder.addAction(resumeAction)
        //create remote view(like Spotify)
//        val l = layoutInflater.inflate(R.layout.activity_main_constraint,null)
//        //set remote view
//        builder.setCustomContentView()

        val intent = Intent(this, AuthActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0,intent,0)

        builder.setContentIntent(pi)

        val myNotification = builder.build()
//        myNotification.flags = Notification.FLAG_AUTO_CANCEL or  Notification.FLAG_NO_CLEAR
        //To make it non clear able


        //3. Send notification
        nManager.notify(1,myNotification)
    }
//    override fun onClick(v:View?){
//        val id=v!!.id
//        when(id){
//            R.id.submitB ->{
//                val t = Toast.makeText(this,"Submit Button clicked", Toast.LENGTH_SHORT)
//                t.setGravity(Gravity.TOP,10,10)
//                t.show()
//            }
//            R.id.cancelB ->{
//                val t = Toast.makeText(this,"Cancel Button clicked", Toast.LENGTH_SHORT)
//                t.setGravity(Gravity.TOP,10,10)
//                t.show()
//            }
//        }
//    }
}
