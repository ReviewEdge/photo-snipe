package com.example.at3photosnipe.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.Player
import com.example.at3photosnipe.Snipe
import com.example.at3photosnipe.R




@Composable
fun MainGame(VM: GameViewModel, Map: () -> Unit, goToInfo: () -> Unit){

    Column {

        Divider(color = Color(android.graphics.Color.parseColor("#93a8c9")))

        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color(android.graphics.Color.parseColor("#93a8c9")))
                .fillMaxHeight(.33f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Text(
                    modifier = Modifier.clickable(enabled = true, onClick={goToInfo()}),
                    text = VM.getGameName(),
                    //                modifier = Modifier.padding(16.dp)
                    fontSize = 32.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Scores:", modifier = Modifier.padding(8.dp), fontSize = 19.sp)
                Button(
                    onClick = { Map() },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = "View Map")
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                content = {
                    items(VM.getPlayers()) { player ->
                        PlayerItem(player = player)
                    }
                }
            )

            Divider(color = Color(android.graphics.Color.parseColor("#93a8c9")))

        }

        Column () {

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxSize(),
//                reverseLayout = true
            ) {
                items(VM.getSnipes()) { snipe ->
                    PictureCard(snipe = snipe)
                }
            }

        }


//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Button(
//                onClick = { Map() },
//                modifier = Modifier.padding(12.dp)
//            ) {
//                Text(text = "View Map")
//            }
//        }
    }
}



@Composable
fun PlayerItem(player: Player) {
    // Display the player's name and score with a dash in between
    Text(
        text = "${player.name} - ${player.score}",
//        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(8.dp)
    )
}




@Composable
fun PictureCard(snipe: Snipe) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the picture
        Image(
            painter = painterResource(id = snipe.pictureRes),
            contentDescription = null, // You should provide a proper content description
            modifier = Modifier
//                .fillMaxWidth()
                .height(300.dp)
                .width(300.dp)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        // Display the text
        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("${snipe.sniper.name}")
            }

            append(" sniped ")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("${snipe.snipee.name}")
            }
        }

        Text(
            text = annotatedString,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        // Display the time of day
        Text(
            text = "${snipe.timeOfDay}",
            fontSize = 12.sp,
//            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Divider()

    }
}




