package com.example.at3photosnipe.screens
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.core.content.ContextCompat
import com.example.at3photosnipe.getUserLocation
//import com.google.android.gms.maps.model.BitmapDescriptor
//import com.google.android.gms.maps.model.BitmapDescriptorFactory
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import com.google.maps.android.compose.GoogleMap
//import com.google.maps.android.compose.MapProperties
//import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
//
//@Composable
//fun ShowMap(){
//    Column(modifier = Modifier.fillMaxSize()){
//        Text(text = "Show Map Screen")
//    }
//    // TODO:
//
//    val context = LocalContext.current
//
//    val otherLoc = getUserLocation(context = context)
//    val otherLat = otherLoc.latitude
//    val otherLng = otherLoc.longitude
//
//
//    Log.e("lat", otherLat.toString())
//    Log.e("lng", otherLng.toString())
//
//    val locs = listOf("30.123,-100.445", "31.123,-101.445", "32.123,-102.445", "33.123,-103.445", "34.123,-104.445", "${otherLat.toString()},${otherLng.toString()}")
//
//
//    val locations = mutableListOf<LatLng>()
//
//    for (i in locs){
//        var temp = i
//        var lat = temp.substringBeforeLast(",").toDouble()
//        var lng = temp.substringAfterLast(",").toDouble()
//
//        var coords = LatLng(lat, lng)
//        locations.add(coords)
//    }
//
//
//    val gcc = LatLng(41.155298, -80.079247)
//    val cameraPosition = rememberCameraPositionState(){
//        position = CameraPosition.fromLatLngZoom(gcc, 15f)
//    }
//
//    GoogleMap(
//        modifier  =Modifier.fillMaxSize(),
//        cameraPositionState = cameraPosition
//    ) {
//        Marker(
//            state = rememberMarkerState(position = gcc),
//            draggable = true,
//            title = "Grove City College",
//            snippet = "Marker in GCC",
//            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
////            icon = bitmapDescriptorFromVector(
////                LocalContext.current,
////                R.drawable.babybowser, width = 100, height = 100
////            )
//        )
//    }
//}
//
//// this function converts from an image in our project into a marker that will show up on google maps
//fun bitmapDescriptorFromVector(context: Context, iconResourceId: Int, width: Int? = null, height: Int? = null): BitmapDescriptor? {
//
//    val drawable = ContextCompat.getDrawable(context, iconResourceId) ?: return null
//    val drawWidth = width ?: drawable.intrinsicWidth
//    val drawHeight = height ?: drawable.intrinsicHeight
//    drawable.setBounds(0, 0, drawWidth, drawHeight)
//    val bm = Bitmap.createBitmap(
//        drawWidth,
//        drawHeight,
//        Bitmap.Config.ARGB_8888
//    )
//
//    val canvas = android.graphics.Canvas(bm)
//    drawable.draw(canvas)
//    return BitmapDescriptorFactory.fromBitmap(bm)
//}
//
//@Composable
//@Preview
//fun ShowMapPreview(){
//    ShowMap()
//}






//package com.example.at3photosnipe.screens

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.R
import com.example.at3photosnipe.getUserLocation
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun ShowMap(){

//    val VM: GameViewModel = GameViewModel.getInstance()

//    var locs = VM.snipeLocations.collectAsState()
//    var len = locs.value.size


    val context = LocalContext.current
//
    val otherLoc = getUserLocation(context = context)
    val otherLat = otherLoc.latitude
    val otherLng = otherLoc.longitude


    Log.e("lat", otherLat.toString())
    Log.e("lng", otherLng.toString())

    if (otherLat != 0.0){

        val locs = listOf("30.123,-100.445", "31.123,-101.445", "32.123,-102.445", "33.123,-103.445", "34.123,-104.445", "${otherLat.toString()},${otherLng.toString()}")

        Column(modifier = Modifier.fillMaxSize()){
            Text(text = "Show Map Screen")
        }
        // TODO:
        val gcc = LatLng(41.155298, -80.079247)
        val cameraPosition = rememberCameraPositionState(){
            position = CameraPosition.fromLatLngZoom(gcc, 15f)
        }

        var properties by remember {
            mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
        }

        val locations = mutableListOf<LatLng>()

        for (i in locs){
            var temp = i
            var lat = temp.substringBeforeLast(",").toDouble()
            var lng = temp.substringAfterLast(",").toDouble()

            var coords = LatLng(lat, lng)
            locations.add(coords)
        }

//    for (i in locs){
//        Log.e("loc in locs", "${i.toString()}")
//    }

//    locations.add(otherLoc)

        GoogleMap(
            modifier  =Modifier.fillMaxSize(),
            cameraPositionState = cameraPosition,
            properties = properties
        ) {
            for(location in locations){
                Marker(
                    state = rememberMarkerState(position = location),
                    draggable = true,
                    title = "Grove City College",
                    snippet = "Marker in GCC",
//            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    icon = bitmapDescriptorFromVector(
                        LocalContext.current,
                        R.drawable.baseline_gps_not_fixed_24, width = 80, height = 80
                    )
                )
            }
        }
    }
}

// this function converts from an image in our project into a marker that will show up on google maps
fun bitmapDescriptorFromVector(context: Context, iconResourceId: Int, width: Int? = null, height: Int? = null): BitmapDescriptor? {

    val drawable = ContextCompat.getDrawable(context, iconResourceId) ?: return null
    val drawWidth = width ?: drawable.intrinsicWidth
    val drawHeight = height ?: drawable.intrinsicHeight
    drawable.setBounds(0, 0, drawWidth, drawHeight)
    val bm = Bitmap.createBitmap(
        drawWidth,
        drawHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

@Composable
@Preview
fun ShowMapPreview(){
    ShowMap()
}