package com.capgemini.andriodui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view_demo.*

class ListViewDemo : AppCompatActivity(), AdapterView.OnItemClickListener {

    val langList = mutableListOf<String>("Java","Python","Kotlin","Swift","C#","C++","JavaScript","Dart","Flutter","Ruby")

    lateinit var adapter :ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_demo)
        //Parts of List
        //1. Adapter - supplying data to the view
        //2. Layout of each item - How to display the data(in what order)
        /*
        listView, spinner, recyclerView -> AdapterViews

        There are 2 types of adapters
        1. ArrayAdapter(Using rn)
        2. CursorAdapter
        3.CustomAdapter
         */
        adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, langList)

        listvw.adapter = adapter
        listvw.setOnItemClickListener(this)

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, index: Int, id: Long) {
        val selectedItem = langList[index]
        Toast.makeText(this,"Selected Item: $selectedItem",Toast.LENGTH_SHORT).show()

        langList.removeAt(index) //data updated(now inform listView to redraw)
        adapter.notifyDataSetChanged() //make listview redraw after data updation

    }
}