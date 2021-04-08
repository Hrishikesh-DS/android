package com.capgemini.andriodui


import android.app.*
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class MyDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var dlg: Dialog

        val bundle = arguments
        val message = bundle?.getString("msg")
        val btn1 = bundle?.getString("btn1")
        val btn2 = bundle?.getString("btn2")
        val dlgType = bundle?.getInt("type")
        // AlterDialog
        val parent = requireActivity() as Activity
        val builder = AlertDialog.Builder(parent)

        val v = layoutInflater.inflate(R.layout.activity_main,null)// to add a layout to dialog
        builder.setView(v)
        when(dlgType){
            1 ->{
                builder.setTitle("Conformation")
                builder.setMessage(message)
                builder.setPositiveButton(btn1,DialogInterface.OnClickListener {
                    _, _ ->
                    parent.finish()
                })
                builder.setNegativeButton(btn2) {dlg, i ->
                    dlg.cancel()
                }
                builder.setNeutralButton("Cancel"){
                    dlg,i ->
                    dlg.cancel()
                }
                builder.setCancelable(false)
                dlg = builder.create()


            }
            2->{
                //timePicker dialog
                dlg = TimePickerDialog(parent, parent as TimePickerDialog.OnTimeSetListener,
                        12, 0, false)
            }
            3->{
                //datePicket dialog
                val calendar = Calendar.getInstance()
                dlg = DatePickerDialog(parent,parent as DatePickerDialog.OnDateSetListener,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE))
            }
        }



        return dlg
    }
}