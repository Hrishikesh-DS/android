package com.capgemini.kotlinlearning

//part 3

//function type(Int, Int) -> Int
fun doAdd(a:Int, b:Int):Int {
    return  a+b
}
//function type(Int, Int) -> Int
fun doSubtract(a:Int, b:Int) = a-b

fun main(){
    //1)function type
    var f:(Int, Int) -> Int

    f= ::doAdd //u passing reference of func here
    var res = f(10,20)
    println("Addition: $res")

    f=::doSubtract
    res=f(10,5)
    println("Subtraction: $res")

    //2)higher order function -input or return type is a function
    doCalculation(100,200, ::doAdd)
    doCalculation(200,100,::doSubtract)

    //3)lambda expressions -anonymous function without name
    //short lived function
    //faster execution
    //limited scope inside function
    val doMultiply=  {a:Int,b:Int ->
        println("___________")
        a*b//can have multi statement,last one is the return
    }
    res=doMultiply(2,3)
    println(res)
    doCalculation(10,4, doMultiply)
    doCalculation(10,20,{a,b -> a/b})
    doCalculation(20,10) { a, b -> a / b }//(trailing lambda)if function is last argument

    //type -(String) -> Int
    var s = {a:String ->
        println("Input: $a")
        a.toInt()
    }
    //single argument is automatically referenced with it.
    s={
        println("Input: $it")
        it.toInt()
    }

    println("_____________________")
    println("Converted: ${s("12")+100}")

    val square:(Int) -> Int ={it*it}
    println("Square of a number is: ${square(10)}")


    //4)extension function
    val arr = arrayOf<Any>(1,2,3,4,5)
    arr.printArray()

    //5)nested/local function
    printArea(10,20)

    var num=-5
    while (num!=0){
        val op= makeZero(num)
        num= op(num)
        // or write num=makeZero(num)(num)
    }
    println("Final num: $num")
}


// higher order function(func call inside func)
fun doCalculation(a:Int, b:Int, operation: (Int, Int) -> Int) {
    val result = operation(a,b)
    println("Result: $result")
}
//extension function
//In order to print an array were making a function in the Array Class itself.
fun Array<Any>.printArray(){
    print("[")
    for(e in this){
        if(e==this.size){
            print(e)
        }
        else{
            print("$e, ")
        }
    }
    println("]")
}

//nested function
//only the main function can be called not the nested one in main class
fun printArea(height: Int, width: Int){
    val pi=22/7
    fun calculateArea():Int = height * width *pi//calculate area cant be called in the main function
    val area=calculateArea()
    println("Area: $area")
}


fun incrementByOne(n: Int)=n+1
fun decrementByOne(n: Int)=n-1
//6)higher order function
fun makeZero(num: Int):(Int) -> Int{
    return if(num>0)
        ::decrementByOne
    else
        ::incrementByOne
}