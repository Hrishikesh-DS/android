package com.capgemini.kotlinlearning

//definition
//class Employee{
//    // data members
//    var firstName = ""
//    var lastName = ""
//    val salary = 1000 // read-only property
//
//    // methods/member functions
//    fun displayInfo(){
//        println("Name: $firstName $lastName")
//        println("Salary: $salary")
//    }
//}
              //primary constructor
class Employee(var firstName: String, var lastName: String,var salary: Int = 1000){

    var bonus=100
    private var city=""

    //Secondary constructor
    constructor(fName: String, lName: String, salary:Int, bonus:Int):
            this(fName,lName,salary){
        println("Secondary constructor done")
        this.bonus=bonus
    }
    constructor(frName:String, lsName:String, salary:Int, bonus:Int, city:String):
            this(frName,lsName,salary, bonus){
                println("Another secondary constructor done")
                this.city=city
            }

    companion object Test{
        var empCount=0
    }

    init {
        println("_____Init executed______")
        ++empCount
    }

    fun displayInfo(){
        println("Name: $firstName $lastName")
        println("Salary: $salary")
        println("Bonus: $bonus")
    }
}

//extension function to add method applyBonus
//private property isnt visible to extension functions
fun Employee.applyBonus(){
    salary+=bonus
}
//adding static method via extension
// fun Employee.Companion.displayCount() if companion object has no name
fun Employee.Test.displayCount(){ //if companion has name Test
    println("Total Employees: $empCount")
}

fun main(){
    val emp1 = Employee("Hrishikesh","DS", 2000)
    emp1.displayInfo()
//    emp1.firstName = "Hrishikesh"
//    emp1.lastName = "DS"

    val emp2 = Employee("Ram", "Krishna", 5000)
    emp2.displayInfo()

    val emp3 = Employee("Mark","Tyson",2000, 200)
    emp3.displayInfo()

    val emp4 = Employee("Mark","Tyson",2000, 200, "Bangalore")
    emp4.applyBonus()
    emp4.displayInfo()

    Employee.displayCount()
}

/*
Inheritance:
Single
    A -> B
Hierarchical
    A -> B
    A -> C
Multilevel
    A -> B -> C
Multiple(Not available in java or kotlin)
    A,B -> C

 */