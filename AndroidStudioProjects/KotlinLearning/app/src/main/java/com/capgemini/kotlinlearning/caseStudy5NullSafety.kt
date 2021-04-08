package com.capgemini.kotlinlearning

import kotlin.text.contains as contains

fun main(){
    var data:String?
    data = "Hrish-i"
//    data=null

    //convert string to uppercase, checking if '-' is part of string
    //explicit null check
    if(data!=null){
        var upper1=data.toUpperCase()
        println("Uppercase data in explicit null check is: $upper1")
        if(data.contains('-')){
            println("It contains '-'")
        }
        else{
            println("Doesnt contain '-'")
        }
    }
    else{
        println("Data is null")
        println("Doesnt contain -")
    }

    //safe call operator ?.
    var upper2=data?.toUpperCase()
    println("With Safe-call operator uppercase data: $upper2")
    if(data?.contains('-')==true){
        println("It contains '-'")
    }
    else{
        println("Doesnt contain '-'")
    }

    //elvis operator ?:
    var upper3=data?.toUpperCase() ?:""
    var bool=data?.contains('-') ?:false
    println("With elvis operator uppercase data: $upper3")
    if(bool){
        println("It contains '-'")
    }
    else{
        println("Doesnt contain '-'")
    }
}