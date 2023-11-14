package com.example.at3photosnipe.screens

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun ShowMap(){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Show Map Screen")
    }
    // TODO:
    val gcc = LatLng(41.155298, -80.079247)
    val cameraPosition = rememberCameraPositionState(){
        position = CameraPosition.fromLatLngZoom(gcc, 15f)
    }

    GoogleMap(
        modifier  =Modifier.fillMaxSize(),
        cameraPositionState = cameraPosition
    ) {
        Marker(
            state = rememberMarkerState(position = gcc),
            draggable = true,
            title = "Grove City College",
            snippet = "Marker in GCC",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
//            icon = bitmapDescriptorFromVector(
//                LocalContext.current,
//                R.drawable.babybowser, width = 100, height = 100
//            )
        )
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