package com.example.at3photosnipe.screens

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun JoinGame(Join: () -> Unit) {
    var textValue by remember { mutableStateOf("") }
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
            value = textValue,
            onValueChange = {
                textValue = it.uppercase()
            },
            label = { Text("Enter your game code:") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(80.dp), // Adjusting the height for a larger input field
            textStyle = TextStyle(fontSize = 36.sp)
        )


        Button(onClick = {
            // Print the text when the button is clicked
            printedText = textValue
            keyboardController?.hide()
            Join()
        },
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="JOIN GAME", fontSize = 24.sp)
        }

        // Display the printed text
        printedText?.let {
            //do something with input
//            Text("Printed Text: $it", modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
@Preview
fun JoinGamePreview(){
    JoinGame(Join = {})
}