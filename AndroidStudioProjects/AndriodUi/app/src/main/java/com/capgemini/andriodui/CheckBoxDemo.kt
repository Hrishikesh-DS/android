package com.capgemini.andriodui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

class CheckBoxDemo : AppCompatActivity() {

    var cityList= mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box_demo)
    }

    fun checkboxClicked(view: View){
        if(view is CheckBox){
            val city = view.text.toString()
            if(view.isChecked){
                cityList.add(city)
            }
            else{
                cityList.remove(city)
            }
        }
        Toast.makeText(this,"Selected Cities: $cityList",Toast.LENGTH_LONG).show()
    }
    override fun onPause(){
        super.onPause()
        Toast.makeText(this,"Selected Cities: $cityList",Toast.LENGTH_LONG).show()
    }
}