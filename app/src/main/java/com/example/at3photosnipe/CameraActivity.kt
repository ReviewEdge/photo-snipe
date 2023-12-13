package com.example.at3photosnipe

import CameraView
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.at3photosnipe.data.Snipe
//import com.example.testintent.ui.theme.TestIntentTheme
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity: ComponentActivity() {

    val VM : GameViewModel = GameViewModel.getInstance()


    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(true)

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true
    }

//    private fun getOutputDirectory(): File {
//        val mediaDir = R.drawable
////        val mediaDir = externalMediaDirs.firstOrNull()?.let {
////            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
////        }
//        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
//    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
        } else {
            Log.i("kilo", "Permission denied")
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {





            val pList by VM.allPlayersList.collectAsState()
            var seId by remember { mutableStateOf("") }
            var seName by remember { mutableStateOf("") }

            for(it in pList) {
                if((it.player_id != VM.currentPlayer!!.player_id) and (it.gameInstanceId == VM.currentGameInstance!!.game_id)) {
                    seId = it.player_id.toString()
                    seName = it.name
                    break
                }
            }

            var expanded by remember { mutableStateOf(false) }



            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("kilo", "View error:", it) }
                )
            }
            if (shouldShowPhoto.value) {
                Column(modifier = Modifier.fillMaxWidth()){
                    Image(
                        painter = rememberAsyncImagePainter(photoUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    val lContext = LocalContext.current


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            }
                        ) {
                            TextField(
//                                value = seName,
                                value = "Select who you sniped",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                modifier = Modifier.menuAnchor()
                            )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {


                                pList.forEach { player ->

                                    //TODO: switch versions after testing
                                    if (player.gameInstanceId == VM.currentGameInstance!!.game_id) {
//                                  if ((player.gameInstanceId == VM.currentGameInstance!!.game_id) and (player.player_id != VM.currentPlayer!!.player_id)) {

                                        DropdownMenuItem(
                                            onClick = {
                                                seId = player.player_id.toString()
                                                seName = player.name

                                                expanded = false
                                                Toast.makeText(lContext, player.name, Toast.LENGTH_SHORT).show()
                                            },
                                            text = { Text(player.name) }
                                        )
                                    }

                                }

                            }
                        }
                    }


                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()){


                        Button(
                            onClick = {
                                finish()
//                                lContext.startActivity(Intent(lContext, CameraActivity::class.java))
                            },
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(text = "Cancel",
                                fontFamily = FontFamily(Font(resId = R.font.poppins_light)))
                        }
                        Button(
//                            onClick = { lContext.startActivity(Intent(lContext, AfterSnipeActivity::class.java)) },
                            onClick = {



                                val ns = VM.newSnipe(
                                    pictureRes = photoUri.toString(),
                                    snipeeId = seId.toInt(),
                                    location = ""
                                )


                                finish()


                          },
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(text = "Save Snipe",
                                fontFamily = FontFamily(Font(resId = R.font.poppins_light))
                            )
                            isCameraScreen = false
                        }
                    }
                }
            }








        }
        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }
}