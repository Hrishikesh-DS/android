package com.capgemini.reminderapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DBHelper(context: Context):
        SQLiteOpenHelper(context,"reminder.db", null, 1){
    companion object{
        val TABLE_NAME = "REMINDER"
        val CLM_TITLE = "title"
        val CLM_DESC = "description"
        val CLM_DATE = "date"
        val CLM_TIME = "time"
        val TABLE_QUERY = "create table $TABLE_NAME ($CLM_TITLE text, $CLM_DESC text, $CLM_DATE text, $CLM_TIME text)"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        try{
            db?.execSQL(TABLE_QUERY)
        }
        catch (e:Exception){
            Log.e("DBHelper", "Error creating table: ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}