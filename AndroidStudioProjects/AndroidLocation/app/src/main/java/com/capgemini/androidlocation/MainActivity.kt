package com.capgemini.androidlocation

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), LocationListener {

    lateinit var lManager:LocationManager
    lateinit var locT : TextView
    var currentLoc: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locT= findViewById(R.id.locationE)
        checkPermissions()

        //get the reference of the LocationManager
        lManager=getSystemService(LOCATION_SERVICE) as LocationManager

        //Location providers - select one
        val providerList=lManager.getProviders(true)
        var providerName=""
        if(providerList.isNotEmpty())
        {
            if(providerList.contains(LocationManager.GPS_PROVIDER))
            {
                providerName=LocationManager.GPS_PROVIDER
            }
            else if(providerList.contains(LocationManager.NETWORK_PROVIDER))
            {
                providerName=LocationManager.NETWORK_PROVIDER
            }
            else
            {
                providerName=providerList[0]
            }
            Toast.makeText(this,"Provider name: $providerName",Toast.LENGTH_SHORT).show()
            Log.d("MainActivity","Provider: $providerName")

//            if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                    checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            val loc = lManager.getLastKnownLocation(providerName)
            if (loc != null){
                updateLocation(loc)
            }
            else{
                Toast.makeText(this,"No Location Found",Toast.LENGTH_SHORT).show()
            }

            // Register location listener
            val time:Long=1000
            val distance: Float= 10.0f
            lManager.requestLocationUpdates(providerName,time,distance,this)

        }

        else
        {
            Toast.makeText(this,"Pls enable Location",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        lManager.removeUpdates(this)
    }

    private fun updateLocation(loc: Location) {
        val latt = loc.latitude
        val longi = loc.longitude
        var distance:Float = 0f

        if(currentLoc != null)
        {
            distance = currentLoc?.distanceTo(loc)!!
        }
        currentLoc=loc



        locT.append("\n Lattitude: $latt,\n Longitude: $longi, \n Distance: $distance \n")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Show on Map")
        menu?.add("Address")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == "address"){
            val address = getAddress()
            Toast.makeText(this,address,Toast.LENGTH_LONG).show()
        }
        else {
            val mapIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:${currentLoc?.latitude},${currentLoc?.longitude}"))
            startActivity(mapIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAddress(): String{
        val gCoder = Geocoder(this)
        val addressList=gCoder.getFromLocation(currentLoc?.latitude!!,
            currentLoc?.longitude!!,10)

        if(addressList.isNotEmpty()){
            val addr = addressList[0]
            var strAddress = ""
            for(i in 0..addr.maxAddressLineIndex){
                strAddress += addr.getAddressLine(i)
            }
            return strAddress
        }
        return ""
    }

    fun checkPermissions()
    {
        //min SDK 23
        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),1)
        }
        else
        {
            Toast.makeText(this,"Location permission Granted",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.isNotEmpty())
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Location permission granted",Toast.LENGTH_SHORT).show()
            }
            else
            {
                finish()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location) {
        updateLocation(location)
    }
}