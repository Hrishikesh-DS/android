package com.capgemini.kotlinlearning

fun main(){

    //list -dynamic arrays

//    val langs = listOf("C", "C++", "Python", "Java")
    val langs = mutableListOf("C", "C++", "Python", "Java")
    //val cant be reassigned/re-referenced but the elements in the list can be changed
    println("List: $langs")
    println("Size: ${langs.size}")
    println("First: ${langs[0]}")
    println("Last: ${langs.last()}")

    for(e in langs){
        println("Languages: $e")
    }
    langs.forEach{
        println("Lang: ${it.toUpperCase()}")
    }

    println("First three elements: ${langs.slice(0..2)}")
    langs.add("Java")//append
    langs[0]="Java"//update
    langs.add(2,"Java")//insert operation
    println("Updated: $langs")
    //removal
    langs.remove("Java")
    while ("Java" in langs){
        langs.remove("Java")
    }
//    println("Removed: $langs")
//    val rem = mutableListOf<String>("Java")
//    val removeAll = langs.removeAll(rem)
//    println("Rem: $langs") //alternative
    println("Removed: $langs")
    langs.reverse()
    println("Reversed: $langs")
    println("__________________________________")
    //set -unique elements

    val listOfNum = listOf(1,2,3,1,2,1,4,5)
    //remove duplicates
    val setOfNum = listOfNum.toSet()
    println("Original list: $listOfNum")
    println("Set: $setOfNum")

    setOfNum.forEach{
        println("Square of each number: ${it*it}")
    }
    println("__________________________________")

    //map -key-value pair, key is index(Dictionary)
    //key should be unique here coz it acts as index
    val contacts = mutableMapOf("John" to 111, "Mary" to 222, Pair("Robert",333))
    println("Map: $contacts")
    println("Number of 'John':${contacts["John"]}")
    println("All contact names: ${contacts.keys}")
    println("All contact numbers: ${contacts.values}")
    if("Robert" in contacts)
        println("Robert's number: ${contacts["Robert"]}")
    else
        println("No contact info")
    //add, update, delete
    contacts["Robert"]= 444 //update/replace
    contacts["Mark"] = 555 //add
    contacts.put("Julius",1111)
    contacts.remove("Mary")
    println("Updated: $contacts")

    //merging
    val officeContacts= mapOf("John" to 222, "Hrishi" to 333)
    contacts.putAll(officeContacts)
    println("Merged: $contacts")

    contacts.forEach { s, i ->
        println("$s Number: $i ")
    }
}