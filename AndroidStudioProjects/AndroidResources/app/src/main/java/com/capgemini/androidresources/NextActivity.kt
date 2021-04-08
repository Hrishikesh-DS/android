package com.capgemini.androidresources

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class NextActivity : AppCompatActivity() {
    lateinit var iv: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        //arrow to go back home
        val aBar = supportActionBar
        aBar?.setDisplayHomeAsUpEnabled(true)

        iv = findViewById(R.id.imageView)
        iv.setBackgroundResource(R.drawable.anim_frame)
        iv.post{
            val anim =  iv.background as AnimationDrawable
            anim.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.mymenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_rotate->{
                val anim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
                iv.startAnimation(anim)
            }
            R.id.menu_move->{
                val anim = AnimationUtils.loadAnimation(this, R.anim.anim_move)
                iv.startAnimation(anim)
            }
            R.id.menu_fade->{
                val anim = AnimationUtils.loadAnimation(this, R.anim.anim_fade)
                iv.startAnimation(anim)
            }
            R.id.menu_scale->{
                val anim = AnimationUtils.loadAnimation(this, R.anim.anim_scale)
                iv.startAnimation(anim)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}