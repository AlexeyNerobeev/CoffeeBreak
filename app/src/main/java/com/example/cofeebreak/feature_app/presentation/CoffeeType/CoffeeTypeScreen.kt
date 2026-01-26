package com.example.cofeebreak.feature_app.presentation.CoffeeType

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import com.example.cofeebreak.common.BottomNavigationBar
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.dmSans
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun CoffeeTypeScreen(navController: NavController, vm: CoffeeTypeVM = hiltViewModel()) {
    val state = vm.state.value
    if(state.error){
        ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
            vm.onEvent(CoffeeTypeEvent.ChangeError)
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
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
            Column(
                modifier = Modifier
                    .padding(top = 28.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.select_country_and_coffee_variety),
                    color = Theme.colors.backIconColor,
                    fontFamily = roboto,
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 2.dp)
                )
                if (state.coffeeTypeList.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 45.dp)
                            .align(Alignment.CenterHorizontally),
                        color = Theme.colors.oppositeColor
                    )
                } else {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier
                            .padding(top = 45.dp)
                            .fillMaxWidth(),
                        columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 20.dp,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(state.coffeeTypeList){ item ->
                            Column(modifier = Modifier
                                .width(158.dp)
                                .clickable {
                                    navController.navigate(Navigation.DesignerScreen)
                                }) {
                                AsyncImage(
                                    model = item.coffee_image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(158.dp)
                                        .clip(RoundedCornerShape(20.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = item.name,
                                    color = Theme.colors.oppositeColor,
                                    fontFamily = dmSans,
                                    fontWeight = FontWeight(400),
                                    fontSize = 17.sp,
                                    modifier = Modifier
                                        .padding(top = 7.dp)
                                )
                                Text(
                                    text = item.description,
                                    color = Theme.colors.alternativeBlack,
                                    fontSize = 10.sp,
                                    fontFamily = dmSans,
                                    fontWeight = FontWeight(400),
                                    modifier = Modifier
                                        .padding(top = 7.dp)
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
            BottomNavigationBar(navController, Navigation.CoffeeTypeScreen)
        }
    }
}