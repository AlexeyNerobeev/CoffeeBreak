package com.example.cofeebreak.feature_app.presentation.MyOrder

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
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
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.dmSans
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
                    navController.navigate(Navigation.MenuScreen)
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
            if (state.orderList.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 27.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Theme.colors.oppositeColor
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 27.dp)
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                ) {
                    items(
                        state.orderList,
                        key = { it.id }) { item ->
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
                        vm.onEvent(MyOrderEvent.Pay)
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            color = Theme.colors.nextButton,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Theme.colors.nextButton,
                        contentColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.cart_icon),
                            tint = Color.White,
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(R.string.next),
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 24.dp)
                        )
                    }
                }
            }
        }
        if (state.pay) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(Theme.colors.mainBackgroundColor)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 35.dp)
                            .padding(start = 32.dp)
                            .padding(end = 28.dp)
                            .padding(bottom = 33.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.payment_for_the_order),
                            color = Theme.colors.backIconColor,
                            fontWeight = FontWeight(500),
                            fontFamily = poppins,
                            fontSize = 20.sp
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 75.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(47.dp)
                                    .background(
                                        color = Theme.colors.orderBoxBackground,
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.cart_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.oppositeColor,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 24.dp)
                            ) {
                                Text(
                                    text = state.userName + "\n",
                                    color = Theme.colors.oppositeColor,
                                    fontWeight = FontWeight(500),
                                    fontFamily = roboto,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = stringResource(R.string.coffee_shop_coffee_break) + "\n"
                                            + state.address,
                                    color = Theme.colors.orderAddressColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight(400),
                                    fontFamily = montserrat
                                )

                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 46.dp)
                                .fillMaxWidth()
                                .background(
                                    color = Theme.colors.orderBoxBackground,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(start = 21.dp)
                                    .padding(end = 15.dp)
                                    .padding(vertical = 18.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .border(
                                                width = 1.dp,
                                                shape = CircleShape,
                                                color = Theme.colors.backIconColor
                                            )
                                            .clickable {
                                                vm.onEvent(MyOrderEvent.SelectSbp)
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (state.selectSbp)
                                            Box(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        color = Theme.colors.backIconColor,
                                                        shape = CircleShape
                                                    )
                                            )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 18.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.payment_online),
                                            color = Theme.colors.oppositeColor,
                                            fontWeight = FontWeight(500),
                                            fontFamily = dmSans,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "СБП",
                                            color = Theme.colors.sbpColor,
                                            fontSize = 10.sp,
                                            fontFamily = poppins,
                                            fontWeight = FontWeight(500),
                                            modifier = Modifier
                                                .padding(top = 7.dp)
                                        )
                                    }
                                }
                                Image(
                                    painter = painterResource(R.drawable.sbp_icon),
                                    contentDescription = null,
                                    modifier = Modifier.height(46.dp),
                                    contentScale = ContentScale.FillHeight
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 19.dp)
                                .fillMaxWidth()
                                .background(
                                    color = Theme.colors.orderBoxBackground,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(start = 21.dp)
                                    .padding(end = 15.dp)
                                    .padding(vertical = 18.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .border(
                                                width = 1.dp,
                                                shape = CircleShape,
                                                color = Theme.colors.backIconColor
                                            )
                                            .clickable {
                                                vm.onEvent(MyOrderEvent.SelectBankCard)
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (state.selectBankCard)
                                            Box(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        color = Theme.colors.backIconColor,
                                                        shape = CircleShape
                                                    )
                                            )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 18.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.bank_card),
                                            color = Theme.colors.oppositeColor,
                                            fontWeight = FontWeight(500),
                                            fontFamily = dmSans,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "2540 xxxx xxxx 2648",
                                            color = Theme.colors.sbpColor,
                                            fontSize = 10.sp,
                                            fontFamily = poppins,
                                            fontWeight = FontWeight(500),
                                            modifier = Modifier
                                                .padding(top = 7.dp)
                                        )
                                    }
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(R.drawable.mir_icon),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(14.dp),
                                        contentScale = ContentScale.FillHeight
                                    )
                                    Image(
                                        painter = painterResource(R.drawable.union_icon),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(26.dp),
                                        contentScale = ContentScale.FillHeight
                                    )
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(top = 98.dp)
                                .padding(bottom = 33.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(R.string.sum),
                                    color = Theme.colors.backIconColor,
                                    fontFamily = roboto,
                                    fontWeight = FontWeight(500),
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = state.totalAmount.toString() + " ₽",
                                    color = Theme.colors.backIconColor,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight(600),
                                    fontFamily = montserrat
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(top = 50.dp)
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
                                        .background(
                                            color = Theme.colors.nextButton,
                                            shape = RoundedCornerShape(30.dp)
                                        ),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Theme.colors.nextButton,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(R.drawable.pay_now_icon),
                                            tint = Color.White,
                                            contentDescription = null
                                        )
                                        Text(
                                            text = stringResource(R.string.pay_now),
                                            color = Color.White,
                                            fontFamily = poppins,
                                            fontWeight = FontWeight(600),
                                            fontSize = 14.sp,
                                            modifier = Modifier
                                                .padding(start = 9.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}