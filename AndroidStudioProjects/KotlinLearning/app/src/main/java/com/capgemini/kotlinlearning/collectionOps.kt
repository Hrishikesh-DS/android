package com.capgemini.kotlinlearning

fun main(){
    //filter(its boolean)
    val data = listOf(1,2,3,4,5,6,7,8,9,10)
    var evenList = mutableListOf<Int>()
    //list of even numbers
    for(i in data){
        if(i%2==0){
            evenList.add(i)
        }
    }
    println("Even list: $evenList")

    val filteredList=data.filter { it % 2 ==0 }
    println("Even list with filter: $filteredList")

    val contacts = mapOf("John" to 111, "Mary" to 222, "Mark" to 333)
    //Get all numbers of ppl whose name starts with 'M'
    val filteredMap=contacts.filter {
        it.key.startsWith("M")
    }
    println("Filtered map: ${filteredMap.values}")
    println("_________________________________________")

    //map (it assigns the value from lambda func
    //square of each number
    val squaredList=data.map { it*it }
    println("Squared list: $squaredList")
    //square of even numbers
    val squareOfEvens = data.filter { it % 2 ==0 }.map{ it * it}
    println("Square of evens: $squareOfEvens")
    println("___________________________________________")

    //reduce
    //cumulative product(multiplication of all numbers)
    var product=1
    data.forEach{
        product*=it
    }
    println("Cumulative Product: $product")
    val prod = data.reduce { prod, i -> prod*i}//starting val of prod is first element of the list
    println("Cumulative product with reduce: $prod")
}