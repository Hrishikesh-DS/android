package com.capgemini.kotlinlearning

//val data= listOf("John", "Andy", "Anand", "Merry", "Henry")
//// list of names starting with 'A'
//val accounts= listOf("111", "222", "123456789012")
//// 1. account numbers with 12 digits
//// 2. if account number != 12 digit, "NA"
//// ["NA", "NA", "123456789012"]
//val num = listOf( 100, 200 ,2123123, 234234, 345,123, 456)
//// max number using reduce

fun main(){
    val data= listOf("John", "Andy", "Anand", "Merry", "Henry")
    val name=data.filter {
        it.startsWith("A")
    }
    println("Name starting with A: $name")

    val accounts= listOf("111", "222", "123456789012")
    val acc=accounts.map {
        if(it.length!=12)
            "NA"
        else
            it
    }
    println("Accounts: $acc")
    val num = listOf( 100, 200 ,2123123, 234234, 345,123, 456)
    val max = num.reduce { mx, i ->
        if(mx<i)
            i
        else
            mx
    }
    println("Max: $max")
}