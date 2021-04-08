package com.capgemini.kotlinlearning

fun main(){
    println("Hello World!")
    println("Kotlin is multiplatform language")

    // var- mutable variable with read and write

    var username="Hrishi"
    var address:String
    address="Bangalore"
    username="Hrishikesh"
    println("Username: $username")
    println("Address: $address")

    //val- immutable- read-only

    val pi=3.142 //type inferred to Double
    println("PI: $pi")
    val p:Float= 12.123412412412412F
    println("P:$p")

    val pChar='-' //char

    var number=1000
    number+=10
    val remainder=(number%pi).toInt()
    println("${++number}")

    val delimiter='A'
    println("${delimiter.toInt()}")
    println("ACSII of '-': ${'-'.toInt()}")
    println("Char value of 104: ${104.toChar()}")
    val isEven=true //boolean
    println("Inverted value: ${!isEven}")

}