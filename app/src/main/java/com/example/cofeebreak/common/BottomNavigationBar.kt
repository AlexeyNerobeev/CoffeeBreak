package com.example.cofeebreak.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun BottomNavigationBar(navController: NavController, currentScreen: Navigation) {
    Box(modifier = Modifier
        .padding(horizontal = 25.dp)
        .padding(bottom = 22.dp)
        .fillMaxWidth()
        .height(64.dp)
        .shadow(elevation = 10.dp,
            shape = RoundedCornerShape(20.dp),
            spotColor = colorResource(R.color.AlternativeBlack).copy(alpha = 0.12f)
        )
        .background(Theme.colors.navigationBarBackground,
            shape = RoundedCornerShape(20.dp)
        ),
        contentAlignment = Alignment.Center){
        Row(modifier = Modifier
            .padding(horizontal = 55.dp)
            .padding(vertical = 21.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = {
                    if(currentScreen != Navigation.MenuScreen)
                        navController.navigate(Navigation.MenuScreen)
                }
            ) {
                Icon(painter = painterResource(R.drawable.shop_icon),
                    contentDescription = null,
                    tint = if (currentScreen == Navigation.MenuScreen)
                    Theme.colors.activeBottomBarIcon
                    else
                    Theme.colors.defaultBottomBarIcon
                )
            }
            IconButton(
                onClick = {
                    if(currentScreen != Navigation.RewardScreen)
                        navController.navigate(Navigation.RewardScreen)
                }
            ) {
                Icon(painter = painterResource(R.drawable.gift_icon),
                    contentDescription = null,
                    tint = if (currentScreen == Navigation.RewardScreen)
                        Theme.colors.activeBottomBarIcon
                    else
                        Theme.colors.defaultBottomBarIcon
                )
            }
            IconButton(
                onClick = {
                    if(currentScreen != Navigation.MyOrderScreen)
                        navController.navigate(Navigation.MyOrderScreen)
                }
            ) {
                Icon(painter = painterResource(R.drawable.order_icon),
                    contentDescription = null,
                    tint = if (currentScreen == Navigation.MyOrderScreen)
                        Theme.colors.activeBottomBarIcon
                    else
                        Theme.colors.defaultBottomBarIcon
                )
            }
        }
    }
}