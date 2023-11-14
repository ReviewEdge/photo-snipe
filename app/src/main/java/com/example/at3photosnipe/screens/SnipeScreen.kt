package com.example.at3photosnipe.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Snipe(){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Snipe Screen Screen")
    }
}

@Composable
@Preview
fun SnipePreview(){
    Snipe()
}