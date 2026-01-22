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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.feature_app.domain.model.Coffee
import com.example.cofeebreak.ui.theme.RewardLine
import com.example.cofeebreak.ui.theme.Theme

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
                            fontWeight = FontWeight(500))
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
                            fontWeight = FontWeight(500))
                    }
                }
            }
            Canvas(modifier = Modifier
                .padding(top = 16.dp)
                .height(1.dp)
                .fillMaxWidth()) {
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.0f,
                    color = RewardLine
                )
            }
        }
    }
}