package com.capgemini.androidstorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DBHelper(context: Context):
        SQLiteOpenHelper(context,"credentials.db",null,1) {

    companion object{
        val TABLE_NAME = "AUTH"
        val CLM_USERID = "userid"
        val CLM_PASSWORD = "password"
        val TABLE_QUERY = "create table $TABLE_NAME ($CLM_USERID text, $CLM_PASSWORD text)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Executed first time when table is created
        try{
            db?.execSQL(TABLE_QUERY)
        }
        catch (e:Exception){
            Log.e("DBHelper","ERROR Creating Table: ${e.message}")
        }
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Create table
        //Existing table- Changes to schema(edit columns)
        // Drop old table and create new one with changes
    }

}