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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.R

@Composable
fun NoGameScreen(CreateGame: () -> Unit, JoinGame: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="PhotoSnipe",
            fontSize = 55.sp,
            modifier = Modifier.padding(vertical=50.dp),
            fontFamily = FontFamily(Font(resId = R.font.poppins_bold))
        )


        Button(onClick = {CreateGame()},
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="NEW GAME", fontSize = 24.sp,
                fontFamily = FontFamily(Font(resId = R.font.poppins_light)))
        }
        Button(onClick = {JoinGame()},
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text="JOIN A GAME", fontSize = 24.sp,
                fontFamily = FontFamily(Font(resId = R.font.poppins_light)))
        }
    }
}


@Preview
@Composable
fun NoGameScreenPreview() {
    NoGameScreen(CreateGame = {}, JoinGame = {})
}