package com.capgemini.reminderapp

import android.app.*
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_reminder.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

//val MY_BROADCAST_SCHEDULE_ACTION = "com.capgemini.androidreceiver.action.scheduled"
var titleV: String = ""
var timeV: String = ""
var dateV: String = ""
var desV: String = ""

class ReminderActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

//    val myReceiver = LocalReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

//        val filter = IntentFilter(MY_BROADCAST_SCHEDULE_ACTION)
//        registerReceiver(myReceiver, filter)

        val int = intent
        val wrapper = DBWrapper(this)
        val cursor = wrapper.retrieveCredentials()
        if (cursor?.count!! > 0) {
            cursor.moveToFirst()
            val idxTitle = cursor.getColumnIndex(DBHelper.CLM_TITLE)
            val idxDes = cursor.getColumnIndex(DBHelper.CLM_DESC)
            val idxTime = cursor.getColumnIndex(DBHelper.CLM_TIME)
            val idxDate = cursor.getColumnIndex(DBHelper.CLM_DATE)
            do {
                val titleRem = cursor.getString(idxTitle)
                if (titleRem == int.getStringExtra("titleEdit")) {
                    val desRem = cursor.getString(idxDes)
                    val dateRem = cursor.getString(idxDate)
                    val timeRem = cursor.getString(idxTime)
                    wrapper.deleteCredentials(titleRem)
                    Toast.makeText(
                        this,
                        "titleEdit:$titleRem desEdit: $desRem dateEdit: $dateRem timeEdit:$timeRem",
                        Toast.LENGTH_LONG
                    ).show()
                    val titleEdit: TextView = findViewById<TextView>(R.id.titleE)
                    val desEdit: TextView = findViewById<TextView>(R.id.desE)
                    val dateEdit: TextView = findViewById<TextView>(R.id.dateE)
                    val timeEdited: TextView = findViewById(R.id.timeE) as TextView
                    timeEdited.text = timeRem
                    titleEdit.text = titleRem
                    desEdit.text = desRem
                    dateEdit.text = dateRem
                }
            } while (cursor.moveToNext())

        }


