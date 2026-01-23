    package com.example.cofeebreak.feature_app.presentation.Menu

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
    import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
    import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
    import androidx.compose.foundation.lazy.staggeredgrid.items
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.material3.Icon
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
    import com.example.cofeebreak.Navigation
    import com.example.cofeebreak.R
    import com.example.cofeebreak.common.BottomNavigationBar
    import com.example.cofeebreak.common.ErrorAlertDialog
    import com.example.cofeebreak.common.dmSans
    import com.example.cofeebreak.common.poppins
    import com.example.cofeebreak.common.roboto
    import com.example.cofeebreak.ui.theme.Theme

    @Composable
    fun MenuScreen(navController: NavController, vm: MenuScreenVM = hiltViewModel()) {
        val state = vm.state.value
        if(state.serverError){
            ErrorAlertDialog(error = stringResource(R.string.server_request_error)) {
                vm.onEvent(MenuScreenEvent.ChangeError)
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
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 27.dp)
                        .padding(start = 26.dp)
                        .padding(end = 33.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.Welcome) + "!",
                            color = Theme.colors.menuTopText,
                            fontWeight = FontWeight(500),
                            fontFamily = poppins,
                            fontSize = 14.sp
                        )
                        if (state.name.isEmpty()){
                            CircularProgressIndicator(modifier = Modifier
                                .padding(top = 2.dp)
                                .size(18.dp),
                                color = Theme.colors.menuNameColor)
                        } else {
                            Text(
                                text = state.name,
                                color = Theme.colors.menuNameColor,
                                fontFamily = poppins,
                                fontSize = 18.sp,
                                fontWeight = FontWeight(500),
                                modifier = Modifier
                                    .padding(top = 2.dp)
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.cart_icon),
                            contentDescription = null,
                            tint = Theme.colors.menuIconsColor,
                            modifier = Modifier
                                .clickable{
                                    navController.navigate(Navigation.MyOrderScreen)
                                }
                        )
                        if(state.avatar_url.isNullOrEmpty()) {
                            Icon(
                                painter = painterResource(R.drawable.menu_profile_icon),
                                contentDescription = null,
                                tint = Theme.colors.menuIconsColor,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                                    .clickable {
                                        navController.navigate(Navigation.ProfileScreen)
                                    }
                            )
                        } else{
                            AsyncImage(model = state.avatar_url,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .clickable{
                                        navController.navigate(Navigation.ProfileScreen)
                                    },
                                contentScale = ContentScale.Crop)
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 7.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(Theme.colors.menuBoxColor)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.choose_your_coffee),
                            color = colorResource(R.color.ChooseYourCoffeeColor),
                            fontFamily = roboto,
                            fontWeight = FontWeight(500),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .padding(top = 16.dp)
                        )
                        if (state.coffeeList.isEmpty()){
                            CircularProgressIndicator(modifier = Modifier
                                .padding(top = 29.dp)
                                .align(Alignment.CenterHorizontally),
                                color = Color.White)
                        } else {
                            LazyVerticalStaggeredGrid(
                                modifier = Modifier
                                    .padding(top = 29.dp)
                                    .fillMaxWidth(),
                                columns = StaggeredGridCells.Fixed(2),
                                verticalItemSpacing = 17.dp,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.coffeeList) { item ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.White)
                                            .clickable{
                                                navController.navigate(Navigation.OrderOptionsScreen(item.coffee_image))
                                            }
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(top = 20.dp)
                                                .padding(bottom = 7.dp)
                                                .padding(horizontal = 7.dp)
                                                .align(Alignment.Center)
                                        ) {
                                            AsyncImage(
                                                model = item.coffee_image,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(120.dp)
                                                    .align(Alignment.CenterHorizontally),
                                                contentScale = ContentScale.Fit
                                            )
                                            Text(
                                                text = item.coffee_name,
                                                color = Color.Black,
                                                fontWeight = FontWeight(500),
                                                fontFamily = dmSans,
                                                fontSize = 14.sp,
                                                modifier = Modifier
                                                    .padding(top = 12.dp)
                                                    .align(Alignment.CenterHorizontally)
                                            )
                                            Text(
                                                text = item.price.toString() + "₽",
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight(500),
                                                fontFamily = poppins,
                                                color = Color.Black,
                                                modifier = Modifier
                                                    .padding(top = 2.dp)
                                                    .align(Alignment.End)
                                            )
                                        }
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
                BottomNavigationBar(navController, Navigation.MenuScreen)
            }
        }
    }