package com.capgemini.kotlinlearning.demo

import kotlinx.coroutines.*

private fun add(a:Int, b:Int):Int{
    println("Addition Executed by: ${Thread.currentThread().name}")
    println("$a + $b: ${a+b}")
    return a+b
}

private fun subtract(a:Int, b:Int):Int{
    println("Subtraction Executed by: ${Thread.currentThread().name}")
    println("$a - $b: ${a-b}")
    return a-b
}

fun main(){
    // launch, async -  coroutine builders
    // runBlocking - to create a main/parent coroutine, which will wait for all child coroutine to finish
    runBlocking {
        // parent/main coroutine
//        val res = add(10,20)
//        println("Add: $res")
//        val sub = subtract(20,10)
//        println("Sub: $sub")
//        println("runBlocking: executed by ${Thread.currentThread().name}")
        println("-----------------------------------------------")
        tryDiffDispatchers()
        println("-----------------------------------------------")
        //launch child coroutine
        val j1 = launch (){
            delay(1500) //suspended function
            //child coroutine getting created
            val res = add(10,20)
            println("Add: $res")
        }
        val j2 = launch() {
            delay(1000)
            val sub = subtract(20,10)
            println("Sub: $sub")
        }
        println("Jobs are executing...")
        //start a new job after j1 and j2 finished
        j1.join() //suspended
        println("J1 job finished")
        j2.join() //suspended
        println("J2 job finished")
//        launch {
//            println("Started new job")
//            val num = getRandom()
//            println("Random number: $num")
//        }
        val num = async {
            getRandom()
        }
        val randomNo = num.await()// suspends until result is available
        println("Random No: $randomNo")
        //nested coroutines
        val job = launch{
            //create child coroutines
            val num1 = async{ getRandom()}
            //child coroutine
            launch {
                delay(2000)
                println("Second child coroutine")
            }
            println("NO: ${num1.await()}")
        }
        if(true){
            job.cancel("trail")
        }
    }


    println("--DONE--")
}

//co-routine
suspend fun getRandom(): Int{
    println("Calculating....")
    delay(3000)
    return (Math.random() * 100).toInt()
}


suspend fun tryDiffDispatchers(){
    //custom coroutineScope of our own
    coroutineScope {
        launch() {
            println("Main runBlocking: ${Thread.currentThread().name}")
            delay(2000)
            println("After delay Main runBlocking: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default: ${Thread.currentThread().name}")
            delay(2000)
            println("After delay Default: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined: ${Thread.currentThread().name}")
            delay(2000)
            println("After delay Unconfined : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("IO: ${Thread.currentThread().name}")
            delay(2000)
            println("After delay IO : ${Thread.currentThread().name}")

        }

    }
}