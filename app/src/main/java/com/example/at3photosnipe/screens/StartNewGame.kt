package com.example.at3photosnipe.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.R


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StartNewGame(VM: GameViewModel, CreateGame: () -> Unit) {
    var newGameName by remember { mutableStateOf("") }
    var newPlayerName by remember { mutableStateOf("") }
    var printedText by remember { mutableStateOf<String?>(null) }




    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text input composable with uppercase transformation
        TextField(
            value = newGameName,
            onValueChange = {
                newGameName = it
            },
            label = { Text("Enter a name for your game:") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(80.dp), // Adjusting the height for a larger input field
            textStyle = TextStyle(fontSize = 32.sp)
        )


        TextField(
            value = newPlayerName,
            onValueChange = {
                newPlayerName = it
            },
            label = { Text("Enter your name:") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(80.dp), // Adjusting the height for a larger input field
            textStyle = TextStyle(fontSize = 32.sp)
        )




        Button(onClick = {
            if (newGameName.isNotEmpty() and newPlayerName.isNotEmpty()) {
                VM.createGame(newGameName = newGameName,
                    firstPlayerName = newPlayerName)


                keyboardController?.hide()
                CreateGame()
            }
        },
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="Start New Game", fontSize = 24.sp,
                fontFamily = FontFamily(Font(resId = R.font.poppins_light))
            )
        }

    }
}
