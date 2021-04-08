package com.capgemini.kotlinlearning

class Math(){
    infix fun square(a:Int):Int{//single argument only
        // can be said as object function variable
        return a*a
    }
}

data class Pointer(var x:Int,var y:Int){
    //operator overloading
   operator fun plus(another: Pointer):Pointer{
        val resultX=this.x+another.x
        val resultY=this.y+another.y
       return Pointer(resultX, resultY)
   }
}

fun main(){
    val m1=Math()
    m1.square(2)

    println("${m1 square  10}")

    val p1=Pointer(10,20)
    val p2=Pointer(10,20)

    val p3=p1+p2 //p1.plus(p2)
    println(p3)
}