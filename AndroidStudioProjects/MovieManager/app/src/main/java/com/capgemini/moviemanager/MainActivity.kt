package com.capgemini.moviemanager

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        when(view.id){
            R.id.insertB->{
                //Insert into both movie and genre table
                val name="MovieName"
                val release_date = "27/02/2000"
                val genre = "Horror"
                saveToDB(name,release_date,genre)
            }
            R.id.queryB->{
                //query movie table, get all rows
//                val cr = contentResolver
//                val cursor = cr.query(MovieContract.MovieEntry.CONTENT_URI,
//                        null,null,null,null)
//                if(cursor?.count!!>0){
//                    var msg=""
//                    val idx=cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_NAME)
//                    var gen = cursor.getString(idx)
//                    msg="$gen, msg"
//                    Toast.makeText(this,"Movie: $msg",Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    private fun saveToDB(name: String, releaseDate: String, genre: String) {
        val me = MovieContract.MovieEntry
        val movieUri = me.CONTENT_URI
        val cr = contentResolver
        val cValue = ContentValues()
        cValue.put(me.COLUMN_NAME,name)
        cValue.put(me.COLUMN_RELEASE_DATE,releaseDate)
        cValue.put(me.COLUMN_GENRE,genre)
        cr.insert(movieUri,cValue)
    }
}