//        if(int.getStringExtra("callingactivity")=="listrem") {
//            var titleRem = int.getStringExtra("titleEdit")
//            var desRem = int.getStringExtra("desEdit")
//            var dateRem = int.getStringExtra("dateEdit")
//            var timeRem = int.getStringExtra("timeEdit")
//            Toast.makeText(this,"titleEdit:$titleRem desEdit: $desRem dateEdit: $dateRem timeEdit:$timeRem",Toast.LENGTH_LONG).show()
//            val titleEdit: TextView = findViewById<TextView>(R.id.titleE)
//            val desEdit: TextView = findViewById<TextView>(R.id.desE)
//            val dateEdit: TextView = findViewById<TextView>(R.id.dateE)
//            val timeEdited: TextView = findViewById(R.id.timeE) as TextView
//            timeEdited.text=timeRem
//            titleEdit.text = titleRem
//            desEdit.text = desRem
//            dateEdit.text = dateRem
//
//        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun clickAddReminder(view: View) {
//        var intent:Intent = Intent(this,RemainderView::class.java)
        intent = Intent(this, MainActivity::class.java)
        titleV = titleE.text.toString()
        desV = desE.text.toString()
        dateV = dateE.text.toString()
        timeV = timeE.text.toString()

        if (titleV.isNotEmpty() && desV.isNotEmpty() && dateV.isNotEmpty() && timeV.isNotEmpty()) {
//            intent.putExtra("title",titleV)
//            intent.putExtra("des",desV)
//            intent.putExtra("date",dateV)
//            intent.putExtra("time",timeV)
//
//            startActivity(intent)
            val minIntent = intent
            val minstr = minIntent.getStringExtra("minutesInterval")
            val min = minstr?.toInt()?:0
            Toast.makeText(this,"$min",Toast.LENGTH_SHORT).show()

            saveToDB(titleV, desV, dateV, timeV)
//            sendNotification(0)
//            val intentReceiver = Intent(this, NotificationReceiver::class.java)
//            val pi=PendingIntent.getBroadcast(this,0,intentReceiver,0)
            when (view.id) {
                R.id.addRemB -> {
                    val alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager
                    val mil = convertDateTime(dateV, timeV,min)
                    val bIntent = Intent(this,NotificationReceiver::class.java)
                    val pi = PendingIntent.getBroadcast(this, 0, bIntent, 0)
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, mil+min, pi)
                }
            }
            addEventToCalender(titleV, desV, dateV, timeV, min)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Data incomplete", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addEventToCalender(
        title: String,
        des: String,
        date: String,
        time: String,
        min: Int
    ) {
        var mil = convertDateTime(date, time, min)
        val timeZone = TimeZone.getDefault()
        val values = ContentValues().apply {
            put(CalendarContract.Events.CALENDAR_ID, 1)
            put(CalendarContract.Events.DTSTART, mil + min)
            put(CalendarContract.Events.DTEND, mil + (60 * 60000))
            put(CalendarContract.Events.TITLE, title)
            put(CalendarContract.Events.DESCRIPTION, des)
            put(CalendarContract.Events.ALL_DAY, 0)
            put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.id)
        }
        this.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)

    }

    @RequiresApi(Build.VERSION_CODES.O)

    private fun convertDateTime(date: String, time: String, min: Int): Long {
        Log.d("Date", date)
        var formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val dateTime = LocalDate.parse(date, formatter)

        val t = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
        val m = t.atDate(dateTime)
        val a = "$m:00.800Z"
        val mil = Instant.parse(a).toEpochMilli() - 19800000 - min
        Log.d("Date", a)
        Log.d("Date", mil.toString())
        return mil
    }

    private fun saveToDB(titleV: String, desV: String, dateV: String, timeV: String) {
        val wrapper = DBWrapper(this)
        val rowid = wrapper.saveCredentials(titleV, desV, dateV, timeV)
        if (rowid.toInt() != -1) {
            Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show()
        }
    }

    fun clickRemCancel(view: View) {
        finish()
    }

    fun clickDate(view: View) {
        val dlg = MyDialog()
        val bundle = Bundle()
        bundle.putInt("type", 1)
        dlg.arguments = bundle
        dlg.show(supportFragmentManager, "aaa")

    }

    fun clickTime(view: View) {
        val dlg = MyDialog()
        val bundle = Bundle()
        bundle.putInt("type", 2)
        dlg.arguments = bundle
        dlg.show(supportFragmentManager, "bbb")
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val texts: TextView = findViewById(R.id.timeE) as TextView
        val day: String =
            "${if ((hourOfDay) < 10) "0" else ""}$hourOfDay:${if ((minute) < 10) "0" else ""}${minute}"
        texts.text = day
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val texts: TextView = findViewById(R.id.dateE) as TextView
        val date: String =
            "$year/${if ((month + 1) < 10) "0" else ""}${month + 1}/${if (dayOfMonth < 10) "0" else ""}${dayOfMonth}"
        texts.text = date
    }

//    private fun sendNotification(i: Int) {
//        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//        lateinit var builder: Notification.Builder
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                "test", "Reminder",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//
//            nManager.createNotificationChannel(channel)
//
//            builder = Notification.Builder(this, "test")
//        } else {
//            builder = Notification.Builder(this)
//        }
//
//
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
//        if (i == 0) {
//            builder.setContentTitle("Reminder")
//            builder.setContentText("New Reminder Added")
//        }
//        if (i == 1) {
//            builder.setContentTitle(titleV)
//            builder.setContentText(desV)
//        }
//        builder.setAutoCancel(true)
//
//        val intent = Intent(this, RemainderView::class.java)
//        val pi = PendingIntent.getActivity(this, 0, intent, 0)
//
//        builder.setContentIntent(pi)
//
//        val myNotification = builder.build()
//
//        nManager.notify(1, myNotification)
//
//    }

//    inner class LocalReceiver : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            when (intent?.action) {
//                MY_BROADCAST_SCHEDULE_ACTION -> {
//                    Toast.makeText(context, "You received notification", Toast.LENGTH_LONG).show()
//                    sendNotification(1)
//                }
//            }
//        }
//
//    }

}