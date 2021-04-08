package com.capgemini.kotlinlearning

import kotlin.math.roundToLong

/*
class BankAccount(Base)
    -accountName -read-only -primary constructor
    -accountNumber -read-only -primary constructor
    -balance -mutable (not a part of constructor)(initial 0)

    -deposit(amount) -inc balance
    -withdraw(amount) -dec balance
    -displayInfo()

class SavingsAccount(Derived)
-(accountName,accountNo) from -primary constructor
-interestRate (read-only)

-depositInterest() -adding interest according to rate with balance
 */
open class BankAccount(val accountName:String, val accountNumber:Long){
    var balance:Double = 0.0
    get(){ //custom getter //return field //added by compiler
        return field.roundToLong().toDouble()
    }
    set(value){ //custom setter
        field = if (value<0)
            0.0
        else
            value
        //field = value
    }
    fun deposit(value:Double){
        balance+=value
    }
    fun withdraw(amount:Double){
        if(amount<= balance)
            balance-=amount
        else {
            println("Invalid amount:More than balance")
            println("_____")
        }
    }
    open fun displayInfo(){
        println("Account Name: $accountName")
        println("Account Number: $accountNumber")
        println("Balance: $balance")
        println("_____")
    }
}

class SavingsAccount(accountName: String,accountNumber: Long, private val interest: Double):
        BankAccount(accountName,accountNumber){
            private var interestAmt:Double=0.0
            fun depositInterest(){
                interestAmt=balance * interest / 100.00
                balance += interestAmt
            }
            override fun displayInfo(){
                super.displayInfo()
                println("Interest: $interest")
                println("Interest Amount: $interestAmt")
                println("___________________________")
            }
        }
fun main(){
    val axisBank=BankAccount("Mark",100)
    axisBank.displayInfo()
    axisBank.deposit(1000.00)
    axisBank.displayInfo()
    axisBank.withdraw(200.00)
    axisBank.displayInfo()
    print("Withdrawing more than balance: ")
    axisBank.withdraw(1000.00)
    axisBank.displayInfo()

    val axisSaving=SavingsAccount("Mary",200,8.0)
    axisSaving.deposit(5000.00)
    axisSaving.displayInfo()
    axisSaving.depositInterest()
    axisSaving.displayInfo()

}