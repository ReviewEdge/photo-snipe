package com.example.at3photosnipe.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.Player
import com.example.at3photosnipe.R

@Composable
fun GameInfo(VM: GameViewModel, Map: () -> Unit) {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isGameManager by remember { mutableStateOf(VM.isGameManager()) }
        val allPlayersList by VM.allPlayersList.collectAsState()

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
        )

        Text(text="Share this code so others can join:", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
        VM.getJoinCode()
            ?.let { Text(text= it, fontSize = 40.sp, modifier = Modifier.padding(12.dp)) }

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
        )


        Button(
            onClick = { Map() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "View Map",
                fontFamily = FontFamily(
                    Font(resId = R.font.poppins_light)
                )
            )
        }


        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
        )




        Text(text="Players:", fontSize = 24.sp, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
        LazyColumn {

            items(allPlayersList) { player ->

                if(player.gameInstanceId == VM.currentGameInstance!!.game_id) {
                    BulletedListItem(player = player, isGameManager, VM=VM)
                }

            }
        }

    }
}

@Composable
fun BulletedListItem(player: Player, isGameManager: Boolean, VM: GameViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "• " + player.name)
        if(isGameManager) {
            Button(onClick = {
                // remove player
                VM.removePlayer(player)
            }) {
                Text(text = "Remove", color = Color.Red)
            }
        }
    }
}
