package com.capgemini.andriodui

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main_constraint.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        passwordE.setOnKeyListener { v, keyCode, event ->
            if(event.action==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_ENTER){
                if(passwordE.text.toString().length<8){
                    Toast.makeText(this,"Minimum 8 characters",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Accepted",Toast.LENGTH_SHORT).show()
                }
                true
            }
            false
        }
    }
    fun cancelClick(view:View){
        Toast.makeText(this,"Cancel Button clicked", Toast.LENGTH_SHORT).show()
        finish()
    }
    fun clickRegister(view: View){
        val nameV=nameE.text.toString()
        val mobV=mobileE.text.toString()
        val emailV = emailE.text.toString()
        val addressV=addressE.text.toString()
        val postalV=postalE.text.toString()
        val passV=passwordE.text.toString()
        if(nameV.isNotEmpty() && mobV.isNotEmpty() && emailV.isNotEmpty() && addressV.isNotEmpty() && postalV.isNotEmpty() && passV.isNotEmpty()){
            Toast.makeText(this,"You entered \nName: $nameV \nMobile: $mobV \nEmail: $emailV \nAddress: $addressV \nPostal Code: $postalV \nPassword: $passV",
                Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Enter all the data", Toast.LENGTH_LONG).show()
        }
    }

    fun passwordCheck(view:View){

    }
}