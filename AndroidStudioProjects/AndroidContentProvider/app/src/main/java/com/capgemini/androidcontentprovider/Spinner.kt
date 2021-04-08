package com.capgemini.androidcontentprovider

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spinner.*
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import java.lang.reflect.Array
import java.util.*

class Spinner : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val options = mutableListOf<String>("Missed Calls", "Incoming Calls", "Outgoing Calls")
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, options
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var args:kotlin.Array<String>
        when (position) {
            0 -> {
                Toast.makeText(this, "Missed calls", Toast.LENGTH_LONG).show()
                 args = arrayOf("${CallLog.Calls.MISSED_TYPE}")
            }
            1 -> {
                Toast.makeText(this, "Incoming calls", Toast.LENGTH_LONG).show()
                args = arrayOf("${CallLog.Calls.INCOMING_TYPE}")
            }
            2 -> {
                Toast.makeText(this, "Outgoing calls", Toast.LENGTH_LONG).show()
                args = arrayOf("${CallLog.Calls.OUTGOING_TYPE}")
            }
            else->{
                args = arrayOf("${CallLog.Calls.OUTGOING_TYPE}")
            }
        }
        val cr = contentResolver

        val cursor = cr.query(
            Uri.parse("content://call_log/calls"),null,
            "${CallLog.Calls.TYPE} = ?",args,null)
        var msg = ""

        if (cursor?.count!! > 0) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                val user = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))
                val number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))
                val calldate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE))
                val date=Date(calldate)
                msg += "User: $user Number: $number CallDate: ${date}  \n"
                Log.d("Spinner", " Number: $number CallDate: $calldate ")
            }
        }
        dataT.text=msg
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "Please select something", Toast.LENGTH_SHORT).show()
    }

}