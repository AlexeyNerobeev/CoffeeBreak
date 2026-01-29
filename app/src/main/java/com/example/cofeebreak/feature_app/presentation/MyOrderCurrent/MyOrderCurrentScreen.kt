package com.example.cofeebreak.feature_app.presentation.MyOrderCurrent

import android.R.id.tabs
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cofeebreak.R
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun MyOrderCurrentScreen(navController: NavController, vm: MyOrderCurrentVM = hiltViewModel()) {
    val state = vm.state.value
    if(state.error){
        ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
            vm.onEvent(MyOrderCurrentEvent.ChangeError)
        }
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)) {
            Text(text = stringResource(R.string.my_order),
                color = Theme.colors.oppositeColor,
                fontFamily = roboto,
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 21.dp)
                    .align(Alignment.CenterHorizontally)
            )
            val tabs = listOf(
                "Текущий",
                "История"
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
                divider = {} ,
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

        }
    }
}