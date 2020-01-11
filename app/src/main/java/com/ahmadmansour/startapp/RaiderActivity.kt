package com.ahmadmansour.startapp

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RaiderActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var accessLocationRequestCode = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.rider_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
    }

    private fun checkPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                            .PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        accessLocationRequestCode)
                return
            }
        }
        // continue because you have permission
       getCurrentLocation()
    }

    private fun getCurrentLocation() {
        Toast.makeText(this.baseContext, "user enabled the location permession", Toast
                .LENGTH_LONG).show()
        var locationOnMap = mapLocationListener(this.baseContext)
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3,
                3f, locationOnMap)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //25.2048° N, 55.2708° E
        // Add a marker in Dubai and move the camera
        val dubai = LatLng(25.2048, 55.2708)
        mMap.addMarker(MarkerOptions().position(dubai).title("new Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dubai))
    }

    var location: Location? = null

    inner class mapLocationListener: LocationListener {
        var context: Context?= null

        constructor(context: Context) {
            location = Location("Start")
            location!!.latitude = 0.0
            location!!.longitude = 0.0
            this.context = context
        }

        override fun onLocationChanged(location: Location?) {
            Toast.makeText(context, "user location has been changed" + location!!.latitude + ", "
                    + location!!.longitude, Toast
                    .LENGTH_LONG).show()
            mMap.clear()

            val dubai = LatLng(location!!.latitude, location!!.longitude)
            mMap.addMarker(MarkerOptions().position(dubai).title("New Location, 00971506708115"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(dubai))
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }

    }

}
