package com.capgemini.kotlinlearning
//part 2
fun main(){
    var res = sum(10,20)
    println("Addition: $res")
    println("______________________")
    displayInfo(age=22, name="Hrishi", city="Bangalore")
    displayInfo("Hrishikesh", city = "Blore", age = 20)
    println("______________________")
    calculateEMI(1000,20,24)
    calculateEMI(1000)
    println("_______________________")
    joinNames("Hrishi","Neha","Ram",delimiter = "-")
    joinNames( "Hrishi","Neha")

}

// 1. Required arguments
fun sum(a:Int, b:Int) = a + b

// 2. Named arguments
fun displayInfo(name: String, age: Int, city: String?){
    println("Name: $name")
    println("Age: $age")
    println("City: ${city?.toUpperCase()}")
}

// 3. default arguments
//alternative for function overloading(Takes less space ->1 definition sufficient)
//good practice- place default args after required
fun calculateEMI(amount: Int, rate:Int = 10, duration: Int=12){
    println("Amount: $amount")
    println("Rate: $rate")
    println("Duration: $duration")

    val emi=amount * rate/100/duration
    println("EMI: $emi")
}

// 4. variable length arguments
fun joinNames(vararg  names: String,delimiter: String="*"){
    println("No. of args: ${names.size}")
    var result=""
    for( s in names){
        result+=s
        result+=delimiter
    }
    println("Joined: $result")
}