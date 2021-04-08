package com.capgemini.andriodui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
    }

    fun buttonClick(view: View){
        val id=view.id
        lateinit var i:Intent
        when(id){
            R.id.radioB->{
                Toast.makeText(this,"Demo button click",Toast.LENGTH_SHORT)
                i=Intent(this,RadioActivity::class.java)

            }
            R.id.checkB->{
                Toast.makeText(this,"Check button click",Toast.LENGTH_SHORT)
                i=Intent(this,CheckBoxDemo::class.java)
            }
            R.id.listB->{
                Toast.makeText(this,"List button click",Toast.LENGTH_SHORT)
                i=Intent(this,ListViewDemo::class.java)
            }
            R.id.spinnerB->{
                Toast.makeText(this,"Spinner button click",Toast.LENGTH_SHORT)
                i=Intent(this,SpinnerViewDemo::class.java)
            }
            R.id.fab->{
                Toast.makeText(this,"Fab button click",Toast.LENGTH_SHORT)
                i=Intent(this,WebViewDemo::class.java)
            }
            R.id.customB->{
                Toast.makeText(this,"Fab button click",Toast.LENGTH_SHORT)
                i=Intent(this,CustomAdaptorDemo::class.java)
            }
        }
        startActivity(i)
    }
}