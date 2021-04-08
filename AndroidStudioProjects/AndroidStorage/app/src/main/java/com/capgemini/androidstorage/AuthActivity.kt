package com.capgemini.androidstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*

val PREF_NAME = "credentials"// a file with this name will be created in internal memory

class AuthActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    fun submitClick(view: View) {
        val userid = useridE.text.toString()
        val password = passE.text.toString()

        if(userid.isNotEmpty() && password.isNotEmpty()){
            Toast.makeText(this,
                "UserId: $userid, Password: $password",Toast.LENGTH_LONG).show()

            saveToDB(userid,password)
        }
        else{
            Toast.makeText(this,"Data Incomplete",Toast.LENGTH_LONG).show()
        }
    }

    private fun saveToDB(userid: String,password: String){
        val wrapper = DBWrapper(this)
        val rowid = wrapper.saveCredentials(userid,password)
        if(rowid.toInt() != -1){
            Toast.makeText(this,"Data Saved!! rowid = $rowid",
                    Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Problem Occurred in insertion",Toast.LENGTH_LONG).show()
        }
    }

    private fun saveCredentials(userid: String, password: String) {
        val pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        //Now we write into the SharedPreference
        val editor = pref.edit()
        editor.putString("userid",userid)
        editor.putString("password",password)
        editor.commit()

        Toast.makeText(this,"Data Saved!",Toast.LENGTH_SHORT).show()

    }

    fun cancelClick(view: View) {
        useridE.setText("")
        passE.setText("")
    }
}