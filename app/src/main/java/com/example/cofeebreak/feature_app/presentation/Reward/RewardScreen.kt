package com.example.cofeebreak.feature_app.presentation.Reward

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.BottomNavigationBar
import com.example.cofeebreak.common.dmSans
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.RewardLine
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun RewardScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Theme.colors.mainBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.reward),
                    color = Theme.colors.oppositeColor,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    fontFamily = roboto,
                    modifier = Modifier
                        .padding(top = 21.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Box(modifier = Modifier
                    .padding(top = 31.dp)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.AlternativeBlack),
                        shape = RoundedCornerShape(12.dp)
                    )) {
                    Column(modifier = Modifier
                        .padding(top = 14.dp)
                        .padding(bottom = 49.dp)
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()) {
                        Row(modifier = Modifier
                            .padding(end = 5.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(R.string.loyalty_card),
                                color = colorResource(R.color.alternativeWhite),
                                fontSize = 14.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(500)
                            )
                            Text(text = "4 / 6",
                                color = colorResource(R.color.alternativeWhite),
                                fontWeight = FontWeight(500),
                                fontFamily = dmSans,
                                fontSize = 14.sp)
                        }
                        Row(modifier = Modifier
                            .padding(top = 25.dp)
                            .padding(start = 4.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Row {
                                Icon(painter = painterResource(R.drawable.active_coffee_cup),
                                    contentDescription = null,
                                    tint = Color.Unspecified)
                                Icon(painter = painterResource(R.drawable.active_coffee_cup),
                                    contentDescription = null,
                                    tint = Color.Unspecified)
                                Icon(painter = painterResource(R.drawable.active_coffee_cup),
                                    contentDescription = null,
                                    tint = Color.Unspecified)
                                Icon(painter = painterResource(R.drawable.active_coffee_cup),
                                    contentDescription = null,
                                    tint = Color.Unspecified)
                                Icon(painter = painterResource(R.drawable.active_coffee_cup),
                                    contentDescription = null,
                                    tint = colorResource(R.color.Gray))
                                Icon(painter = painterResource(R.drawable.active_coffee_cup),
                                    contentDescription = null,
                                    tint = colorResource(R.color.Gray))
                            }
                            Text(text = "16%",
                                color = colorResource(R.color.MainColor),
                                fontWeight = FontWeight(500),
                                fontFamily = poppins,
                                fontSize = 25.sp
                            )
                        }
                    }
                }
                Box(modifier = Modifier
                    .padding(top = 31.dp)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.AlternativeBlack),
                        shape = RoundedCornerShape(12.dp)
                    )) {
                    Icon(painter = painterResource(R.drawable.coffee_beans_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .align(Alignment.BottomEnd))
                    Row(modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(vertical = 25.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically){
                        Column {
                            Text(text = stringResource(R.string.my_points),
                                color = colorResource(R.color.alternativeWhite),
                                fontFamily = dmSans,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500))
                            Text(text = "240",
                                color = colorResource(R.color.alternativeWhite),
                                fontWeight = FontWeight(500),
                                fontSize = 25.sp,
                                fontFamily = poppins,
                                modifier = Modifier
                                    .padding(top = 2.dp))
                        }
                        Box(modifier = Modifier
                            .clickable{

                            }){
                            Icon(painter = painterResource(R.drawable.pay_points_button),
                                contentDescription = null,
                                tint = Color.Unspecified)
                        }
                    }
                }
                Text(text = stringResource(R.string.points_accrual_history),
                    color = colorResource(R.color.AlternativeBlack),
                    fontFamily = dmSans,
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 7.dp)
                        .padding(start = 5.dp))
                LazyColumn(modifier = Modifier
                    .padding(top = 25.dp)
                    .padding(start = 31.dp)
                    .padding(end = 24.dp)
                    .fillMaxWidth()) {
                    item {
                        Column(modifier = Modifier
                            .fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = stringResource(R.string.americano),
                                        color = colorResource(R.color.AlternativeBlack),
                                        fontFamily = roboto,
                                        fontWeight = FontWeight(500),
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        text = stringResource(R.string.june_24),
                                        color = Theme.colors.rewardHistoryColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight(400),
                                        fontFamily = roboto,
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                }
                                Text(
                                    text = stringResource(R.string.points_12),
                                    color = colorResource(R.color.AlternativeBlack),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = poppins,
                                )
                            }
                            Canvas(Modifier
                                .padding(top = 23.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                            ) {
                                val width = size.width
                                drawLine(
                                    start = Offset(x= 0f, y = 0f),
                                    end = Offset(x = width, y = 0f),
                                    color = RewardLine,
                                    strokeWidth = 1.0f
                                )
                            }
                        }
                    }
                    item {
                        Column(modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = stringResource(R.string.latte),
                                        color = colorResource(R.color.AlternativeBlack),
                                        fontFamily = roboto,
                                        fontWeight = FontWeight(500),
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        text = stringResource(R.string.june_22),
                                        color = Theme.colors.rewardHistoryColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight(400),
                                        fontFamily = roboto,
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                }
                                Text(
                                    text = stringResource(R.string.points_12),
                                    color = colorResource(R.color.AlternativeBlack),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = poppins,
                                )
                            }
                            Canvas(Modifier
                                .padding(top = 23.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                            ) {
                                val width = size.width
                                drawLine(
                                    start = Offset(x= 0f, y = 0f),
                                    end = Offset(x = width, y = 0f),
                                    color = RewardLine,
                                    strokeWidth = 1.0f
                                )
                            }
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){
            BottomNavigationBar(navController, Navigation.RewardScreen)
        }
    }
}