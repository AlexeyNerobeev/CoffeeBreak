package com.example.cofeebreak.feature_app.presentation.Redeem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.colorResource
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
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme
import org.jetbrains.annotations.Async

@Composable
fun RedeemScreen(navController: NavController, vm: RedeemVM = hiltViewModel()) {
    val state = vm.state.value
    if(state.serverError){
        ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
            vm.onEvent(RedeemEvent.ChangeError)
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)) {
            Box(modifier = Modifier
                .padding(top = 21.dp)
                .padding(start = 26.dp)
                .fillMaxWidth()){
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(painter = painterResource(R.drawable.back_icon),
                        tint = Theme.colors.backProfileIcon,
                        contentDescription = null)
                }
                Text(text = stringResource(R.string.pay_with_points),
                    color = Theme.colors.oppositeColor,
                    fontFamily = roboto,
                    fontWeight = FontWeight(500),
                    fontSize =  16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            if(state.isLoaded) {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .padding(start = 22.dp)
                        .padding(end = 27.dp)
                        .fillMaxWidth()
                ) {
                    items(state.redeemList) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 37.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = item.coffee_image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(80.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                ) {
                                    Text(
                                        text = item.name,
                                        color = colorResource(R.color.AlternativeBlack),
                                        fontFamily = roboto,
                                        fontWeight = FontWeight(400),
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = stringResource(R.string.valid_until) + item.really_up_to,
                                        color = Theme.colors.rewardHistoryColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight(500),
                                        fontFamily = poppins,
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                .background(
                                    color = colorResource(R.color.AlternativeBlack),
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .clickable {

                                }
                                .padding(10.dp)) {
                                Text(
                                    text = item.price.toString() + stringResource(R.string.price),
                                    color = Color.White,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight(500),
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            } else{
                CircularProgressIndicator(modifier = Modifier
                    .padding(top = 35.dp)
                    .align(Alignment.CenterHorizontally),
                    color = colorResource(R.color.AlternativeBlack))
            }
        }
    }
}