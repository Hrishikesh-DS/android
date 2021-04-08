package com.capgemini.andriodui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_spinner_view_demo.*

class SpinnerViewDemo : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val langList = mutableListOf<String>("Java","Python","Kotlin","Swift","C#","C++","JavaScript","Dart","Flutter","Ruby")

    lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_view_demo)

        adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,langList)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this,"Selection: ${langList[position]}",Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this,"Pleas select something",Toast.LENGTH_SHORT).show()
    }
}