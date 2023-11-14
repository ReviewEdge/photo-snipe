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
fun MainGame(Map: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Main Game Screen")
    }
    Button(onClick = {Map()},
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = "View Map")
    }
}

@Composable
@Preview
fun MainViewPreview(){
    MainGame(Map = {})
}