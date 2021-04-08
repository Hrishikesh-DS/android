package com.capgemini.kotlinlearning

interface displayInterface{
    //declaration
    fun display(){
        println("from displayinterface")
    }
}

interface exampleInterface{
    fun demo()
    fun display(){
        println("from displayinterface")
    }
}
// MRO- Method Resolution Order- whatever order u list the interface,
//same order the interface methods will be called
//here display from displayInterface will be called first
abstract class Person(var name:String) :displayInterface,exampleInterface{
    override fun display() {
        println("Name: $name")
    }
}

class Teacher(name: String, val subject:String):Person(name){
    override fun demo() {
        println("$name teacher teaches: $subject")
    }
}

class Player:  exampleInterface,displayInterface{

    override fun demo() {
        println("Demo")
    }

    override fun display() {
        println("Player")
    }
}
fun main(){
    val p1=Teacher("John", "Android Development")
    p1.display()
    p1.demo()
}