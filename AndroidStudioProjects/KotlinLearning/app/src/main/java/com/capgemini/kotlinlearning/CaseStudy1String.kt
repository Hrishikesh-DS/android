package com.capgemini.kotlinlearning

fun main(){
    var logdata="""
        This is error log #2
        This is debug log #10
        This is error log #12
        This is error log #30
        This is debug log #15
        
    """.trimIndent()

    /*
    Find count of:
    Total number of logs
    Total number of error logs
    Total number of debug logs

    for error logs, print the error code too
     */
    var logcount=0
    var errorcount=0
    var debugcount=0
    var logs=logdata.lines()
    for(l in logs){
        if(l.contains("log")){
            logcount=logcount+1
        }
        if(l.contains("error")){
            errorcount=errorcount+1
        }
        if(l.contains("debug")){
            debugcount=debugcount+1
        }
        if(l.contains("error")){
            var p=l.indexOf('#')
            println("Error code: ${l.substring(p+1)}")
        }

    }
    println("Log count: $logcount")
    println("Debug count: $debugcount")
    println("Error count: $errorcount")
}