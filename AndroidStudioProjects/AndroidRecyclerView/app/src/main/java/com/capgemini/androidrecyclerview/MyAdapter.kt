package com.capgemini.androidrecyclerview

import android.graphics.Color
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
import androidx.recyclerview.widget.RecyclerView

/*
function type
(input args type)-> return type
 */
class MyAdapter(val data: List<Students>
                ,val listener: (MutableList<Students>)->Unit )
    : RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    val studentCheck= mutableListOf<Students>()
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameT: TextView
        val iView: ImageView
        val cardV: CardView
        val marksT: TextView
        val cb:CheckBox
        init{
            nameT = view.findViewById(R.id.nameT)
            iView = view.findViewById(R.id.imageView)
            cardV = view.findViewById(R.id.parentC)
            marksT = view.findViewById(R.id.marksT)
            cb = view.findViewById(R.id.itemCb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate layout for item
        //Called by recyclerView(layout manager) automatically
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.student_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val std = data[position]

        holder.nameT.text = std.name
        holder.marksT.text = std.marks.toString()
        holder.iView.setImageResource(R.drawable.person)
//        holder.itemView.setOnClickListener{
//            listener(std)
//        }
        holder.cb.setOnClickListener{
            if(it is CheckBox && holder.cb.isChecked){
                studentCheck.add(Students(std.name,std.id,std.marks))
//                Toast.makeText(,"$studentCheck",Toast.LENGTH_SHORT).show()
                Log.d("Student List", "$studentCheck")
            }
            else if(!holder.cb.isChecked){
                studentCheck.remove(std)
            }
            listener(studentCheck)
            holder.cb.isChecked=false
            //implement using ischecked and pass the boolean flag
        }

        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_list_item)
        holder.itemView.startAnimation(anim)

//        when(std.marks){
//            in 75..100 -> holder.itemView.setBackgroundColor(Color.GREEN)
//            in 50..75 -> holder.itemView.setBackgroundColor(Color.YELLOW)
//            in 25..50 -> holder.itemView.setBackgroundColor(Color.CYAN)
//            else -> holder.itemView.setBackgroundColor(Color.RED)
//        }
        when(std.marks){
            in 75..100 -> holder.cardV.setCardBackgroundColor(Color.GREEN)
            in 50..75 -> holder.cardV.setCardBackgroundColor(Color.YELLOW)
            in 25..50 -> holder.cardV.setCardBackgroundColor(Color.CYAN)
            else -> holder.cardV.setCardBackgroundColor(Color.RED)
        }
    }

    override fun getItemCount() = data.size

}