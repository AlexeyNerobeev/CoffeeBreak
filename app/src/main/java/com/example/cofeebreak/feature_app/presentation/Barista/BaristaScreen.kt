package com.example.cofeebreak.feature_app.presentation.Barista

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.cofeebreak.common.BottomNavigationBar
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun BaristaScreen(navController: NavController, vm: BaristaVM = hiltViewModel()) {
    val state = vm.state.value
    if(state.error){
        ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
            vm.onEvent(BaristaEvent.ChangeError)
        }
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = Theme.colors.mainBackgroundColor)) {
            Row(
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(start = 26.dp)
                    .padding(end = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                    text = stringResource(R.string.coffee_lovers_designer),
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
            Column(modifier = Modifier
                .padding(top = 28.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()) {
                Text(text = stringResource(R.string.select_a_barista),
                    color = Theme.colors.backIconColor,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    fontFamily = roboto,
                    modifier = Modifier
                        .padding(start = 10.dp))
                if (state.baristaList.isEmpty()){
                    CircularProgressIndicator(modifier = Modifier
                        .padding(top = 17.dp)
                        .align(Alignment.CenterHorizontally),
                        color = Theme.colors.oppositeColor)
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 17.dp)
                            .fillMaxWidth()
                    ) {
                        items(state.baristaList) { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(81.dp)
                                    .shadow(
                                        elevation = 12.dp,
                                        shape = RoundedCornerShape(22.dp),
                                        clip = false,
                                        spotColor = Theme.colors.baristaShadow
                                    )
                                    .background(
                                        color = Theme.colors.baristaBoxBackground,
                                        shape = RoundedCornerShape(22.dp)
                                    )
                                    .clickable{
                                        navController.navigate(Navigation.DesignerScreen)
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .padding(end = 34.dp)
                                        .padding(vertical = 10.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        AsyncImage(
                                            model = item.barista_image,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(62.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 18.dp)
                                        ) {
                                            Text(
                                                text = item.name,
                                                color = Theme.colors.oppositeColor,
                                                fontFamily = roboto,
                                                fontWeight = FontWeight(400),
                                                fontSize = 14.sp
                                            )
                                            Text(
                                                text = item.status,
                                                color = colorResource(R.color.Gray),
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight(400),
                                                fontFamily = poppins,
                                                modifier = Modifier
                                                    .padding(top = 12.dp)
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .size(15.dp)
                                            .clip(CircleShape)
                                            .background(
                                                color = if (item.status == "Топ-бариста")
                                                    colorResource(R.color.topBaristaColor)
                                                else
                                                    colorResource(R.color.regularBaristaColor)
                                            )
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                            )
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){
            BottomNavigationBar(navController, Navigation.BaristaScreen)
        }
    }
}