package com.capgemini.kotlinlearning

fun main(){
    val num=5
    println("Factorial of $num is: ${factorial(num)}")

    val str="Hello World"
    println("String without space: ${str.removeSpace()}")

    println("Addition: ${printOperation(0)(20,4)}")
    println("Subtraction: ${printOperation(1)(20,4)}")
    println("Multiplication: ${printOperation(2)(20,4)}")
}
//function- higher order function
/*
input - flag(int)
return - function for mathematical operation- add, sub, mul
 */
fun addi(a:Int,b:Int)=a+b
fun sub(a:Int,b:Int)=a-b
fun mult(a:Int,b:Int)=a*b
fun non(a:Int,b:Int)=a-b

fun ops(flag:Int):(Int,Int)->Int {
    return when(flag){
        0-> ::addi
        1-> ::sub
        2-> ::mult
        else -> ::non
    }
}

fun printOperation(flag:Int):(Int,Int)->Int {
    return when(flag){
        0-> {a:Int,b:Int -> a+b}
        1-> {a:Int,b:Int -> a-b}
        2-> {a:Int,b:Int -> a*b}
        else -> {a:Int,b:Int -> 0}
    }
}

fun factorial(n:Int):Int {
    return if(n==0 || n==1)
        1
    else
        n * factorial(n - 1)
}

// extension function to String which will remove all spaces in the string

fun String.removeSpace():String{
    return this.replace(" ","")
}




