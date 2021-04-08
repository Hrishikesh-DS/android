package com.capgemini.kotlinlearning

fun main(){
    // print all prime numbers between 1 to 20

    for(num in 1..20){
        var flag=1
        for(i in 2..num/2) {
            if(num>1)
                if ((num % i) == 0) {
                    flag = 0
                    break
                }
        }
        if(flag==1 &&  num>1){
            println(num)
        }

    }
}