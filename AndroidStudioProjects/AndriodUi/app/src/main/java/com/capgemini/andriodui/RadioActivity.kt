package com.capgemini.andriodui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

class RadioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio)
    }
    var selectedCity=""
    fun radioClicked(view: View){
        if(view is RadioButton){
            val isChecked=view.isChecked
            if(isChecked){
                when(view.id){
                    R.id.bangaloreR->selectedCity=view.text.toString()
                    R.id.mumbaiR->selectedCity=view.text.toString()
                    R.id.chennaiR->selectedCity=view.text.toString()
                }
                Toast.makeText(this,"Selected City: $selectedCity",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onPause(){
        super.onPause()
        Toast.makeText(this,"Selected Cities: $selectedCity",Toast.LENGTH_LONG).show()
    }
}