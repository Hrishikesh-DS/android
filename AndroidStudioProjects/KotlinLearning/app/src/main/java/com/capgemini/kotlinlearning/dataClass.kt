package com.capgemini.kotlinlearning

data class KotlinVehicle(val speed: Int, val make: String)

fun main(){
    val v1=KotlinVehicle(100, "Hyundai")
    val v2=KotlinVehicle(120, "Hyundai")

    println(v1)
    if(v1==v2){
        println("same")
    }
    else
        println("different")
}
//internal class Vehicle {
//    var speed = 0
//    var make: String? = null
//
//    override fun toString(): String {
//        return "Vehicle{" +
//                "speed=" + speed +
//                ", make='" + make + '\'' +
//                '}'
//    }
//}