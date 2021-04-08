package com.capgemini.kotlinlearning

fun main(){
    var sentence = "Kotlin is a general purpose language with latest version 1.4"
    // count vowels, consonants, digits, spaces
    var vowelCount=0
    var consonantCount=0
    var digitCount=0
    var spaceCount=0
    var extra=0
    for(c in sentence){
        when(c){
            'a','e','i','o','u' -> vowelCount=vowelCount+1
            in 'a'..'z' -> consonantCount=consonantCount+1
            in '0'..'9' -> digitCount=digitCount+1
            ' '->spaceCount=spaceCount+1
            else -> extra++
        }

    }
    println("Vowel count: $vowelCount")
    println("Consonant count: $consonantCount")
    println("Digit count: $digitCount")
    println("Space count: $spaceCount")
}
