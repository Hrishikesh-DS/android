package com.capgemini.kotlinlearning

fun main(){
    val numRange=1..10
    println("Num Range: $numRange")

    val chRange='a'.rangeTo('z')
    println("Char range: $chRange")

    val i=10
    println("Is 10 available? ${i in numRange}")

    val oddRange=numRange.step(2)
    println("Odd: $oddRange")

    val reversed=numRange.reversed()
    println("Reversed: $reversed")

    val tenToOne=10.downTo(1)
    println("Rev: $tenToOne")

    //even numbers between 0 to 10
    val evenRange=(0..10).step(2) //or 0.rangeTo(10).step(2)[.reversed() if u wanna reverse it
    println("Even: $evenRange")
    println("Even numbers: ${evenRange.toList()}")
}