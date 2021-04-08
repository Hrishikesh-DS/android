package com.capgemini.androidcontentprovider

import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val brightness = Settings.System.getInt(contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,0)
        seekBar.progress = brightness

        seekBar.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        Settings.System.putInt(contentResolver,
//            Settings.System.SCREEN_BRIGHTNESS,progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


    lateinit var mPlayer:MediaPlayer
    fun playClick(view: View) {
        mPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        mPlayer.start()
    }

    fun stopPlaying(view: View) {
        if(mPlayer.isPlaying)
            mPlayer.stop()
    }
}