package com.capgemini.kotlinlearning

fun main(){
    //for - iterating over a collection
    val data= arrayOf(1,2,3,4,5,6,7,8,9)
    for(i in data){
        println("Element: $i")
    }
    var counter=0
    while(counter<5){
        println("Counter: $counter")
        counter++
    }
    do{
        println("Counter: $counter")
        counter--
    }while (counter>0)
    //square of all even nos from 1 to 10
    for(i in (0..20).step(2)){
        println("Square of $i: ${i*i}")
    }
    //generate tables of nums from 23 to 27
    //nested loops
    for(num in 23..27){
        println("Table of $num:")
        for(i in 1..10){
            println(num*i)
        }
        println("-------")
    }
    //loop control statements -continue and break
    val msg="Hi! Hello! how are you?"
    for(ch in msg){
        if(ch == '!'){
            println("Continuing loop")
//            break
            continue
        }
        println("Char: $ch")
    }
    println("DONE")
}