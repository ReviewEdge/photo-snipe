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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.Player
import com.example.at3photosnipe.data.GameInstance


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun JoinGame(VM: GameViewModel, Join: () -> Unit) {
    var joinCode by remember { mutableStateOf("") }
    var newPlayerName by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current


    val gList by VM.gameInstances.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text input composable with uppercase transformation
        TextField(
            value = joinCode,
            onValueChange = {
                joinCode = it.uppercase()
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

            //TODO:
            //ACTIVATE THIS CODE IN PRODUCTION (leaving backdoor for dev)
//            if (!VM.isNameTaken(newPlayerName)) {
//
//            }


                if ((joinCode != null) and newPlayerName.isNotEmpty()) {
                    keyboardController?.hide()

                    var gameThatImGonnaJoin: GameInstance? = null
                    gList.forEach {g->
                        if(g.gameJoinCode == joinCode) {
                            gameThatImGonnaJoin = g
                        }
                    }

                    if (gameThatImGonnaJoin != null) {
                        println("found the game")

                        VM.createPlayerAndAddToGameAndSetCurrents(newPlayerName = newPlayerName, game = gameThatImGonnaJoin!!)

                        println("added player to game")

                        Join()
                    }

                }

        },
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="JOIN GAME", fontSize = 24.sp)
        }

    }
}

