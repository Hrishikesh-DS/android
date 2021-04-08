package com.capgemini.androidservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RPCservice : Service() {

    override fun onBind(intent: Intent): IBinder {
        return MyMathImplementation()
    }

    class MyMathImplementation: IMathInterface.Stub(){
        override fun add(a:Int,b:Int):Int{
            return a+b
        }
        override fun subtract(a:Int,b:Int):Int{
            return a-b
        }
    }
}