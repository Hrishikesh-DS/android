package com.capgemini.kotlinlearning

class ListOperations<T>(val list:List<T>){
    fun oddIndexedItems(): List<T>{
        return list.filterIndexed { i, _ ->
            (i%2!=0)
        }
    }
}

fun <T> getOddItems(list:List<T>):List<T>{
    return list.filterIndexed { i, _ ->
        (i%2!=0)
    }
}

data class Persons(val name:String)

fun main(){
    val listOfInt = listOf(1,2,3,4,5,6,7,8)
    val intList = ListOperations<Int>(listOfInt)
    println("filtered Int: ${intList.oddIndexedItems()}")

    val strList = listOf("C","C++","JAVA","Python")
    val strListOp=ListOperations<String>(strList)
    println("Strings at odd index: ${strListOp.oddIndexedItems()}")

    var personList = mutableListOf<Persons>()
    personList.add(Persons("Hrishi"))
    personList.add(Persons("Ram"))
    personList.add(Persons("Mark"))
    personList.add(Persons("Mary"))

    val personOp = ListOperations<Persons>(personList)
    println("Person at odd index: ${personOp.oddIndexedItems()}")

    println("Person at odd index with generic func: ${getOddItems(personList)}")
}