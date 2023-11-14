package com.example.at3photosnipe.screens

import android.icu.text.CaseMap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CreateGame(){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Create Game Screen")
    }
}

@Composable
@Preview
fun CreateGamePreview(){
    CreateGame()
}