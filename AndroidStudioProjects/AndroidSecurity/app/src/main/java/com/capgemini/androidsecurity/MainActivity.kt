package com.capgemini.androidsecurity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
    }

    fun checkPermission(){
        if(checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //ask the user to grant it.
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),1)
        }
        else{
            Toast.makeText(this, "Call Permission Granted..",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Got the permission",Toast.LENGTH_SHORT).show()
                }else{
                    finish()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun callClick(view: View) {
        val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:123456"))
        startActivity(callIntent)
    }

    fun LoginClick(view: View) {
        val authActivity = Intent("com.capgemini.androidui.action.login")
        startActivity(authActivity)
    }
}