package com.example.cofeebreak.feature_app.presentation.Startup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.redressed
import kotlinx.coroutines.delay

@Preview
@Composable
fun PrevStartup(){
    StartupScreen(rememberNavController())
}

@Composable
fun StartupScreen(navController: NavController) {
    LaunchedEffect(key1 = null) {
        delay(1500)
        navController.navigate(Navigation.MenuScreen)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center) {
        Image(painter = painterResource(R.drawable.startup_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(modifier = Modifier
            .padding(horizontal = 40.dp)
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
                    .padding(top = 71.dp)
            )
        }
    }
}