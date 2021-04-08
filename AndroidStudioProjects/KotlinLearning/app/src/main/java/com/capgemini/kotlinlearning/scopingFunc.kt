package com.capgemini.kotlinlearning
//part 4
import java.util.*

fun main(){

    var arr= arrayOf(1,2,3,4,5)
    //returning the last expression - let,run and with
    //1)Let(object referred as it)
    var arrSize = arr.let {
        println("Size(Let): ${it.size}")
        it.size
    }
    var updatedFirst = arr.first().let {
        it+1
    }
    println("Updated(Let): $updatedFirst")

    //2)Run(object referred as this so no need to mention it
    arrSize = arr.run {
        println("Size(Run) :$size")
        println("First(Run): ${first()}")
        size
    }

    //3)With - non-extension function (in run its done with dot, here its independent
    val last = with(arr){
        println("Size(With): $size")
        println("First(With): ${first()}")
        this[lastIndex]=0
        this[lastIndex]
    }
    println("Last element(With): ${arr.last()}")

    //4)Apply(returns the object itself on which the apply is called
    val intArr = IntArray(4).apply {
        for(i in indices){
            this[i] = 10+i
        }
    }
    println("Array: ${intArr.contentToString()}")
//    println("${Arrays.toString(intArr)}")

    //5)Also- returns the same object like apply(only reference is different)
    val anotherArray = IntArray(5).also {
        println("${Arrays.toString(it)}")
    }
}