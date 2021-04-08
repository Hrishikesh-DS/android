package com.capgemini.reminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

class Interval : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interval)
    }
    var min=""
    fun radioClicked(view: View){
        if(view is RadioButton){
            val isChecked = view.isChecked
            if(isChecked){
                when(view.id){
                    R.id.min15R->min="15"
                    R.id.min30R->min="30"
                    R.id.min60R->min="60"
                    else->min="0"
                }
            }
        }
//        Toast.makeText(this,"Minutes Interval: $min",Toast.LENGTH_SHORT).show()
        val i = Intent(this, ReminderActivity::class.java)
        i.putExtra("minutesInterval",min)
        startActivity(i)
    }
}