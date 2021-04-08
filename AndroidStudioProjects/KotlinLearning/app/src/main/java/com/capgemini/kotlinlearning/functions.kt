package com.capgemini.kotlinlearning
//part 1
fun main(){
    //the function call doesn't allow the next line to execute until the above function call is executed
    val c =add(10,20) //this function call is blocking(by default)
    customPrint("main","Addition:$c")

    val s= add("Good ","Morning")
    customPrint("main","$s")

    val sum= add(1.25,3.25)
    println("Sum: $sum")

    displayName("hrishi")
    displayName(null)
}

//declaration

fun add(a:Int, b:Int): Int{
    val res=a+b
    println("$a + $b = $res")
    customPrint("add","$a + $b = $res")
    return res
}

fun add(a:String, b:String): String{
    return a+b
}

//single expression functions
fun add(a:Double, b:Double)=a+b
//fun add(a:Double, b:Double):Double{
//    return a+b
//}

fun customPrint(methodName: String, msg: String){
    println("[$methodName]: $msg")
}

fun displayName(name:String?){
//    println("Name: ${name?.toUpperCase()}")
    println("Name: ${name?.toUpperCase() ?: ' '}")
}