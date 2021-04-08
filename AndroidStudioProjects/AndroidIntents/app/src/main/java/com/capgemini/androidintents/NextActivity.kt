package com.capgemini.androidintents

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val intent =intent //getting intent which caused launch of this activity
        var name = intent.getStringExtra("Name")


        welcomeT.append(name)
    }

}