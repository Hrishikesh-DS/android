package com.capgemini.kotlinlearning

import com.capgemini.kotlinlearning.demo.*
import java.math.BigInteger
import com.capgemini.kotlinlearning.xyz.calculate as cal2
import com.capgemini.kotlinlearning.xyz.calculate as cal
import com.capgemini.kotlinlearning.demo.calculate as squareMe


val area = 10 * PI
val myCounter = counter + 10

fun main(){
    println("Area: $area")
    println("Counter: $myCounter")
    cal() //demo package
    cal2() //xyz package
    val stud=Student()

    println("Square: ${squareMe(10)}")
    println("Factorial: ${factor(50)}")
}

//define factorial function using BigInteger of java
fun factor(num:Int):BigInteger{
    var fact:BigInteger= BigInteger("1")
    for(i in 1..num){
        fact=fact.multiply(BigInteger(i.toString()))
    }
    return fact
}