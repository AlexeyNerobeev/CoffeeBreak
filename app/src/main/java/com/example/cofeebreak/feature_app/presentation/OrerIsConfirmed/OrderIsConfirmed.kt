package com.example.cofeebreak.feature_app.presentation.OrerIsConfirmed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun OrderIsConfirmed(navController: NavController) {
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = Theme.colors.mainBackgroundColor)) {
            IconButton(
                onClick = {
                    navController.navigate(Navigation.MenuScreen(true))
                },
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(start = 26.dp)
            ) {
                Icon(painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Theme.colors.backIconColor)
            }
        }
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center){
            Column(modifier = Modifier
                .padding(horizontal = 54.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(painter = painterResource(R.drawable.take_away_icon),
                    contentDescription = null,
                    tint = Theme.colors.backIconColor)
                Text(text = stringResource(R.string.ordered),
                    color = Theme.colors.oppositeColor,
                    fontWeight = FontWeight(400),
                    fontFamily = roboto,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 32.dp)
                )
                Text(text = stringResource(R.string.alexey_your_order_has_been_successfully_placed) + "\n",
                    color = Theme.colors.grayColor,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 22.dp))
                Text(text = stringResource(R.string.the_order_will_be_ready) + "\n",
                    color = Theme.colors.alternativeBlack,
                    fontFamily = roboto,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = stringResource(R.string.present_your_personal_qr_code),
                    color = Theme.colors.grayColor,
                    fontFamily = roboto,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center)
            }
        }
    }
}