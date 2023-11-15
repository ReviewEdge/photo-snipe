package com.example.at3photosnipe.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.at3photosnipe.R


data class Player(val name: String, val score: Int)

data class Snipe(val pictureRes: Int, val sniper: Player, val snipee: Player, val timeOfDay: String)


@Composable
fun MainGame(Map: () -> Unit){

    val players = listOf(
        Player(name = "Player 1", score = 21),
        Player(name = "Player 2", score = 18),
        Player(name = "Player 3", score = 18),
        Player(name = "Player 4", score = 14),
        // Add more players as needed
    )

    val s1 = Snipe(pictureRes = R.drawable.architecture, sniper = players.first(), snipee = players[1], "10:27 AM")
    val s2 = Snipe(pictureRes = R.drawable.business, sniper = players[2], snipee = players[1], "10:52 AM")
    val s3 = Snipe(pictureRes = R.drawable.culinary, sniper = players[3], snipee = players[0], "11:08 AM")
    val s4 = Snipe(pictureRes = R.drawable.film, sniper = players.first(), snipee = players[3], "11:15 AM")

    val snipes = listOf(s1,s2,s3,s4)



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
                    text = "Grove City",
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
                    items(players) { player ->
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
                items(snipes) { snipe ->
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
        Text(
            text = "${snipe.sniper.name} sniped ${snipe.snipee.name}",
//            style = MaterialTheme.typography.body1,
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





@Composable
@Preview
fun MainViewPreview(){
    MainGame(Map = {})
}