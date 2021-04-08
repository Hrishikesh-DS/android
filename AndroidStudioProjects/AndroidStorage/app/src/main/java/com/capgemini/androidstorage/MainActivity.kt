package com.capgemini.androidstorage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    //Read the credential sharedPref
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
//        val userid = pref.getString("userid","")
//        textView.setText("Welcome $userid")
//        if(userid?.isEmpty()==true){
//            loginB.visibility = View.VISIBLE
//        }
//        else{
//            loginB.visibility = View.INVISIBLE
//        }
        val wrapper = DBWrapper(this)
        val cursor = wrapper.retrieveCredentials()
        if(cursor.count>0){
            cursor.moveToFirst()
            textView.setText("")
            do{
                val userid = cursor.getString(cursor.getColumnIndex(DBHelper.CLM_USERID))
                val pass = cursor.getString(cursor.getColumnIndex(DBHelper.CLM_PASSWORD))
                textView.append(" $userid, ")
            }while (cursor.moveToNext())
//            Toast.makeText(this,"UserID: $userid, Password: $pass",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Table is Empty",Toast.LENGTH_LONG).show()
        }
    }

    fun buttonClick(view: View) {
        when(view.id){
            R.id.loginB->{
                val i = Intent(this,AuthActivity::class.java)
                startActivity(i)
            }
            R.id.logoutB->{
//                val editor = pref.edit()
//                editor.clear()
//                editor.apply()
//                Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show()
//                textView.setText("Please Login Again")
//                loginB.visibility = View.VISIBLE
                val wrapper=DBWrapper(this)
                wrapper.deleteCredentials()
                textView.setText("Please Login Again")
            }
            R.id.writeB->{
//                writeToFile("Hello World!")
                writeToPublicFile("Android App Development")
            }
            R.id.readB->{
               val data:String = readFromPublicFile()
                Toast.makeText(this, data,Toast.LENGTH_SHORT).show()
            }
        }
    }
    val fileName = "data.txt"
    private fun readFromFile(): String {
        val fis = openFileInput(fileName)
//        val data = ByteArray(1024)
//        fis.read(data)
        val data = fis.readBytes()
        val dataS = String(data)
        return dataS
    }

    private fun writeToFile(input: String) {
        //write into private internal memory
        deleteFile(fileName)
        val fos = openFileOutput(fileName, MODE_APPEND)
        fos.write(input.toByteArray())
        fos.close()
        Toast.makeText(this, "Data Written",Toast.LENGTH_SHORT).show()
    }

    private fun writeToPublicFile(input: String){
        //write into private internal storage
        if(isSDCardAvailable()){

            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(fileName,null,cacheDir)
//            val file = File(path,fileName)
            Log.d("MainActivity","location: ${file.absolutePath}")
            val fos = FileOutputStream(file)
            fos.write(input.toByteArray())
            fos.close()
            Toast.makeText(this,"Data Written",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"SD card may not be available",Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFromPublicFile(): String{
        if(isSDCardAvailable()){
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File(path,fileName)
            Log.d("MainActivity","location: ${file.absolutePath}")
            val fis = FileInputStream(file)
            val data = fis.readBytes()
            val dataS = String(data)
            Toast.makeText(this,dataS,Toast.LENGTH_SHORT).show()
            return dataS
        }
        return ""
    }

    private fun isSDCardAvailable(): Boolean{
        return (Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED)
    }
}