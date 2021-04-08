package com.capgemini.androidintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_background_color.*

class BackgroundColorActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_color)
    }

    fun clickedOK(view: View) {
        val colo = spinner.selectedItem as String
        //  Toast.makeText(this,colo,Toast.LENGTH_SHORT).show()
        val intent = Intent()
        intent.putExtra("colour", colo)
        setResult(-1, intent)
        finish()
    }
}