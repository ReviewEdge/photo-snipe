package com.example.at3photosnipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.at3photosnipe.screens.ConfirmSnipe
import com.example.at3photosnipe.screens.CreateGame
import com.example.at3photosnipe.screens.JoinGame
import com.example.at3photosnipe.screens.MainGame
import com.example.at3photosnipe.screens.NoGameScreen
import com.example.at3photosnipe.screens.Screens
import com.example.at3photosnipe.screens.ShowMap
import com.example.at3photosnipe.screens.Snipe
import com.example.at3photosnipe.screens.StartNewGame
import com.example.at3photosnipe.screens.canGoBack
import com.example.at3photosnipe.ui.theme.AT3PhotoSnipeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoSnipeApp() {
    val navController = rememberNavController()
    val currentScreenHandler by navController.currentBackStackEntryAsState()
    val isNotStartScreen = currentScreenHandler?.destination?.route != Screens.SelectGame.route

    var cameraEnable = false

    if (currentScreenHandler?.destination?.route == Screens.Snipe.route ||
        currentScreenHandler?.destination?.route == Screens.Confirm.route ||
        currentScreenHandler?.destination?.route == Screens.Main.route ||
        currentScreenHandler?.destination?.route == Screens.Map.route)
    {
         cameraEnable = true
    }

    Scaffold(
        topBar = {
            if (isNotStartScreen) {
                MyTopBar(
                    canGoBack = canGoBack(currentScreenHandler?.destination?.route),
                    goBack = { navController.navigateUp() },
                )
            }
        },
        bottomBar = {
                MyBottomBar(
                    goCamera = {navController.navigate(Screens.Snipe.route)},
                    enabled = cameraEnable
                )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.SelectGame.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.SelectGame.route) {
                NoGameScreen()
            }
            composable(route = Screens.Snipe.route) {
                Snipe()
            }
            composable(route = Screens.Map.route) {
                ShowMap()
            }
            composable(route = Screens.CreateGame.route) {
                CreateGame()
            }
            composable(route = Screens.Main.route) {
                MainGame()
            }
            composable(route = Screens.Confirm.route) {
                ConfirmSnipe()
            }
            composable(route = Screens.NewGame.route) {
                StartNewGame()
            }
            composable(route = Screens.JoinGame.route) {
                JoinGame()
            }
        }
    }
}

@Composable
fun MyTopBar(canGoBack: Boolean,
             goBack: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        if (canGoBack){
            IconButton(onClick = { goBack()}) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = null
                )
            }
        }
        Text(text = "temp")
    }
}

@Composable
fun MyBottomBar(goCamera: () -> Unit, enabled: Boolean, modifier: Modifier = Modifier.fillMaxWidth()){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        IconButton(onClick = { goCamera()}, enabled = enabled
        ) {
            Icon(painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                contentDescription = null
            )
        }
    }
}