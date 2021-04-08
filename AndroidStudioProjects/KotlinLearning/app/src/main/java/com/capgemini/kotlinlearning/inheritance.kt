package com.capgemini.kotlinlearning

open class EmployeeBase(var name:String, var salary: Int){
    open var bonus=100
    companion object{
        var empCount = 0
    }
    init{
        println("___________________________")
        println("Init of Employee")
        ++empCount
    }
    open fun displayInfo(){
        println("Name: $name")
        println("Salary: $salary")
    }
    open fun applyBonus(){ salary+=bonus }
}

class Developer(name: String, salary: Int, val skills: String):
    EmployeeBase(name, salary){
        companion object{
            var devCount = 0
        }
        init {
            println("Init of Dev")
            ++devCount
        }
    override fun displayInfo(){
        println("___Developer Display Info____")
        super.displayInfo()
        println("Skills: $skills")


    }
    override var bonus = 200
    }
class Tester(name:String, salary: Int, var testingTool:String):
    EmployeeBase(name,salary){
    companion object{
        var testerCount=0
    }
    init{
        println("Init tester")
        ++testerCount
    }
    override var bonus=150
    override fun displayInfo() {
        super.displayInfo()
        println("Testing tool: $testingTool")

    }

}

fun main(){
    val emp1=EmployeeBase("John Smith", 10000)
    emp1.applyBonus()
    emp1.displayInfo()

    val emp2 = Developer("Marry Jane", 20000,"Kotlin")
    emp2.applyBonus()
    emp2.displayInfo()

    val emp3=Tester("Mark Smith", 25000,"Python")
    emp3.applyBonus()
    emp3.displayInfo()
    println("___________________________________")
    println("Total Employee: ${EmployeeBase.empCount}")
    println("Total Developers: ${Developer.devCount}")
    println("Total Testers: ${Tester.testerCount}")
}

/*
class Tester: EmployeeBase
bonus = 150
New property - TestingTool
displayInfo
count of testers
 */


