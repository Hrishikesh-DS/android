package com.capgemini.androidservice

import android.app.IntentService
import android.content.Intent
import java.util.*

//// TODO: Rename actions, choose action names that describe tasks that this
//// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
//const val ACTION_FOO = "com.capgemini.androidservice.action.FOO"
//const val ACTION_BAZ = "com.capgemini.androidservice.action.BAZ"
//
//// TODO: Rename parameters
//const val EXTRA_PARAM1 = "com.capgemini.androidservice.extra.PARAM1"
//const val EXTRA_PARAM2 = "com.capgemini.androidservice.extra.PARAM2"
//
///**
// * An [IntentService] subclass for handling asynchronous task requests in
// * a service on a separate handler thread.
// * TODO: Customize class - update intent actions and extra parameters.
// */
class MyIntentService : IntentService("MyIntentService") {
    //executed on a separate thread(but here its only 1 thread at a time
    override fun onHandleIntent(intent: Intent?) {
        val filename = intent?.getStringExtra("filename")
        //no need to create a thread(auto-created 1 thread), directly execute the task -http request

        //send broadcast or status bar notification
        val bIntent = Intent(SERVICE_BROADCAST_ACTION)
        bIntent.putExtra("time", Calendar.getInstance().time.toString())
        bIntent.putExtra("downloaded",filename)
        sendBroadcast(bIntent)
    }

}