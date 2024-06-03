package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
class PlayerInformation {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun ScreenBPlayerInformation(onClick1:()->Unit={})
    {   val viewModel:MainViewModel= viewModel()
        val gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFFCC36A), Color(0xFFFE606E))
        )
        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }
        Column(modifier = Modifier.fillMaxSize().background(gradient)) {

        }
         AnimatedVisibility(visibleState = state){
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .background(gradient)
                    .fillMaxSize(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .background(
                            Color.DarkGray, AbsoluteCutCornerShape(35.dp)
                        )
                        .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
                ) {
                    Text(
                        "PLAYER INFORMATION", fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center, modifier = Modifier
                            .background(
                                Color(0xFFFADAAD), AbsoluteCutCornerShape(35.dp)
                            )
                            .fillMaxWidth(0.60f)
                    )
                }
                Spacer(modifier = Modifier.height(35.dp))
                Row(modifier = Modifier.padding(top = 70.dp, start = 25.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.image_player1),
                        contentDescription = "player1"
                    )
                    TextField(
                        value = viewModel.player1,
                        onValueChange = { new -> viewModel.player1 = new },
                        label = { Text("Name", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier
                            .fillMaxHeight(0.17f)
                            .align(Alignment.Bottom)
                            .padding(start = 25.dp, end = 25.dp),
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MainViewModel().buttonColor
                        ),
                        colors = TextFieldDefaults.textFieldColors(containerColor = MainViewModel().background2)
                    )

                }
                Spacer(modifier = Modifier.height(25.dp))
                Row(modifier = Modifier.padding(start = 25.dp, top = 25.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.image_player2),
                        contentDescription = "player2"
                    )
                    TextField(
                        value = viewModel.player2,
                        onValueChange = { new -> viewModel.player2 = new },
                        label = { Text("Name", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier
                            .fillMaxHeight(0.22f)
                            .align(Alignment.Bottom)
                            .padding(start = 25.dp, end = 25.dp),
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MainViewModel().buttonColor
                        ),
                        colors = TextFieldDefaults.textFieldColors(containerColor = MainViewModel().background1)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "readyFight",
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(top = 35.dp, bottom = 35.dp)
                )
                Spacer(modifier = Modifier.height(7.dp))
                Button(
                    onClick = onClick1,
                    modifier = Modifier.padding(7.dp),
                    colors = ButtonDefaults.buttonColors(MainViewModel().background1)
                ) {
                    Text(
                        "START",
                        color = MainViewModel().buttonColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

            }
        }
    }

}

@Preview
@Composable
fun Previewhg(){
    PlayerInformation().ScreenBPlayerInformation()
}
