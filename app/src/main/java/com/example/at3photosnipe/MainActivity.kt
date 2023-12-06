package com.example.at3photosnipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.at3photosnipe.screens.GameInfo
import com.example.at3photosnipe.screens.JoinGame
import com.example.at3photosnipe.screens.NoGameScreen
import com.example.at3photosnipe.screens.StartNewGame
import com.example.at3photosnipe.ui.theme.AT3PhotoSnipeTheme
import com.example.at3photosnipe.PhotoSnipeApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    var gotoCamera = false

    private val gotoCameraLiveData = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI content
        setContent {
            AT3PhotoSnipeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Display either PhotoSnipeApp or CameraActivity based on the value of gotoCamera
                    if (gotoCamera) {
                        Intent(applicationContext, CameraActivity::class.java).also {
                            startActivity(it)
                        }
                    } else {
                        PhotoSnipeApp()
                    }
                }
            }
        }

        // Launch the coroutine after setting the UI content
        startContinuousCheck()
    }

//    private fun startContinuousCheck() {
//        lifecycleScope.launch(Dispatchers.Main) {
//            while (true) {
//                // Perform your boolean check here
//                gotoCamera = checkBooleanValue()
//
//                // Print the boolean value to the log
//                Log.d("MainActivity", "Is Camera Screen: $gotoCamera")
//
//                // Delay for a specific duration before the next check
//                delay(100) // Delay for 100 milliseconds (adjust as needed)
//            }
//        }
//    }

    private fun startContinuousCheck() {
        lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                // Perform your boolean check here
                val newValue = checkBooleanValue()

                // Update LiveData with the new value
                gotoCameraLiveData.value = newValue

                // Delay for a specific duration before the next check
                delay(100) // Delay for 100 milliseconds (adjust as needed)
            }
        }
    }
    private fun checkBooleanValue(): Boolean {
        // Replace this with your actual boolean check logic
        gotoCamera = isCameraScreen
        print(gotoCamera)
        Log.d("e", "$gotoCamera")
        // For example, you might check a shared preference value or some other condition
        return gotoCamera // Placeholder value
    }

//    private fun checkBooleanValue(): Boolean {
//        // Replace this with your actual boolean check logic
//        // For example, you might check a shared preference value or some other condition
//        return true // Placeholder value
//    }
}



//class MainActivity : ComponentActivity() {
//    private fun startContinuousCheck() {
//        // Launch a coroutine in the Main dispatcher
//        // Note: Change the dispatcher if the check involves a background task
//        lifecycleScope.launch(Dispatchers.Main) {
//            while (true) {
//                // Perform your boolean check here
//                isCameraScreen = checkBooleanValue()
//
//                // Delay for a specific duration before the next check
//                delay(100) // Delay for 1 second (adjust as needed)
//            }
//        }
//    }
//
//    var gotoCamera = false;
//    private fun checkBooleanValue(): Boolean {
//        // Replace this with your actual boolean check logic
//        gotoCamera = isCameraScreen
//        print(gotoCamera)
//        Log.d("e", "$gotoCamera")
//        // For example, you might check a shared preference value or some other condition
//        return gotoCamera // Placeholder value
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        startContinuousCheck()
//
//        super.onCreate(savedInstanceState)
//        if (gotoCamera){
//            setContent {
//                AT3PhotoSnipeTheme {
//                    // A surface container using the 'background' color from the theme
//                    Surface(
//                        modifier = Modifier.fillMaxSize(),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                            Intent(applicationContext, CameraActivity::class.java).also {
//                                startActivity(it)
//                            }
////                    JoinGame()
////                    StartNewGame()
////                    GameInfo()
//                    }
//                }
//            }
//        } else {
//            setContent {
//                AT3PhotoSnipeTheme {
//                    Surface(
//                        modifier = Modifier.fillMaxSize(),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                        PhotoSnipeApp()
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AT3PhotoSnipeTheme {
        Greeting("Android")
    }
}