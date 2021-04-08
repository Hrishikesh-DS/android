package com.capgemini.kotlinlearning

fun main(){
    var name:String //non nullable reference
    name="Hrishikesh"
//    name=null

    //nullable reference
    var uname: String?

//    uname="Hrishikesh"
    uname=null
//    var len=uname.length //cant work coz uname maybe null
    var len:Int

    //1. Explicit checking null
    if(uname!=null){
        len=uname.length //smart casting done by compiler(it allows as if uname is non-nullable)
    }
    else{
        println("String is null")
        len=0
    }
    println("Length after explicit check: $len")

    //2. Using safe-call operator -?
    //returns null if nullable reference is null
    var length=uname?.length //if uname is null itll return a null and type mismatch will happen with len but itll work with length
    println("With Safe-call operator length: $length")

    //3. Using Elvis/null-coalescing operator - ?:
    len=uname?.length ?: 0 //with elvis u can assign a non null value
    println("With Elvis operator: $len")

    var x:String = uname?:""
    println("X: $x")

    //4. Non-null Assertion operator - !!.
    uname="XYZ"
    var y:String=uname!! //Chance of NPE( coz u unwrap forcefully)
    println("With Assertion operator: $y")

}