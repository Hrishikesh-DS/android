package com.capgemini.androidintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.app.Activity
import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun buttonClick(view: View){
        when(view.id){
            R.id.contactB->{
                //launch contact app and select contact
                val i = Intent(Intent.ACTION_PICK,
//                    Uri.parse("content://contacts/people"))
                    ContactsContract.Contacts.CONTENT_URI)
                //launching child activity
                startActivityForResult(i, 1)
            }
            R.id.callB->{
                //dialer/phone to make a call              //ACTION_DIAL -  to open the dial pad of call
                val callIntent = Intent(Intent.ACTION_CALL,//ACTION_CALL - to directly call(but needs permission)
                    Uri.parse("tel:9845768750"))
                startActivity(callIntent)
            }
            R.id.visitB->{
                val webIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://capgemini.com"))
                startActivity(webIntent)
            }
            R.id.locateB -> {
                val mapIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=navami lakshmi,bangalore"))
                startActivity(mapIntent)
            }
            R.id.nextB->{
                val nextIntent = Intent(this,NextActivity::class.java)
                val name = nameE.text.toString()
                if(name.isNotEmpty()){
                    nextIntent.putExtra("Name",name)
                }
                startActivity(nextIntent)
            }
            R.id.colorB->{
                val intent = Intent(this,BackgroundColorActivity::class.java)
                startActivityForResult(intent,2)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Login")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
                "Login" -> {
                    //launch AuthActivity
                    val i = Intent("com.capgemini.androidui.action.login")
                    if(i.resolveActivity(packageManager)!=null) {
                        startActivity(i)
                    }
                    else{
                        Toast.makeText(this,"AndroidUI app not installed yet.Pls install",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return super.onOptionsItemSelected(item)
    }
    //callback automatically executed after child activity is closed
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            1->{
                if(resultCode == RESULT_OK){
                    val selectedContact = data?.dataString
                    Snackbar.make(parentL, "Selected Contact: $selectedContact",
                        Snackbar.LENGTH_INDEFINITE).show()
                    Log.d("AndroidIntent","Selected Contact: $selectedContact")
                }
                else if(resultCode== RESULT_CANCELED){
                    Toast.makeText(this,"No contact selected",Toast.LENGTH_SHORT).show()
                }

            }
            2->{
                if(resultCode== Activity.RESULT_OK)
                { val selectColor= data?.getStringExtra("colour")
                    Toast.makeText(this,selectColor,Toast.LENGTH_LONG).show()
                    when(selectColor)
                    {"RED" -> parentL.setBackgroundColor(Color.RED)
                        "BLUE" -> parentL.setBackgroundColor(Color.BLUE)
                        "GREEN" -> parentL.setBackgroundColor(Color.GREEN)
                        "YELLOW" -> parentL.setBackgroundColor(Color.YELLOW)


                    }
                }
            }
        }
    }
}