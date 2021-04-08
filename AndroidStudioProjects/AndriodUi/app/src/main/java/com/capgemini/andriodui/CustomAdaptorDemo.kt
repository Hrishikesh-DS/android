
package com.capgemini.andriodui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_custom_adaptor_demo.*
import kotlinx.android.synthetic.main.activity_main_constraint.*
import java.security.AccessControlContext
import java.text.FieldPosition
var listOfStudents = mutableListOf<Student>()
class CustomAdaptorDemo : AppCompatActivity(),AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_adaptor_demo)

        listOfStudents.add(Student("John", 1, 78.7))
        listOfStudents.add(Student("Merry", 2, 98.6))
        listOfStudents.add(Student("Robert", 3, 38.4))
        listOfStudents.add(Student("Mike", 4, 58.8))
        listOfStudents.add(Student("Mark", 5, 78.6))
        listOfStudents.add(Student("Jerry", 6, 81.2))
        listOfStudents.add(Student("John", 1, 78.7))
        listOfStudents.add(Student("Merry", 2, 98.6))
        listOfStudents.add(Student("Robert", 3, 38.4))
        listOfStudents.add(Student("Mike", 4, 58.8))
        listOfStudents.add(Student("Mark", 5, 78.6))
        listOfStudents.add(Student("Jerry", 6, 81.2))

        val adapter = StudentAdapter(
            this,
            R.layout.student_list_item, listOfStudents
        )

        studentL.adapter = adapter
        studentL.setOnItemClickListener(this)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val std= listOfStudents[position]

        Toast.makeText(this,"Name: ${std.name} " +
                "Roll No: ${std.id} Percentage: ${std.percentage}",Toast.LENGTH_SHORT).show()
    }


}

data class Student(val name: String, val id: Int, val percentage: Double)

class StudentAdapter(context: Context,val layoutRes: Int,val data: List<Student>)
    :ArrayAdapter<Student>(context, layoutRes, data){
        override  fun getItem(position: Int):Student?{
            return data[position]
        }

    //executed no of times as number of elements of array
    //create view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val student = getItem(position)
        //view creation - first check if the view is already created?
//        lateinit var view: View
//        if(convertView!=null)
//            view = LayoutInflater.from(context).inflate(layoutRes, null)
//        else
//            view = convertView!!

        var view = convertView?:LayoutInflater.from(context).inflate(layoutRes, null)

        //bind data appropriately
        val nameT = view.findViewById<TextView>(R.id.nameT)
        val rollT = view.findViewById<TextView>(R.id.rollT)
        val percT = view.findViewById<TextView>(R.id.percT)

        nameT.text = student?.name
        rollT.text = "${student?.id}"
        percT.text = "${student?.percentage}"

        return view
    }

}