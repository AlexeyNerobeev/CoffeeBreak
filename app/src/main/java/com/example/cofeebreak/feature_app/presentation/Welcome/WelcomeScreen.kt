package com.example.cofeebreak.feature_app.presentation.Welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cofeebreak.R
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.redressed

@Preview
@Composable
fun prevWelcomeScreen(){
    WelcomeScreen()
}

@Composable
fun WelcomeScreen() {
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.MainColor))){
                Column(modifier = Modifier
                    .padding(top = 63.dp)
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 46.dp)
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(painter = painterResource(R.drawable.cup_of_coffee_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(98.dp))
                    Text(text = "Coffee break",
                        fontSize = 64.sp,
                        fontFamily = redressed,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 54.dp)
                    )
                }
            }
            Text(text = "Почувствуй себя бариста!",
                color = Color.Black,
                fontFamily = poppins,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .padding(horizontal = 68.dp)
            )
            Text(text = "Любой кофе под ваш заказ",
                fontFamily = poppins,
                fontSize = 18.sp,
                color = colorResource(R.color.Gray))
            Row(modifier = Modifier
                .padding(top = 43.dp)){
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .height(10.dp)
                    .width(33.dp)
                    .background(colorResource(R.color.MainColor)))
                Box(modifier = Modifier
                    .padding(start = 10.dp)
                    .clip(CircleShape)
                    .size(10.dp)
                    .background(colorResource(R.color.Gray)))
                Box(modifier = Modifier
                    .padding(start = 10.dp)
                    .clip(CircleShape)
                    .size(10.dp)
                    .background(colorResource(R.color.Gray)))
            }
        }
    }
}