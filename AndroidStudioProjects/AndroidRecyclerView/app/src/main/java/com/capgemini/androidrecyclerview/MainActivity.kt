package com.capgemini.androidrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listOfStudents = mutableListOf<Students>()
    var deletedStudents= mutableListOf<Students>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listOfStudents.add(Students("John Smith",1,90))
        listOfStudents.add(Students("Mary Rose",2,80))
        listOfStudents.add(Students("Jay King",3,65))
        listOfStudents.add(Students("May Robinson",4,40))
        listOfStudents.add(Students("Jen Jenkins",5,24))
        listOfStudents.add(Students("Mark Raffaello",6,65))
        listOfStudents.add(Students("Jenn Alison",7,70))
        listOfStudents.add(Students("Mach Band",8,5))

        rView.layoutManager = LinearLayoutManager(this)
//        ,LinearLayoutManager.HORIZONTAL,false)
//        rView.layoutManager = GridLayoutManager(this, 2)
//        rView.layoutManager = StaggeredGridLayoutManager(2,
//            StaggeredGridLayoutManager.VERTICAL)
        rView.adapter = MyAdapter(listOfStudents)
        {
            for(i in it){
                deletedStudents.add(i)
            }
//            Toast.makeText(this,"$deletedStudents",Toast.LENGTH_SHORT).show()
        }
        val touchHelper =ItemTouchHelper(MyTouchCallback(){
            listOfStudents.removeAt(it)
//            rView.adapter?.notifyDataSetChanged()
            rView.adapter?.notifyItemRemoved(it)
        })
        touchHelper.attachToRecyclerView(rView)
    }

    val DELETE=1
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,DELETE,0,"DELETE")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            DELETE->{
                listOfStudents.removeAll(deletedStudents)
            deletedStudents.clear()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

class MyTouchCallback(val action: (Int)->Unit): ItemTouchHelper.SimpleCallback(0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        action(viewHolder.adapterPosition)
        Toast.makeText(viewHolder.itemView.context,"Swiped:${viewHolder.adapterPosition}",Toast.LENGTH_SHORT).show()
    }

}
