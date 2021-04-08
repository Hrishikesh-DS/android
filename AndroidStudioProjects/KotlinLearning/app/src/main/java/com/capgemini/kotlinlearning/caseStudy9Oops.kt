package com.capgemini.kotlinlearning

/*
class Student
    -name
    -rollNo
    -marks
    -totalMarks out of 1000

    -displayInfo()
    -calculatePercent()
class University
    -name
    -listOfStudent: List<Student>

    -addStudent(name,rollNo,marks)-populate the list
    -getTopper() -return student with max marks
 */
var totalMarks: Double=100.00
data class Student(var name:String, var rollNo:Int, var marks: Double){
    companion object {
        var percent:Double=0.0
    }
    fun calculatePercentage(){
        percent=marks/totalMarks*100
        println("Percentage: $percent")
    }

    fun displayInfo(){
        println("Name: $name")
        println("Roll No: $rollNo")
        println("marks: $marks")
        println("Total marks: $totalMarks")
    }
}
var listOfStudent= mutableListOf<Student>()
class University(val name: String){
    fun addStudent(name:String, rollNo: Int, marks: Double){
        var stud= Student(name,rollNo,marks)
        listOfStudent.add(stud)
    }

    fun getTopper():Student?=listOfStudent.maxBy { it.marks }

    fun getListOfStudents():MutableList<Student>{
        return listOfStudent
    }

}

fun main(){
    val stud1=Student("Hrishi",100,80.00)
    stud1.calculatePercentage()
    stud1.displayInfo()


    val uni1=University("Bangalore")
    uni1.addStudent("John",120, 90.00)
    uni1.addStudent("Mark",140, 70.00)
    uni1.addStudent("Mary",40, 100.00)
    println("Topper: ${uni1.getTopper()?.name}")
    println(uni1.getListOfStudents())
}
