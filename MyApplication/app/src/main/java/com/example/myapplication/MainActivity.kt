package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val n: Int = 6
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenA
                )
                {
                    composable<ScreenA> {
                        PlayScreen(onClick = { navController.navigate(ScreenB) })
                    }
                    composable<ScreenB> {
                        var a = PlayerInformation()
                        val viewModel: MainViewModel = viewModel()
                        a.ScreenBPlayerInformation(onClick1 = {
                            navController.navigate(
                                ScreenB1(
                                    name1 = viewModel.player1,
                                    name2 = viewModel.player2
                                )
                            )
                        })
                    }
                    composable<ScreenB1> {
                        var a = ScreenBInitial()

                        val args = it.toRoute<ScreenB1>()
                        a.ScreenB1(
                            onClick = {navController.navigate(ScreenA)},
                            onClick1 = { navController.navigate(LeaderBoard) },
                            onClick2 = {
                                navController.navigate(
                                    ScreenC(
                                        name1 = args.name2,
                                        name2 = args.name1
                                    )
                                )
                                {mainViewModel.buttonState.forEach { it.value = 0 }
                                    mainViewModel.buttonStateColor.forEach { it.value = mainViewModel.buttonColor }
                                    mainViewModel.resultBG = mainViewModel.background1
                                    mainViewModel.numberClicked = 0
                                    mainViewModel.Score1 = 0
                                    mainViewModel.Score2 = 0}
                            })
                    }
                    composable<LeaderBoard> {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color(0xFF6ADFFC), Color(0xFFFFA2F0))
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .background(gradient)
                        ) {


                                Text(
                                    text = "LEADERBOARD",
                                    fontSize = 30.sp, // Increase font size for heading
                                    fontWeight = FontWeight.Bold,// Apply bold font weight
                                    modifier = Modifier.padding(bottom = 8.dp).align(Alignment.CenterHorizontally) // Add bottom padding for spacing
                                )
                                Row( modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Player Name", fontSize = 23.sp, textAlign = TextAlign.Left, modifier = Modifier.weight(1f), color = Color.DarkGray)
                                    Text(text = "Matches Won", fontSize = 23.sp, textAlign = TextAlign.Right, modifier = Modifier.weight(1f), color = Color.DarkGray)
                                }
                                LazyColumn {
                                    items(mainViewModel.players.size) { i ->
                                        Row( modifier = Modifier.padding(
                                                vertical = 8.dp,
                                                horizontal = 16.dp)
                                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                            Text(
                                                text = mainViewModel.players[i], fontSize = 20.sp, textAlign = TextAlign.Start, modifier =Modifier.weight(1f))
                                            Text(text = mainViewModel.playerSWin[i].toString(), fontSize = 20.sp, textAlign = TextAlign.End, modifier = Modifier.weight(1f))
                                        }
                                    }
                                }
                            }


                    }

                    composable<ScreenC> {
                        var b = MainMakeGrid()
                        var c = MainViewModel()
                        //c.winners
                        val args = it.toRoute<ScreenC>()

                        Box(modifier = Modifier) {
                            b.MakeGrid(
                                n = n,
                                viewModel = mainViewModel,
                                name1 = args.name1,
                                name2 = args.name2,
                                onclick5 = { navController.navigate(ScreenA) },
                                onClick4 = {
                                    navController.navigate(
                                        ScreenB1(
                                            name1 = args.name1,
                                            name2 = args.name2
                                        )
                                    )
                                })
                        }
                    }

                }//fourth closing braces --> MyApplicationTheme
            }//third closing braces --> set content method
        }//second closing braces -->fun on create
    }//first closing braces --> Main activity class
}
@Composable
fun PlayScreen(onClick:()->Unit){
    var showDialog by remember {
        mutableStateOf(false)
    }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFC071), Color(0xFFE74F4F))
    )
    Column (
        Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(gradient),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment =Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.color), contentDescription ="title" ,Modifier.padding(vertical =
        60.dp))
        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "image",
            Modifier
                .fillMaxWidth(0.85f)
                .padding(top = 140.dp))
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 75.dp))
            {var ba:Color = Color(0xFF58C7FA)
                Button(
                    onClick = onClick, modifier = Modifier
                        .padding(15.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    colors = ButtonDefaults.buttonColors(ba)
                ) { Text("PLAY",style = TextStyle(fontSize = 35.sp), fontWeight = FontWeight.ExtraBold) }
                Button(
                    onClick = {showDialog = true}, modifier = Modifier
                        .padding(15.dp)
                        .clip(
                            shape = RoundedCornerShape(100)
                        ),
                    colors = ButtonDefaults.buttonColors(Color.Red))
                { Text(text = "?", style = TextStyle(fontSize = 35.sp), fontWeight = FontWeight.ExtraBold, color = Color.White) }
            }
        if (showDialog ==true){
            AlertDialog(
                onDismissRequest = { showDialog = false }, confirmButton = {
                    Button(onClick = { showDialog=false }, colors = ButtonDefaults.buttonColors(MainViewModel().background1)) { Text(text = "OKAY!")}},
                title = {
                    Text(
                        text = "HOW TO PLAY"
                    )
                }, text = { Text(text = "1st Turn of each player: Players can choose any tile on the grid on this turn only. Clicking a tile assigns your colour to it and awards you 3 points on that tile." +
                        "Subsequent Turns: After the first turn, players can only click on tiles that already have their own colour. Clicking a tile with your colour adds 1 point to that tile.The background colour indicates the next player." +
                        "The propagation goes on,Players take turns clicking on tiles and the objective is to eliminate your opponent's colour entirely from the screen.")}
            )
        }
    }

}

@Serializable
object ScreenA
@Serializable
object ScreenB
@Serializable
data class ScreenB1(var name1: String="player1",var name2: String="player2")
@Serializable
object LeaderBoard
@Serializable
data class ScreenC(var name1:String="player1",var name2:String="player2")
