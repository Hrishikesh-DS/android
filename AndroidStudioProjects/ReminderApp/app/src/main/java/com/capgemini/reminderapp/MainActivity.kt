package com.capgemini.reminderapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    val titleList= arrayListOf<String>()
    val desList = arrayListOf<String>()
    val dateList = arrayListOf<String>()
    val timeList = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(contB)
    }
    fun reminderClicked(view: View){
        var i:Intent = Intent(this, ReminderActivity::class.java)
        startActivity(i)
    }
    fun viewClicked(view: View){
        val i:Intent = Intent(this,RemainderView::class.java)
        startActivity(i)
    }
    val MENU_SMS = 1
    val MENU_CALL = 2
    val MENU_EMAIL = 3
    override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(0, MENU_SMS, 1, "Send a message to us")
        menu?.add(0, MENU_CALL, 2, "Call Us")
        menu?.add(0, MENU_EMAIL, 3, "Email Us")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_SMS->{
                val i = Intent(Intent.ACTION_SENDTO,
                        Uri.parse("smsto:12345678"))
                startActivity(i)
            }
            MENU_CALL->{
                val callIntent = Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:9845768750"))
                startActivity(callIntent)

            }
            MENU_EMAIL->{
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "*/*"
                    putExtra(Intent.EXTRA_EMAIL, "capgemini@gmail.com")
                    putExtra(Intent.EXTRA_SUBJECT, "Contacting us")
                }
                startActivity(intent)
            }
        }
        return true

    }

    val MENU_INTERVAL = 1
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val intervalItem = menu?.add(0,MENU_INTERVAL, 0, "SELECT INTERVAL")
        intervalItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_INTERVAL->{
                val int=Intent(this,Interval::class.java)
                startActivity(int)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}