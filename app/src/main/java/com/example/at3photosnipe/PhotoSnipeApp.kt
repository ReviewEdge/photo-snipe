package com.example.at3photosnipe

import android.os.Build
import androidx.annotation.RequiresApi
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.at3photosnipe.screens.ConfirmSnipe
import com.example.at3photosnipe.screens.CreateGame
import com.example.at3photosnipe.screens.GameInfo
import com.example.at3photosnipe.screens.JoinGame
import com.example.at3photosnipe.screens.MainGame
import com.example.at3photosnipe.screens.NoGameScreen
import com.example.at3photosnipe.screens.Screens
import com.example.at3photosnipe.screens.ShowMap
//import com.example.at3photosnipe.screens.
import com.example.at3photosnipe.screens.StartNewGame
import com.example.at3photosnipe.screens.canGoBack
import com.example.at3photosnipe.GameViewModel
import com.example.at3photosnipe.ui.theme.AT3PhotoSnipeTheme

public var isCameraScreen = false

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun PhotoSnipeApp(VM: GameViewModel) {
fun PhotoSnipeApp() {
    val VM : GameViewModel = GameViewModel.getInstance()

    val navController = rememberNavController()
    val currentScreenHandler by navController.currentBackStackEntryAsState()
    val isNotStartScreen = currentScreenHandler?.destination?.route != Screens.SelectGame.route
    var isCameraScreen = currentScreenHandler?.destination?.route == Screens.CameraView.route

    var cameraEnable = false

    if (currentScreenHandler?.destination?.route == Screens.CameraView.route ||
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
                    selectGame = { navController.navigate(Screens.SelectGame.route) },
                    goBack = { navController.navigateUp() },
                    goToMain = { navController.navigate(Screens.Main.route) }
                )
            }
        },
        bottomBar = {
                MyBottomBar(
                    goCamera = {navController.navigate(Screens.CameraView.route)},
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
                NoGameScreen(CreateGame = {
                    navController.navigate(Screens.NewGame.route)
                }, JoinGame = {
                    navController.navigate(Screens.JoinGame.route)
                })
            }
            composable(route = Screens.NewGame.route) {
                StartNewGame(VM=VM, CreateGame = {
                    navController.navigate(Screens.GameInfo.route)
                })
            }
            composable(route = Screens.GameInfo.route) {
                GameInfo(VM=VM
//                    CreateGame = {
//                    navController.navigate(Screens.GameInfo.route)
//                }
                )
            }
            composable(route = Screens.CameraView.route) {
                CreateGame(StartGame = {
                    navController.navigate(Screens.Main.route)
                })
            }
            composable(route = Screens.JoinGame.route) {
                JoinGame(VM=VM, Join = {
                    navController.navigate(Screens.Main.route)
                })
            }
            composable(route = Screens.Main.route) {
                MainGame(VM=VM,
                    Map = {
                    navController.navigate(Screens.Map.route)
                    },
                    goToInfo = {
                        navController.navigate(Screens.GameInfo.route)
                    }
                )
            }
            composable(route = Screens.CameraView.route) {
//                Snipe(CameraView = {
//                    navController.navigate(Screens.Confirm.route)
//                })
            }
            composable(route = Screens.Confirm.route) {
                ConfirmSnipe(Confirm = {
                    navController.navigate(Screens.Main.route)
                })
            }
            composable(route = Screens.Map.route) {
                ShowMap()
            }
        }
    }

    fun getCameraScreen(): () -> Boolean {
        return {isCameraScreen}
    }
}

@Composable
fun MyTopBar(canGoBack: Boolean,
             goBack: () -> Unit,
             goToMain: () -> Unit,
             selectGame: () -> Unit,
){
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
//        IconButton(onClick = {
//            selectGame()
//        }) {
//            Icon(painter = painterResource(id = R.drawable.baseline_fiber_new_24), contentDescription = null)
//        }
        Button(onClick = {
            selectGame()
        }) {
            Text(text = "Change Game")
        }
        Button(onClick = {
            goToMain()
        }) {
            Text(text = "Stream")
        }
    }
}

@Composable
fun MyBottomBar(goCamera: () -> Unit, enabled: Boolean, modifier: Modifier = Modifier.fillMaxWidth()){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){

        val lContext = LocalContext.current

        IconButton(onClick = {
            lContext.startActivity(Intent(lContext, CameraActivity::class.java))
                             }, enabled = enabled
        ) {
            Icon(painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                contentDescription = null
            )
        }
    }
}