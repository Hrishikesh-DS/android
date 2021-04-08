package com.capgemini.androidresources

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var tv:TextView
    lateinit var parentL : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv=findViewById(R.id.textView)
        tv.setText(R.string.heading)

        parentL = findViewById(R.id.parent)

//        val appResources = resources
//        window.setTitle(appResources.getString(R.string.heading))

        val aBar = supportActionBar
        aBar?.title=resources.getString(R.string.heading)
        aBar?.subtitle=resources.getString(R.string.app_name)

        //wont work anymore to set a logo in the action bar due to latest android UI guidelines
//        aBar?.setLogo(R.mipmap.ic_launcher)
//        aBar?.setDisplayUseLogoEnabled(true)

//        aBar?.hide()


    }

    fun buttonClick(view: View) {
        val i = Intent(this,ToolBarActivity::class.java)
        startActivity(i)

//        val img = resources.getDrawable(R.drawable.download,null)
//        parentL.background=(img)
//        val locations = resources.getStringArray(R.array.choices)
//        Toast.makeText(this,"Loc: ${locations.contentToString()}",Toast.LENGTH_SHORT).show()
    }
}