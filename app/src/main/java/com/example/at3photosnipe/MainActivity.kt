package com.example.at3photosnipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.at3photosnipe.screens.GameInfo
import com.example.at3photosnipe.screens.JoinGame
import com.example.at3photosnipe.screens.NoGameScreen
import com.example.at3photosnipe.screens.StartNewGame
import com.example.at3photosnipe.ui.theme.AT3PhotoSnipeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AT3PhotoSnipeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotoSnipeApp()
//                    NoGameScreen()
//                    JoinGame()
//                    StartNewGame()
//                    GameInfo()
                }

            }
        }
    }
}

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