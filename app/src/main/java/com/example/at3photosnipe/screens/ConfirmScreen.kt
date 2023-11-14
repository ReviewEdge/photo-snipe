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
fun ConfirmSnipe(Confirm: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Confirm Snipe Screen")
    }
    Button(onClick = {Confirm()},
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = "Confirm Snipe")
    }
}

@Composable
@Preview
fun ConfirmSnipePreview(){
    ConfirmSnipe(Confirm = {})
}