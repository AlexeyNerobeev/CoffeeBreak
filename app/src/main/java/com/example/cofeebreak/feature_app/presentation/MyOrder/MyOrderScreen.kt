package com.example.cofeebreak.feature_app.presentation.MyOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.cofeebreak.R
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.montserrat
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun MyOrderScreen(navController: NavController, vm: MyOrderVM = hiltViewModel()) {
    val state = vm.state.value
    if (state.error) {
        ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
            vm.onEvent(MyOrderEvent.ChangeError)
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Theme.colors.mainBackgroundColor)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(start = 26.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Theme.colors.backIconColor
                )
            }
            Text(
                text = stringResource(R.string.my_order),
                color = Theme.colors.oppositeColor,
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                fontFamily = roboto,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(start = 29.dp)
            )
            if(state.orderList.isEmpty()){
                CircularProgressIndicator(modifier = Modifier
                    .padding(top = 27.dp)
                    .align(Alignment.CenterHorizontally),
                    color = Theme.colors.oppositeColor)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 27.dp)
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                ) {
                    items(state.orderList) { item ->
                        Box(
                            modifier = Modifier
                                .padding(bottom = 21.dp)
                                .fillMaxWidth()
                                .background(
                                    color = Theme.colors.orderBoxBackground,
                                    shape = RoundedCornerShape(15.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(top = 18.dp)
                                    .padding(end = 7.dp)
                                    .padding(start = 25.dp)
                                    .padding(bottom = 9.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    AsyncImage(
                                        model = item.coffee_image,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(52.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 25.dp)
                                    ) {
                                        Text(
                                            text = item.name,
                                            color = Theme.colors.oppositeColor,
                                            fontFamily = roboto,
                                            fontWeight = FontWeight(500),
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = item.options,
                                            color = Theme.colors.optionsColor,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight(400),
                                            fontFamily = roboto,
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                        )
                                        Text(
                                            text = "x " + item.count.toString(),
                                            color = Theme.colors.countColor,
                                            fontFamily = roboto,
                                            fontWeight = FontWeight(500),
                                            fontSize = 12.sp,
                                            modifier = Modifier
                                                .padding(top = 5.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = item.price.toString() + " ₽",
                                    color = Theme.colors.oppositeColor,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    fontFamily = montserrat,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 33.dp)
                    .padding(start = 33.dp)
                    .padding(end = 28.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.total_amount),
                        color = Theme.colors.titleTextProfile,
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        fontSize = 12.sp
                    )
                    Text(
                        text = state.totalAmount.toString() + " ₽",
                        color = Theme.colors.oppositeColor,
                        fontWeight = FontWeight(600),
                        fontSize = 24.sp,
                        fontFamily = poppins,
                        modifier = Modifier
                            .align(Alignment.End)
                    )
                }
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = Theme.colors.nextButton,
                            shape = RoundedCornerShape(30.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Theme.colors.nextButton,
                        contentColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                       Icon(painter = painterResource(R.drawable.cart_icon),
                           tint = Color.White,
                           contentDescription = null)
                        Text(text = stringResource(R.string.next),
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 24.dp))
                    }
                }
            }
        }
    }
}