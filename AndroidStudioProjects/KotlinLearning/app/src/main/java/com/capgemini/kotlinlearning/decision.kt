package com.capgemini.kotlinlearning

fun main(){
    val num=10
    //is even or odd
    //if as statement
    if(num%2==0){
        println("$num is even")
    }
    else{
        println("$num is odd")
    }
    val num2=100

    //if as an expression
    val max=if(num>num2) num else num2
    println("MAX: $max")

    val maximum=if(num>num2){
//        println("$num is max")
        num
    } else{
//        print("$num2 is max")
        num2
    }
    println("MAX: $maximum")

    //when
    val marks=80
    when(marks){
//        if String -> println("Invalid Marks")
        100 -> println("Excellent")
        in 75..100 -> {
            println("Distinction")
        }
        in 65..75 -> println("First class")
        in 55..65 -> println("Second class")
        in 35..55 -> println("Third class")
        else -> println("Failed")
    }
    //chain of if-else-if
    /*
    if condition 1
    else if condition 2
    else if condition 3
    else
     */

    when{
        marks !is Int -> println("Invalid")
        marks in 75..100 -> println("Distinction")
        marks in 65..75 -> println("First class")
        marks in 55..65 -> println("Second class")
        marks in 35..55 -> println("Third class")
        marks < 35 -> println("Failed")
    }
}