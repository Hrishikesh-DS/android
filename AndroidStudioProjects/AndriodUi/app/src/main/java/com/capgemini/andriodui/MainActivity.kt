package com.capgemini.andriodui

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.provider.Settings.Secure.putString
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main_constraint.*
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity(), View.OnClickListener,
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{
    var firstTimeUser = true
//    lateinit var registerButton: Button
//    lateinit var msgText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_constraint)//view objects created and drawn

//        // Dynamic UI creation
//        val parent = LinearLayout(this)
//        val tv = TextView(this)
//        parent.addView(tv)
//        
//        setContentView(parent)

        //REGISTER FOR CONTEXT MENU FEATURE
        registerForContextMenu(demoB)

//        registerB.setOnClickListener{
//            Toast.makeText(it.context,"Through Lambda: ${it.id}", Toast.LENGTH_SHORT).show()
//        }
        signInB.setOnClickListener(this)//registered listener to the view
//        registerB.setOnClickListener(this)

        //if firstTime, both buttons else only sign-in
        //hide the REGISTER button

//        registerButton = findViewById(R.id.registerB)
//        var msgText: TextView = findViewById(R.id.tv2)
        if(!firstTimeUser){
            //hide the register button
                //Invisible = View still part of hierarchy
                    //GONE = view gets removed from view hierarchy
            registerB.visibility = View.GONE
            tv2.text = "Please Sign-In"
        }
    }
    fun registerClicked(view: View) {
//        when(view.id){
//            R.id.registerB ->{
//                Toast.makeText(this,"Register clicked",Toast.LENGTH_SHORT).show()
//            }
//        }
        //show the popup menu - as employee or customer

        val pMenu = PopupMenu(this,registerB)
        val menu =  pMenu.menu
        menu.add(0,1,0,"Driver")
        menu.add(0,2,1,"Rider")
        pMenu.show()

        pMenu.setOnMenuItemClickListener {
            when(it.itemId){
                1->{
                    val inten = Intent(this, RegisterActivity::class.java)
                    startActivity(inten)
                    true
                }
                else->{
                    val inten = Intent(this, RegisterActivity::class.java)
                    startActivity(inten)
                    true
                }
            }
        }
    }


    override fun onClick(v: View?){


        //reaction to click event

        val id=v!!.id
        when(id){
            R.id.registerB ->{
                val t = Toast.makeText(this,"Register Button clicked",Toast.LENGTH_SHORT)
                t.setGravity(Gravity.TOP,10,10)
                t.show()

            }

            R.id.signInB ->{
//                val t = Toast.makeText(this,"Sign-In Button clicked",Toast.LENGTH_SHORT)
//                t.setGravity(Gravity.TOP,10,10)
//                t.show()
                Snackbar.make(parentL,"Signing in... ",Snackbar.LENGTH_LONG).show()

                //launch AuthActivity
                val i = Intent(this, AuthActivity::class.java)
                //Intent i = new Intent(this, AuthActivity.class)
                startActivity(i)//ActivityManager
            }
            R.id.demoB ->{
                val t = Toast.makeText(this,"Demo Button clicked",Toast.LENGTH_SHORT)
                t.setGravity(Gravity.TOP,10,10)
                t.show()

                //launch AuthActivity
                val i = Intent(this, DemoActivity::class.java)
                //Intent i = new Intent(this, AuthActivity.class)
                startActivity(i)//ActivityManager
            }
        }


    }


    val MENU_LOGIN = 1 //create unique positive ID
    val MENU_REGISTER = 2
    val MENU_EXIT = 3
    val MENU_TIME = 4
    val MENU_DATE = 5
    val MENU_PROGRESS = 6
    val MENU_STOP = 7
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val loginItem = menu?.add(0, MENU_LOGIN, 0, "LOGIN")
//        loginItem?.setIcon(android.R.drawable.ic_dialog_info)
//        loginItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        menu?.add(0,MENU_REGISTER,1,"REGISTER")
        menu?.add(0,MENU_EXIT,2,"EXIT")
        menu?.add(0,MENU_TIME,3,"Pick Time")
        menu?.add(0,MENU_DATE,4,"Pick Date")
        menu?.add(0,MENU_PROGRESS,5,"Start Task")
        menu?.add(0,MENU_STOP,6,"Stop Task")

        return super.onCreateOptionsMenu(menu)
    }
    lateinit var pDlg: ProgressDialog
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            MENU_PROGRESS->{
//                pDlg = ProgressDialog.show(this, "Downloading...","Please Wait",
//                        false,true)
                progressBar.visibility = View.VISIBLE
                progressBar.progress = 50

            }
            MENU_STOP->{
//                pDlg.cancel()
                progressBar.visibility = View.INVISIBLE
            }
            MENU_LOGIN->{
                val i = Intent(this,AuthActivity::class.java)
                startActivity(i)
                return true
            }
            MENU_REGISTER->{
//                val i = Intent(this,RegisterActivity::class.java)
//                startActivity(i)
                val dlog = MyDialog()
                val bundl = Bundle()
                bundl.putString("msg","Choose options")
                bundl.putString("btn1", "Driver")
                bundl.putString("btn2","Rider")
                dlog.arguments = bundl
                dlog.show(supportFragmentManager,"abc")
                return true
            }
            MENU_EXIT->{
//                finish()
//                return true
                val dlg = MyDialog()
                val bundle = Bundle()
                bundle.putString("msg","Do u want to exit???")
                bundle.putString("btn1", "Yes")
                bundle.putString("btn2","No")
                bundle.putInt("type",1)
                dlg.arguments = bundle

                dlg.show(supportFragmentManager, "xyz")
                return true
            }
            MENU_TIME ->{
                val dlg = MyDialog()
                val bundle  = Bundle()
                bundle.putInt("type", 2)
                dlg.arguments = bundle
                dlg.show(supportFragmentManager, "aaa")
            }
            MENU_DATE ->{
                val dlg = MyDialog()
                val bundle = Bundle()
                bundle.putInt("type", 3)
                dlg.arguments = bundle
                dlg.show(supportFragmentManager,"bbb")
            }
        }
        return super.onOptionsItemSelected(item)
    }
    val MENU_RB = 1
    val MENU_CB = 2
    val MENU_LV = 3
    val MENU_SP = 4
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v?.id == R.id.demoB)//if there are multiple context
        {
            menu?.add(0, MENU_RB, 0, "Radio Button Demo")
            menu?.add(0, MENU_CB, 0, "Check Box Demo")
            menu?.add(0, MENU_LV, 0, "List View Demo")
            menu?.add(0, MENU_SP, 0, "Spinner Demo")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        lateinit var intent: Intent

        when(item.itemId){
            MENU_LV->{
                intent = Intent(this,ListViewDemo::class.java)
            }
            MENU_CB->{
                intent = Intent(this,CheckBoxDemo::class.java)
            }
            MENU_RB->{
                intent = Intent(this,RadioActivity::class.java)
            }
            MENU_SP->{
                intent = Intent(this,SpinnerViewDemo::class.java)
            }
        }
        startActivity(intent)
        return true

    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(this,"Time: $hourOfDay hrs, $minute mins",Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(this,"Year: $year, Month: $month, Day: $dayOfMonth",Toast.LENGTH_SHORT).show()
    }
}