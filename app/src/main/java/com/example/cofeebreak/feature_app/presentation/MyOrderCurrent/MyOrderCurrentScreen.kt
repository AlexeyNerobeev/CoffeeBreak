package com.example.cofeebreak.feature_app.presentation.MyOrderCurrent

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
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
import com.example.cofeebreak.common.BottomNavigationBar
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.RewardLine
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun MyOrderCurrentScreen(navController: NavController, vm: MyOrderCurrentVM = hiltViewModel()) {
    val state = vm.state.value
    if (state.error) {
        ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
            vm.onEvent(MyOrderCurrentEvent.ChangeError)
        }
    }
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
            Text(
                text = stringResource(R.string.my_order),
                color = Theme.colors.oppositeColor,
                fontFamily = roboto,
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 21.dp)
                    .align(Alignment.CenterHorizontally)
            )
            val tabs = listOf(
                stringResource(R.string.сurrent),
                stringResource(R.string.history)
            )
            TabRow(
                selectedTabIndex = state.select,
                containerColor = Theme.colors.mainBackgroundColor,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[state.select]),
                        color = Theme.colors.oppositeColor,
                        height = 2.dp
                    )
                },
                divider = {},
                modifier = Modifier
                    .padding(top = 31.dp)
                    .padding(horizontal = 57.dp)
                    .fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = state.select == index,
                        onClick = {
                            vm.onEvent(MyOrderCurrentEvent.OnTabSelected(index))
                        },
                        selectedContentColor = Theme.colors.oppositeColor,
                        unselectedContentColor = Theme.colors.unSelectOrder,
                        text = {
                            Text(
                                text = title,
                                fontFamily = roboto,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            ) {
                drawLine(
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    color = RewardLine
                )
            }
            if (state.load) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 21.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Theme.colors.oppositeColor
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 21.dp)
                        .padding(start = 22.dp)
                        .padding(end = 27.dp)
                        .fillMaxWidth()
                ) {
                    if (state.select == 0) {
                        items(state.currentOrderList) { item ->
                            Column(
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        AsyncImage(
                                            model = item.coffee_image,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(50.dp),
                                            contentScale = ContentScale.FillWidth
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 18.dp)
                                        ) {
                                            Text(
                                                text = item.name,
                                                color = colorResource(R.color.AlternativeBlack),
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(400),
                                                fontFamily = roboto
                                            )
                                            Row(
                                                modifier = Modifier
                                                    .padding(top = 7.dp),
                                                verticalAlignment = Alignment.Bottom
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.hidtory_location_icon),
                                                    contentDescription = null,
                                                    tint = Theme.colors.historyLocationIcon
                                                )
                                                Text(
                                                    text = state.address,
                                                    fontFamily = poppins,
                                                    fontWeight = FontWeight(500),
                                                    fontSize = 10.sp,
                                                    modifier = Modifier
                                                        .padding(start = 4.dp),
                                                    color = Theme.colors.alternativeBlack
                                                )
                                            }
                                            Text(
                                                text = vm.formatOrderDate(
                                                    createdAt = item.created_at,
                                                    timeTo = item.time_to
                                                ),
                                                color = Theme.colors.rewardHistoryColor,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight(500),
                                                fontFamily = poppins,
                                                modifier = Modifier
                                                    .padding(top = 7.dp)
                                            )
                                        }
                                    }
                                    Text(
                                        text = item.price.toString() + " ₽",
                                        color = colorResource(R.color.AlternativeBlack),
                                        fontFamily = poppins,
                                        fontWeight = FontWeight(500),
                                        fontSize = 16.sp
                                    )
                                }
                                Canvas(
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                        .padding(start = 10.dp)
                                        .padding(end = 4.dp)
                                        .fillMaxWidth()
                                        .height(1.dp)
                                ) {
                                    drawLine(
                                        start = Offset(0f, 0f),
                                        end = Offset(size.width, 0f),
                                        color = RewardLine
                                    )
                                }
                            }
                        }
                    } else {
                        items(state.historyOrderList) { item ->
                            Column(
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        AsyncImage(
                                            model = item.coffee_image,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(50.dp),
                                            contentScale = ContentScale.FillWidth
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 18.dp)
                                        ) {
                                            Text(
                                                text = item.name,
                                                color = colorResource(R.color.AlternativeBlack),
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(400),
                                                fontFamily = roboto
                                            )
                                            Row(
                                                modifier = Modifier
                                                    .padding(top = 7.dp),
                                                verticalAlignment = Alignment.Bottom
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.hidtory_location_icon),
                                                    contentDescription = null,
                                                    tint = Theme.colors.historyLocationIcon
                                                )
                                                Text(
                                                    text = state.address,
                                                    fontFamily = poppins,
                                                    fontWeight = FontWeight(500),
                                                    fontSize = 10.sp,
                                                    modifier = Modifier
                                                        .padding(start = 4.dp),
                                                    color = Theme.colors.alternativeBlack
                                                )
                                            }
                                            Text(
                                                text = vm.formatOrderDate(
                                                    createdAt = item.created_at,
                                                    timeTo = item.time_to
                                                ),
                                                color = Theme.colors.rewardHistoryColor,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight(500),
                                                fontFamily = poppins,
                                                modifier = Modifier
                                                    .padding(top = 7.dp)
                                            )
                                        }
                                    }
                                    Column {
                                        Text(
                                            text = item.price.toString() + " ₽",
                                            color = colorResource(R.color.AlternativeBlack),
                                            fontFamily = poppins,
                                            fontWeight = FontWeight(500),
                                            fontSize = 16.sp
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(76.dp, 32.dp)
                                                .padding(top = 7.dp)
                                                .background(color = colorResource(R.color.AlternativeBlack),
                                                    shape = RoundedCornerShape(50.dp))
                                                .clickable{

                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = stringResource(R.string.to_order),
                                                color = Color.White,
                                                fontWeight = FontWeight(500),
                                                fontFamily = poppins,
                                                fontSize = 10.sp)
                                        }
                                    }
                                }
                                Canvas(
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                        .padding(start = 10.dp)
                                        .padding(end = 4.dp)
                                        .fillMaxWidth()
                                        .height(1.dp)
                                ) {
                                    drawLine(
                                        start = Offset(0f, 0f),
                                        end = Offset(size.width, 0f),
                                        color = RewardLine
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){
            BottomNavigationBar(navController, Navigation.MyOrderCurrentScreen)
        }
    }
}