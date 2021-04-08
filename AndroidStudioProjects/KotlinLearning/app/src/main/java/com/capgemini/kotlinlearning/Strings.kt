package com.capgemini.kotlinlearning

fun main(){
    var name="Hrishikesh DS" //escaped string
    var address="""
        Jayanagar 
        Bangalore
        India
    """.trimIndent()
    println("Name: $name")
    println("Address: $address")

    val pincode=123456
    //String interpolation/ templating
    val msg="$name resides at $address with pincode $pincode"
    println(msg)

    //string manipulation
    val data="Hi! Hello! How are you?"
    println("Length:${data.length}")
    println("First Char: ${data.first()}")
    println("Last Char: ${data.last()}")
    println("Char at index 2: ${data[2]}")
    println("Is string empty: ${data.isEmpty()}")
    println("Is it a question ?: ${data.endsWith('?')}")
    println("First 5 characters: ${data.substring(0,5)}")
    println("Is '!' available?: ${data.contains('!')}")
    println("'!' is present at index: ${data.indexOf('!')}")

}