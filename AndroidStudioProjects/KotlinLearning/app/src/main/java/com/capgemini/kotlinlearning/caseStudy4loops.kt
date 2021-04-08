package com.capgemini.kotlinlearning

fun main(){
    //count no of digits in number(int) and find reverse
    var countdig=0
    var num=1050
    var rev=0
    while(num>0){
        rev=num%10+rev*10
        countdig+=1
        num=num/10
    }

    //find the factorial -> n!
    var fact=1
    var n=5
    for(i in 1..n){
        fact=fact*i
    }

    println("reverse: $rev")
    println("Count digits: $countdig")
    println("Factorial: $fact")
}