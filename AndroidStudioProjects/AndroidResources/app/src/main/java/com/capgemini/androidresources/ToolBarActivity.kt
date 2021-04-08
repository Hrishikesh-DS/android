package com.capgemini.androidresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar

class ToolBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_bar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tb = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(tb)


        val imgbutton = findViewById<ImageButton>(R.id.imageButton)
        imgbutton.setOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}