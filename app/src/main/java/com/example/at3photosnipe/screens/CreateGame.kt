package com.example.at3photosnipe.screens

import android.icu.text.CaseMap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateGame(StartGame: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Create Game Screen")
    }
    Button(onClick = {StartGame()},
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = "Create Game")
    }
}

@Composable
@Preview
fun CreateGamePreview(){
    CreateGame(StartGame = {})
}