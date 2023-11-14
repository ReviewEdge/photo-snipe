package com.example.at3photosnipe.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoGameScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="NEW GAME", fontSize = 24.sp)
        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="JOIN A GAME", fontSize = 24.sp)
        }
    }
}


@Preview
@Composable
fun NoGameScreenPreview() {
    NoGameScreen()
}