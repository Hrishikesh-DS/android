package com.capgemini.kotlinlearning.demo

import kotlin.concurrent.thread

private fun add(a:Int, b:Int):Int{
    println("Executed by: ${Thread.currentThread().name}")
    println("$a + $b: ${a+b}")
    return a+b
}

private fun subtract(a:Int, b:Int):Int{
    println("Executed by: ${Thread.currentThread().name}")
    println("$a - $b: ${a-b}")
    return a-b
}

fun main(){
    println("Hello from ${Thread.currentThread().name}")
    val t1 = thread(name = "Addition",start = false) {
        //long running task to be performed in this thread
        //can be downloading from server
        Thread.sleep(1000)
        val res = add(20,10)
        println("Addition: $res")
    }
    val t2 = thread(name = "Subtraction") {
        //can be uploading to server
        Thread.sleep(1000)
        val res = subtract(20,10)
        println("Subtraction: $res")
    }
    println("Start T1")
    t1.start()

    //wait for secondary methods
    //is t1 done
    t1.join()//join is for waiting for thread to finish
    //wait for downloading to finish first
    println("T1 Finished")
    t2.join()//now wait for uploading to finish
    println("T2 Finished")

//    for(i in 1..1_00_00000){
//        thread {
//            Thread.sleep(1000)
//            print("*")
//        }
//    }
}