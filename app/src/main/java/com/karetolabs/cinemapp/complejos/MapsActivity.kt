package com.karetolabs.cinemapp.complejos

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerDragListener, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setInterval(10 * 100)
            .setExpirationDuration(1000)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.llBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


    }

    private fun validatePermissions() {
        if ((ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
            &&
            (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
        ) {
            getUserLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if (grantResults.isNotEmpty()) {
                    getUserLocation()
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val delta = LatLng(19.4028844, -99.155805)
        val vallejo = LatLng(19.468312, -99.1470277)
        val real = LatLng(19.4364687, -99.149468)

        mMap.addMarker(
            MarkerOptions()
                .position(delta)
                .title("Parque Delta")
                .draggable(true)
        )

        mMap.addMarker(
            MarkerOptions()
                .position(vallejo)
                .title("Marker in Sydney")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        val polygon = mMap.addPolygon(
            PolygonOptions()
                .add(delta, real, vallejo)
                .strokeColor(Color.RED)
                .fillColor(ContextCompat.getColor(this, R.color.primary_color))
        )

        polygon.isClickable = true

        mMap.setOnPolygonClickListener {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(19.4117632, -99.1625216))
                    .title("Marker in Sydney")
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
            )
        }


        val marker = mMap.addMarker(
            MarkerOptions()
                .position(real)
                .title("Marker in Sydney")
                .draggable(true)
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.images))
                .icon(getBitmapDescriptorFromVector(this, R.drawable.ic_image))
        )
        marker?.tag = "Hola Mundo"

        mMap.setOnMarkerDragListener(this)
        mMap.setOnMarkerClickListener {
            binding.txtTitle.text = it.title
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            println("Position Marker" + it.position.toString())
            println("Position Marker" + it.title)
            println("Position Marker" + marker?.tag)
            true
        }
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(real, 15f)
        )
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        //GoogleMap.MAP_TYPE_TERRAIN
        //GoogleMap.MAP_TYPE_NONE
        //GoogleMap.MAP_TYPE_NORMAL
        //GoogleMap.MAP_TYPE_SATELLITE
        googleMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
            isTiltGesturesEnabled = false
            isCompassEnabled = false
            isMapToolbarEnabled = false

        }
        googleMap.isTrafficEnabled = false
        validatePermissions()
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        //Obtenemos la ultima ubicacion del usuario
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            val currentPosition = LatLng(it.latitude, it.longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(currentPosition)
                    .title("Marker in Home")
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 15f))
        }

        //Nos devuelve la ubicacion cada que ocurre un cambio con el usuario
        fusedLocationProviderClient.getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                    return this
                }

                override fun isCancellationRequested(): Boolean {
                    return false
                }

            }).addOnSuccessListener {
            setLocation(it.latitude, it.longitude)
        }
        mMap.isMyLocationEnabled = true
    }


    /**
     * Permite utilizar imagenes vectorizadas (svg) en mapas
     */
    private fun getBitmapDescriptorFromVector(
        context: Context,
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor {

        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        val bitmap = Bitmap.createBitmap(
            vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    override fun onMarkerDrag(p0: Marker) {
        println("OnMarkerDragLatitude" + p0.position.latitude + "Longitude" + p0.position.longitude)
    }

    override fun onMarkerDragEnd(p0: Marker) {
        println("OnMarkerDragEndLatitude" + p0.position.latitude + "Longitude" + p0.position.longitude)
    }

    override fun onMarkerDragStart(p0: Marker) {
        println("OnMarkerDragStartLatitude" + p0.position.latitude + "Longitude" + p0.position.longitude)
    }

    override fun onLocationChanged(p0: Location) {
        println("Ubicacion" + p0.latitude + p0.longitude)
        setLocation(p0.latitude, p0.longitude)

    }


    private fun setLocation(latitude: Double, longitude: Double) {
        val currentPosition = LatLng(latitude, longitude)
        mMap.addMarker(
            MarkerOptions()
                .position(currentPosition)
                .title("Marker in Home")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 15f))
    }

}