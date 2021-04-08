package com.capgemini.reminderapp

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBWrapper(val context: Context) {
    val helper = DBHelper(context)
    val db: SQLiteDatabase = helper.readableDatabase

    fun saveCredentials(title: String, description: String, date: String, time: String): Long {
        val cValue = ContentValues()
        cValue.put(DBHelper.CLM_TITLE,title)
        cValue.put(DBHelper.CLM_DESC,description)
        cValue.put(DBHelper.CLM_DATE,date)
        cValue.put(DBHelper.CLM_TIME,time)

        return db.insert(DBHelper.TABLE_NAME, null, cValue)
    }
    fun retrieveCredentials(): Cursor{
        val col = arrayOf(DBHelper.CLM_TITLE, DBHelper.CLM_DESC, DBHelper.CLM_DATE, DBHelper.CLM_TIME)
        return  db.query(DBHelper.TABLE_NAME, col,
        null, null, null,
                null, null)
    }

    fun deleteCredentials(title: String){
        db.delete(DBHelper.TABLE_NAME,"${DBHelper.CLM_TITLE} = ?", arrayOf(title))
    }

    fun deleteAll(){
        db.delete(DBHelper.TABLE_NAME,null,null)
    }
}