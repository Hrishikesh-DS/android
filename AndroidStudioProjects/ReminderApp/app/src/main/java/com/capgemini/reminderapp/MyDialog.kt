package com.capgemini.reminderapp

import android.app.*
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class MyDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var dlg:Dialog
        val bundle = arguments

        val message = bundle?.getString("msg")
        val btn1 = bundle?.getString("btn1")
        val btn2 = bundle?.getString("btn2")
        val dlgType = bundle?.getInt("type")
        val parent = requireActivity() as Activity
        val builder = AlertDialog.Builder(parent)
        when(dlgType){
            2->{
                dlg = TimePickerDialog(parent, parent as TimePickerDialog.OnTimeSetListener,
                    12, 0, false)
            }
            1->{
                val calendar = Calendar.getInstance()
                dlg = DatePickerDialog(parent,parent as DatePickerDialog.OnDateSetListener,
                    calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE))
            }
//            3->{
//                builder.setTitle("Conformation")
//                builder.setMessage(message)
//                builder.setPositiveButton(btn1){dlg, i->
//
//                }
//                builder.setNegativeButton(btn2) {dlg, i ->
//                    dlg.cancel()
//                }
//                builder.setCancelable(false)
//                dlg = builder.create()
//            }
        }
        return dlg
    }
}