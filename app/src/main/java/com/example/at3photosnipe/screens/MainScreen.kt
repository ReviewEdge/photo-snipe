package com.example.at3photosnipe.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.MyDateUtils
import com.example.at3photosnipe.Player
import com.example.at3photosnipe.data.Snipe
import com.example.at3photosnipe.R


@Composable
fun MainGame(VM: GameViewModel, Map: () -> Unit, goToInfo: () -> Unit){

    val snipeList by VM.snipes.collectAsState()
    val playerList by VM.allPlayersList.collectAsState()

    var someSnipesExist by remember { mutableStateOf(false) }



    Column {

        Divider(color = Color(android.graphics.Color.parseColor("#93a8c9")))

        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color(android.graphics.Color.parseColor("#93a8c9")))
                .fillMaxHeight(.33f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier
                    .height(8.dp) // Adjust the value as needed
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                VM.getGameName()?.let {
                    Text(
                        modifier = Modifier.clickable(enabled = true, onClick={goToInfo()}),
                        text = it,
//                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 32.sp,
                        fontFamily = FontFamily(
                            Font(resId = R.font.poppins_bold)
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))


                Image(
                    painterResource(id = R.drawable.baseline_info_24),
                    contentDescription = null,
                    modifier = Modifier.clickable{ goToInfo() }
                )

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Scores:", modifier = Modifier.padding(8.dp), fontSize = 19.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_bold)
                    ))

                Button(
                    onClick = { Map() },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "View Map",
                        fontFamily = FontFamily(
                            Font(resId = R.font.poppins_light)
                        ))
                }

            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.Start,
//                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxWidth(),
                content = {

                    items(playerList) { player ->
                        if(player.gameInstanceId == VM.currentGameInstance!!.game_id) {
                            PlayerItem(player = player)
                        }
                    }
                }
            )

        }

        Divider(color = Color(android.graphics.Color.parseColor("#93a8c9")))


        Column () {

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxSize(),
//                reverseLayout = true
            ) {

                items(snipeList.sortedBy { snipe: Snipe -> snipe.time_of_day }) { snipe ->
                    if(snipe.gameInstanceId == VM.currentGameInstance!!.game_id) {
                        PictureCard(snipe = snipe, VM=VM)
                        someSnipesExist = true
                    }
                }


            }

            if (!someSnipesExist) {
                Spacer(modifier = Modifier.padding(16.dp))

                Text("No Snipes Yet")

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
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    )
}




@Composable
fun PictureCard(snipe: Snipe, VM: GameViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the picture
        Image(
            painter = rememberAsyncImagePainter(Uri.parse(snipe.picture_res)),
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

                // TODO: make this so that instead of getting id, it actually gets the sniper's player object

//                append("${snipe.sniper.name}")

                append("${VM.getPlayerById(snipe.sniper_id)?.name}")

            }

            append(" sniped ")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) {


                // TODO: make this so that instead of getting id, it actually gets the snipee's player object
//                append("${snipe.snipee.name}")
                append("${VM.getPlayerById(snipe.snipee_id)?.name}")

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
            text = "${MyDateUtils.formatDateStringCustom(snipe.time_of_day)}",
            fontSize = 12.sp,
//            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Divider()

    }
}

