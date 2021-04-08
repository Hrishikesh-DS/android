package com.capgemini.kotlinlearning

import java.util.*

fun main(){
    var data= arrayOf(1,2,3,4,5,6)

    println("No of elements: ${data.size}")
    println("First element: ${data.first()}")
    println("Last element: ${data.last()}")
    println("Element at index 2: ${data[2]}")

    data[0]=10
    println("Aray: ${Arrays.toString(data)}")

    var numberArray=Array(4){0}
    numberArray[0]=12
    println("Arrays: ${numberArray.contentToString()}")//Array<Int>=>Integer[]

    var arr=IntArray(4)

    println("Int arr: ${arr.contentToString()}")// IntArray -> int[]
    numberArray=arr.toTypedArray()
    arr=numberArray.toIntArray()

}