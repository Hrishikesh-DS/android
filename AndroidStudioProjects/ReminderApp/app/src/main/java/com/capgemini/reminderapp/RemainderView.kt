package com.capgemini.reminderapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_remainder_view.*
import kotlinx.android.synthetic.main.activity_reminder.*

var reminderList = mutableListOf<Reminder>()

class RemainderView : AppCompatActivity(), AdapterView.OnItemClickListener {
    lateinit var adapter: ArrayAdapter<Reminder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remainder_view)

//        val intent = intent
//        var titleR=intent.getStringExtra("title")?:""
//        var desR=intent.getStringExtra("des")?:""
//        var dateR=intent.getStringExtra("date")?:""
//        var timeR=intent.getStringExtra("time")?:""
//        if(titleR.isNotEmpty() && desR.isNotEmpty() && dateR.isNotEmpty() && timeR.isNotEmpty()) {
//            reminderList.add(Reminder(titleR, desR, dateR, timeR))
//        }
//        else{
//            reminderList = reminderList
//        }

        adapter = ReminderAdapter(this, R.layout.reminder_list, reminderList)
        reminderL.adapter = adapter
        reminderL.setOnItemClickListener(this)
        Log.d("ReminderView", "OnCreate")
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        retrieveFromDB()
        adapter.notifyDataSetChanged()
    }


    fun retrieveFromDB() {
        reminderList.clear()
        val wrapper = DBWrapper(this)
        val cursor = wrapper.retrieveCredentials()
        if (cursor?.count!! > 0) {
            cursor.moveToFirst()
            val idxTitle = cursor.getColumnIndex(DBHelper.CLM_TITLE)
            val idxDesc = cursor.getColumnIndex(DBHelper.CLM_DESC)
            val idxDate = cursor.getColumnIndex(DBHelper.CLM_DATE)
            val idxTime = cursor.getColumnIndex(DBHelper.CLM_TIME)
            do {
                val titleR = cursor.getString(idxTitle)
                val desR = cursor.getString(idxDesc)
                val dateR = cursor.getString(idxDate)
                val timeR = cursor.getString(idxTime)
                reminderList.add(Reminder(titleR, desR, dateR, timeR))
            } while (cursor.moveToNext())
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d("ReminderView", "OnPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d("ReminderView", "OnStop")
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val std = reminderList[position]
        var builder = AlertDialog.Builder(this)
        builder.setMessage(reminderList[position]?.title)
            .setPositiveButton("EDIT") { _, _ ->

                var intent: Intent = Intent(this, ReminderActivity::class.java)
                val titleEdit = std.title
//                    val desEdit = std.des
//                    val dateEdit = std.date
//                    val timeEdit = std.time
////        Toast.makeText(this,"position: $position titleEdit:$titleEdit desEdit: $desEdit dateEdit: $dateEdit timeEdit:$timeEdit",Toast.LENGTH_LONG).show()
//                    intent.putExtra("callingactivity","listrem")
//                    intent.putExtra("titleEdit",titleEdit)
//                    intent.putExtra("desEdit",desEdit)
//                    intent.putExtra("dateEdit",dateEdit)
//                    intent.putExtra("timeEdit",timeEdit)
//                    startActivity(intent)

                intent.putExtra("titleEdit", titleEdit)
                startActivity(intent)
                adapter.notifyDataSetChanged()
            }
            .setNeutralButton("DELETE") { _, _ ->
                val wrapper = DBWrapper(this)
                val titleEdit = std.title
                wrapper.deleteCredentials(titleEdit ?: "")
                Toast.makeText(this, titleEdit, Toast.LENGTH_SHORT).show()
                intent = Intent(this, RemainderView::class.java)
                adapter.notifyDataSetChanged()
                startActivity(intent)
                finish()

            }
            .setNegativeButton("MENU") { _, _ ->
                // Toast.makeText(applicationContext,"No", Toast.LENGTH_LONG).show()
                finish()

            }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()


    }

}

data class Reminder(val title: String?, val des: String?, val date: String?, val time: String?)

class ReminderAdapter(context: Context, val layoutRes: Int, val data: List<Reminder>) :
    ArrayAdapter<Reminder>(context, layoutRes, data) {

    override fun getItem(position: Int): Reminder? {
        return data[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val reminder = getItem(position)
        var view = convertView ?: LayoutInflater.from(context).inflate(layoutRes, null)
        val titleT = view.findViewById<TextView>(R.id.titleT)
        val desT = view.findViewById<TextView>(R.id.descT)
        val dateT = view.findViewById<TextView>(R.id.dateT)
        val timeT = view.findViewById<TextView>(R.id.timeT)

        titleT.text = reminder?.title
        desT.text = reminder?.des
        dateT.text = reminder?.date
        timeT.text = reminder?.time

        return view

    }
}