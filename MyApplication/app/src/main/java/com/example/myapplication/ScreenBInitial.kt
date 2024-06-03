package com.example.myapplication

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ScreenBInitial {
    @Composable
    fun ScreenB1(onClick: () -> Unit={},onClick1:()->Unit,onClick2:()->Unit){
        val gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFFFC071), Color(0xFFE74F4F))
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .background(gradient), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(onClick = onClick,colors=ButtonDefaults.buttonColors(MainViewModel().background1)) {
                    Text(text = "Back", fontWeight = FontWeight.ExtraBold, fontSize =20.sp)

                }
                Spacer(modifier = Modifier.width(80.dp))
                Button(
                    onClick = onClick1,
                    colors = ButtonDefaults.buttonColors(MainViewModel().background2)
                ) {
                    Text(text = "LEADERBOARD", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                }
            }
            Image(painter = painterResource(id = R.drawable.icon), contentDescription ="icon", modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.50f))
            Box (modifier = Modifier
                .background(
                    Color.DarkGray, AbsoluteCutCornerShape(35.dp)
                )
                .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
            ){
                Text(
                    "SELECT MODE", fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,modifier = Modifier
                        .background(
                            Color(0xFFFADAAD), AbsoluteCutCornerShape(35.dp)
                        )
                        .fillMaxWidth(0.50f)
                )
            }
            Button(onClick = onClick2,colors = ButtonDefaults.buttonColors(MainViewModel().background1), modifier = Modifier.padding(top = 40.dp)
            ) {
                Text("CLASSICAL", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
