package com.example.at3photosnipe.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Snipe(Snipe: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Snipe Screen Screen")
    }
    Button(onClick = {Snipe()},
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = "Snipe")
    }
}

@Composable
@Preview
fun SnipePreview(){
    Snipe(Snipe = {})
}