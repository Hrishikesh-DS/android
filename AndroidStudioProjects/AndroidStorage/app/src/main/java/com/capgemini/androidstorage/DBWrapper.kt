package com.capgemini.androidstorage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBWrapper(val context: Context) {
    val helper = DBHelper(context)
    val db: SQLiteDatabase = helper.readableDatabase

    fun saveCredentials(userid: String, pass: String): Long{
        //Insert operation
        val cValue = ContentValues()
        cValue.put(DBHelper.CLM_USERID, userid)
        cValue.put(DBHelper.CLM_PASSWORD, pass)

        return db.insert(DBHelper.TABLE_NAME,null, cValue)
    }
    fun retrieveCredentials(): Cursor{
        //Query operation
        val clms = arrayOf(DBHelper.CLM_USERID, DBHelper.CLM_PASSWORD)
        return db.query(DBHelper.TABLE_NAME,clms,
                null,null,
                null,null,null)
    }

    fun deleteCredentials(){
        db.delete(DBHelper.TABLE_NAME,null,null)
    }
}