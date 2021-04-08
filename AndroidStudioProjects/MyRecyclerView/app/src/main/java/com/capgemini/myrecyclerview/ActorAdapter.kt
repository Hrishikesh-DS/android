package com.capgemini.myrecyclerview

import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.io.InputStream
import java.net.URL
import kotlin.concurrent.thread

class ActorAdapter(val data: List<Actors>)
    :RecyclerView.Adapter<ActorAdapter.ViewHolder>(){
        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val actorT: TextView
            val iView: ImageView
            init{
                actorT = view.findViewById(R.id.actorT)
                iView = view.findViewById(R.id.imageView)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.actor_list,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val act = data[position]

        holder.actorT.text=act.name
        lateinit var drawable: Drawable
        val t1 = thread {
            val myUrl = URL(act.img)
            val inputStream: InputStream = myUrl.getContent() as InputStream
            drawable = Drawable.createFromStream(inputStream, null)
        }
        t1.join()
        holder.iView.setImageDrawable(drawable)

        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_list_item)
        holder.itemView.startAnimation(anim)
    }


    override fun getItemCount()= data.size
}