package com.capgemini.kotlinlearning

/*
maintain Aadhar database

-Aadhar number
-Mobile number
-City
-Name

Map - key(AadharNo) - value[Mobile, Name, City] //list -  option1
To get name- map[aadharNo][1]

Map - key(AadharNo) - value{"mobile":111, "name":"Hrishi", "city":"bangalore"}
map[AadharNo]["Name"]
 */

/*
fun addRecord(aadhar,name,city,mobile)
fun getName(aadharNo)
fun printDetails(city)
 */
val aadhar= mutableMapOf<Long,Any>()
fun main(){
    addRecord(1111111111,"Hrishi","Bangalore",111)
    addRecord(2222222222,"Ram","Bangalore",222)
    addRecord(3333333333,"Mary","Mumbai",333)
//    getName(1111111111)
}

fun addRecord(aadharNo:Long, name:String, city:String, mobile:Long){
    val details= mutableMapOf("name" to name,"city" to city,"mobile" to mobile)
    aadhar[aadharNo]=details
}

//fun getName(aadharNo:Long){
//    val name= aadhar.map {
//        if(it.key==aadharNo){
//            it(aadharNo)("name")
//        }
//    }
//}
//
//fun printDetails(city: String){
//    val details= aadhar.filter {
//
//
//    }
//}