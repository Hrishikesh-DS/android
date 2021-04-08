package com.capgemini.androidcontentprovider

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.ContactsContract.CommonDataKinds.Phone as Phone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        when(view.id){
            R.id.contactB->{
                val i = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(i,1)
            }
            R.id.logB->{
                val i = Intent(this, Spinner::class.java)
                startActivity(i)
            }
            R.id.settingB->{
                val i = Intent(this, SettingsActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                if(resultCode== RESULT_OK){
                    val selectionU = data?.data
                    val rowid = selectionU?.lastPathSegment

                    //query contact db to get name and number
                    val cr = contentResolver
                    val args = arrayOf(rowid)
                    //select * from phone where rowid = 10
                    val cursor = cr.query(Phone.CONTENT_URI, null,
                            "${Phone.CONTACT_ID} = ?",
                            args,null)
                    val emails = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID
                    + " = ?", null, null)
                    var msg = ""
//                    var eml = ""
                    if(cursor?.count!!>0){
                        //at-least one number
                        cursor.moveToFirst()
                        val idxName = cursor.getColumnIndex(Phone.DISPLAY_NAME)
                        val idxNumber = cursor.getColumnIndex(Phone.NUMBER)
                        var contactNum=""
                        do{
                            val name = cursor.getString(idxName)
                            contactNum = cursor.getString(idxNumber)
                            msg += "$name:$contactNum \n"
                        }while (cursor.moveToNext())
//                        //Make a call
//                        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $contactNum"))
//                        startActivity(callIntent)
                    }
                    else{
                        msg = "No Phone number found"
                    }
                    detailsT.text = msg
                }
                else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this,"No Contact Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}