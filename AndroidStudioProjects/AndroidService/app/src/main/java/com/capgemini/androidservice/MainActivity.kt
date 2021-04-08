package com.capgemini.androidservice

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

val SERVICE_BROADCAST_ACTION = "com.capgemini.androidservice.action.task_completed"
//event identifier

class MainActivity : AppCompatActivity() {
    val receiver=MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(SERVICE_BROADCAST_ACTION)
        registerReceiver(receiver,filter)

    }

    fun buttonClick(view: View){
        val serviceIntent= Intent(this,MyService :: class.java)
//        val i = Intent(this,MyIntentService::class.java)
        when(view.id){
            R.id.startB ->{
                serviceIntent.putExtra("filename","xyz.png")
                startService(serviceIntent)
//                i.putExtra("filename","xyz.png")
//                startService(i)
            }//launch service
            R.id.stopB->
            {stopService(serviceIntent)}//stop service
//            stopService(i)
            R.id.scheduleB->{
                //1)schedule to run after 10 secs(time bound)
                val am = getSystemService(ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, MyService::class.java)
                intent.putExtra("filename","abc.png")
                val pi = PendingIntent.getService(this,0,intent,0)

                am.set(AlarmManager.RTC,System.currentTimeMillis()+10_000, pi)
            }
            R.id.jScheduleB->{
                //2)schedule my service to run whenever network is available on the phone
                // JobScheduler

                val jScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
                val builder = JobInfo.Builder(1,
                    ComponentName(this, MyJobService::class.java))

                builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                builder.setMinimumLatency(2000)
                builder.setPeriodic(15*60*1000)
                if(jScheduler.schedule(builder.build())==JobScheduler.RESULT_SUCCESS){
                    Toast.makeText(this,"Job Scheduled...", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this,"Problem with Job Scheduling...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    inner class MyReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                SERVICE_BROADCAST_ACTION->{
                    val time = intent.getStringExtra("time")
                    val filename=intent.getStringExtra("downloaded")
                    textView.text="$filename downloaded at: $time"
                }
            }

        }
    }
}