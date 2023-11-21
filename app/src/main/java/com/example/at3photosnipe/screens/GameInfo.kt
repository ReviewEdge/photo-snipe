package com.example.at3photosnipe.screens

import android.app.GameManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.Player

@Composable
fun GameInfo(VM: GameViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // should probably change:
        var isGameManager by remember { mutableStateOf(VM.isGameMangager()) }



        Text(text=VM.getGameName(), fontSize = 32.sp, modifier = Modifier.padding(12.dp))

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
        )

        Text(text="Share this code so others can join:", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
        Text(text=VM.getJoinCode(), fontSize = 40.sp, modifier = Modifier.padding(12.dp))

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
        )

        Text(text="Players:", fontSize = 24.sp, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
        LazyColumn {
            items(VM.getPlayers()) { player ->
                println(player.name)
                BulletedListItem(player = player, isGameManager, VM=VM)
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
                /*TODO*/
                // remove player
                VM.removePlayer(player)
            }) {
                Text(text = "Remove", color = Color.Red)
            }
        }
    }
}
