package com.capgemini.andriodui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view_demo.*

//PhoneGap, Cordova, React Native - hybrid mobile app framework

class WebViewDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_demo)

        webContent.settings.javaScriptEnabled = true
        webContent.settings.allowContentAccess=true


        webContent.loadUrl("https://capgemini.com")
    }
}