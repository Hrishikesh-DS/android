package com.capgemini.kotlinlearning

fun main(){
    var data="Hi! Hello! How are you?"// i cant reassign if val. if i choose var, i can reassign data with a new data

    println(data[0])

    //split
    val splits=data.split('!')
    println("Splits: $splits")

    //change to lowercase
    val lower = data.toLowerCase()
    println("Original String: $data")
    println("Lower case: $lower")
    println("Capitalized: ${lower.capitalize()}")
    //replace '!' with '*'
    val replaced = data.replace('!','*')
    println("Replaced: $replaced")
    val address="""
        Rajajinagar,
        Bangalore,
        India
    """.trimIndent()
    //no of lines
    val lines=address.lines()
    println("No of lines: ${lines.size}")
    println("Lines: $lines")
    //comparison
    val str1="Hello"
    val str2="Hello"

    println("Comparison: ${str1.compareTo(str2)}")

    if(str1==str2){
        println("Both strings are same")
    }
}