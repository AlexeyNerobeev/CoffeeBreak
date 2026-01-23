package com.example.cofeebreak.feature_app.presentation.OrderOptions

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.dmSans
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.feature_app.domain.model.Coffee
import com.example.cofeebreak.ui.theme.RewardLine
import com.example.cofeebreak.ui.theme.Theme
import io.github.jan.supabase.realtime.Column

@Composable
fun OrderOptionsScreen(
    navController: NavController,
    imageUrl: String,
    vm: OrderOptionsVM = hiltViewModel()
) {
    val state = vm.state.value
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
            Row(
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(start = 26.dp)
                    .padding(end = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back_icon),
                        contentDescription = null,
                        tint = Theme.colors.backIconColor
                    )
                }
                Text(
                    text = stringResource(R.string.order),
                    color = Theme.colors.oppositeColor,
                    fontWeight = FontWeight(500),
                    fontFamily = roboto,
                    fontSize = 16.sp
                )
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.cart_icon),
                        contentDescription = null,
                        tint = Theme.colors.backIconColor
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 19.dp)
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.coffeeBackground),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(vertical = 12.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .padding(start = 35.dp)
                    .padding(end = 48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.americano),
                    color = Theme.colors.backIconColor,
                    fontWeight = FontWeight(500),
                    fontFamily = dmSans,
                    fontSize = 14.sp
                )
                Box(
                    modifier = Modifier
                        .size(73.dp, 29.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .border(
                            shape = RoundedCornerShape(50.dp),
                            width = 1.dp,
                            color = Theme.colors.orderOptionsBoxColor
                        )
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "-",
                            color = Theme.colors.backIconColor,
                            fontFamily = dmSans,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            modifier = Modifier
                                .clickable {
                                    vm.onEvent(OrderOptionsEvent.MinusCoffeeCount)
                                })
                        Text(
                            text = state.coffeeCount.toString(),
                            color = Theme.colors.backIconColor,
                            fontFamily = dmSans,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500)
                        )
                        Text(
                            text = "+",
                            color = Theme.colors.backIconColor,
                            fontFamily = dmSans,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            modifier = Modifier
                                .clickable {
                                    vm.onEvent(OrderOptionsEvent.PlusCoffeeCount)
                                })
                    }
                }
            }
            Canvas(
                Modifier
                    .padding(top = 13.dp)
                    .height(1.dp)
                    .fillMaxWidth()
            ) {
                drawLine(
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = size.width, y = 0f),
                    color = RewardLine,
                    strokeWidth = 1.0f
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 17.dp)
                    .padding(start = 35.dp)
                    .padding(end = 48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.ristretto),
                    color = Theme.colors.backIconColor,
                    fontWeight = FontWeight(500),
                    fontFamily = dmSans,
                    fontSize = 14.sp
                )
                Row {
                    Box(
                        modifier = Modifier
                            .size(73.dp, 29.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .border(
                                shape = RoundedCornerShape(50.dp),
                                width = 1.dp,
                                color = Theme.colors.orderOptionsBoxColor
                            )
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.one),
                            color = Theme.colors.backIconColor,
                            fontFamily = dmSans,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(73.dp, 29.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .border(
                                shape = RoundedCornerShape(50.dp),
                                width = 1.dp,
                                color = Theme.colors.orderOptionsBoxColor
                            )
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.two),
                            color = Theme.colors.backIconColor,
                            fontFamily = dmSans,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500)
                        )
                    }
                }
            }
            Canvas(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(1.dp)
                    .fillMaxWidth()
            ) {
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.0f,
                    color = RewardLine
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 11.dp)
                    .padding(start = 35.dp)
                    .padding(end = 53.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.on_site_takeaway),
                    color = Theme.colors.backIconColor,
                    fontWeight = FontWeight(500),
                    fontFamily = dmSans,
                    fontSize = 14.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.on_site_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.alternativeWhite)
                        )
                    }
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(start = 31.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.with_you_icon),
                            contentDescription = null,
                            tint = Theme.colors.backIconColor
                        )
                    }
                }
            }
            Canvas(
                modifier = Modifier
                    .padding(top = 17.dp)
                    .fillMaxWidth()
                    .height(1.dp)
            ) {
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    color = RewardLine
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(start = 35.dp)
                    .padding(end = 48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.volume_ml),
                    color = Theme.colors.backIconColor,
                    fontWeight = FontWeight(500),
                    fontFamily = dmSans,
                    fontSize = 14.sp
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(R.drawable.volume_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.alternativeWhite),
                            modifier = Modifier
                                .size(17.dp, 22.dp)
                        )
                        Text(
                            text = "250",
                            color = colorResource(R.color.alternativeWhite),
                            fontFamily = dmSans,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 22.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.volume_icon),
                            contentDescription = null,
                            tint = Theme.colors.backIconColor,
                            modifier = Modifier
                                .size(24.dp, 31.dp)
                        )
                        Text(
                            text = "350",
                            color = Theme.colors.backIconColor,
                            fontFamily = dmSans,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 25.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.volume_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.alternativeWhite),
                            modifier = Modifier
                                .size(29.dp, 38.dp)
                        )
                        Text(
                            text = "250",
                            color = colorResource(R.color.alternativeWhite),
                            fontFamily = dmSans,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
                }
            }
            Canvas(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .fillMaxWidth()
                    .height(1.dp)
            ) {
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    color = RewardLine
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(start = 35.dp)
                    .padding(end = 31.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.prepare_by_a_certain_time_today),
                    color = Theme.colors.backIconColor,
                    fontFamily = dmSans,
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp
                )
                Column(horizontalAlignment = Alignment.End) {
                    Switch(
                        checked = state.switch,
                        onCheckedChange = {
                            vm.onEvent(OrderOptionsEvent.Switch)
                        },
                        modifier = Modifier
                            .size(51.dp, 31.dp),
                        colors = SwitchDefaults.colors(
                            checkedBorderColor = Theme.colors.switchColor,
                            uncheckedTrackColor = colorResource(R.color.Gray),
                            checkedTrackColor = Theme.colors.switchColor,
                            uncheckedBorderColor = Theme.colors.switchColor
                        )
                    )
                    if (state.switch) {
                        Box(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(86.dp, 36.dp)
                                .background(
                                    color = Theme.colors.dateBoxColor,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "18 : 10",
                                color = Theme.colors.oppositeColor,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(400),
                                fontSize = 22.sp,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(vertical = 3.dp)
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 32.dp)
                    .padding(end = 28.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colorResource(R.color.MainColor),
                        shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.MainColor),
                    contentColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .padding(end = 13.dp)
                        .padding(vertical = 13.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.filter_icon),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(
                            stringResource(R.string.coffee_lovers_designer),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            fontFamily = roboto,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.more_icon),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 23.dp)
                    .padding(start = 30.dp)
                    .padding(end = 34.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.total_amount),
                    color = Theme.colors.backIconColor,
                    fontFamily = roboto,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp
                )
                Text(
                    text = "100 ₽",
                    color = Theme.colors.totalPriceColor,
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600)
                )
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 30.dp)
                    .padding(end = 29.dp)
                    .padding(bottom = 19.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Theme.colors.nextButton
                )
            ) {
                Text(
                    text = stringResource(R.string.next),
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    fontFamily = roboto,
                    fontSize = 14.sp
                )
            }
        }
    }
}