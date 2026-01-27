package com.example.cofeebreak.feature_app.presentation.Designer

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.dmSans
import com.example.cofeebreak.common.montserrat
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.BorderDesigner
import com.example.cofeebreak.ui.theme.RewardLine
import com.example.cofeebreak.ui.theme.Theme
import io.github.jan.supabase.realtime.Column

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignerScreen(navController: NavController, vm: DesignerVM = hiltViewModel()) {
    val state = vm.state.value
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
            Row(
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(start = 26.dp)
                    .padding(end = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
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
                    .padding(top = 39.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Navigation.BaristaScreen)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.select_a_barista),
                        color = Theme.colors.backIconColor,
                        fontFamily = dmSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        painter = painterResource(R.drawable.more_icon),
                        contentDescription = null,
                        tint = Theme.colors.backIconColor
                    )
                }
                Canvas(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 19.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = stringResource(R.string.type_of_coffee),
                        color = Theme.colors.backIconColor,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        fontFamily = dmSans,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Slider(
                            value = state.sliderPosition,
                            onValueChange = {
                                vm.onEvent(DesignerEvent.SliderChange(it))
                            },
                            steps = 1,
                            thumb = {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .shadow(
                                            elevation = 6.dp,
                                            shape = CircleShape,
                                            clip = false
                                        )
                                        .background(Color.White, CircleShape)
                                )
                            },
                            track = { sliderState ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .background(
                                            color = Theme.colors.inactiveSlider,
                                            shape = RoundedCornerShape(2.dp)
                                        )
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(sliderState.value)
                                            .height(4.dp)
                                            .background(
                                                color = Theme.colors.activeSlider,
                                                shape = RoundedCornerShape(2.dp)
                                            )
                                    )
                                }
                            }
                        )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.arabica),
                                color = colorResource(R.color.alternativeWhite),
                                fontFamily = dmSans,
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp
                            )
                            Text(
                                text = stringResource(R.string.robusta),
                                color = colorResource(R.color.alternativeWhite),
                                fontFamily = dmSans,
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                Canvas(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Navigation.CoffeeCountryScreen)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.coffee_type),
                        color = Theme.colors.backIconColor,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp)
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(painter = painterResource(R.drawable.more_icon),
                            contentDescription = null,
                            tint = Theme.colors.backIconColor)
                    }
                }
                Canvas(modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 7.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = stringResource(R.string.roasting),
                        color = Theme.colors.backIconColor,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.Bottom) {
                        Icon(painter = painterResource(R.drawable.flame_icon),
                            contentDescription = null,
                            tint = colorResource(R.color.alternativeWhite))
                        Row(modifier = Modifier
                            .padding(start = 16.dp)) {
                            Icon(painter = painterResource(R.drawable.flame_icon),
                                contentDescription = null,
                                tint = colorResource(R.color.alternativeWhite))
                            Icon(painter = painterResource(R.drawable.flame_icon),
                                contentDescription = null,
                                tint = colorResource(R.color.alternativeWhite))
                        }
                        Column(modifier = Modifier
                            .padding(start = 18.dp)){
                            Icon(painter = painterResource(R.drawable.flame_icon),
                                contentDescription = null,
                                tint = Theme.colors.backIconColor,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally))
                            Row{
                                Icon(painter = painterResource(R.drawable.flame_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.backIconColor)
                                Icon(painter = painterResource(R.drawable.flame_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.backIconColor)
                            }
                        }
                    }
                }
                Canvas(modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.grinding),
                        color = Theme.colors.backIconColor,
                        fontWeight = FontWeight(500),
                        fontFamily = dmSans,
                        fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.Bottom) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(painter = painterResource(R.drawable.corn_icon),
                                contentDescription = null,
                                tint = Theme.colors.backIconColor,
                                modifier = Modifier
                                    .size(21.dp, 25.dp))
                        }
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(painter = painterResource(R.drawable.corn_icon),
                                contentDescription = null,
                                tint = colorResource(R.color.alternativeWhite),
                                modifier = Modifier
                                    .size(27.dp, 34.dp))
                        }
                    }
                }
                Canvas(modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .clickable{
                        vm.onEvent(DesignerEvent.SelectMilk)
                    },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.milk),
                        color = Theme.colors.backIconColor,
                        fontWeight = FontWeight(500),
                        fontFamily = dmSans,
                        fontSize = 14.sp)
                    Text(text = stringResource(R.string.select),
                        color = Theme.colors.backIconColor,
                        fontSize = 14.sp,
                        fontFamily = roboto,
                        fontWeight = FontWeight(400))
                }
                Canvas(modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .clickable{
                        vm.onEvent(DesignerEvent.SelectSyrup)
                    },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = stringResource(R.string.syrup),
                        color = Theme.colors.backIconColor,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp)
                    Text(text = stringResource(R.string.select),
                        color = Theme.colors.backIconColor,
                        fontSize = 14.sp,
                        fontFamily = roboto,
                        fontWeight = FontWeight(400))
                }
                Canvas(modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Navigation.AdditivesScreen)
                    },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = stringResource(R.string.supplements),
                        color = Theme.colors.backIconColor,
                        fontWeight = FontWeight(500),
                        fontFamily = dmSans,
                        fontSize = 14.sp)
                    Icon(painter = painterResource(R.drawable.more_icon),
                        contentDescription = null,
                        tint = Theme.colors.backIconColor)
                }
                Canvas(modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.Top){
                    Text(text = stringResource(R.string.ice),
                        color = Theme.colors.backIconColor,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.Bottom){
                        Icon(painter = painterResource(R.drawable.no_ice),
                            contentDescription = null,
                            tint = Theme.colors.backIconColor)
                        Icon(painter = painterResource(R.drawable.ice_cube),
                            contentDescription = null,
                            tint = colorResource(R.color.oneIceColor),
                            modifier = Modifier
                                .padding(start = 26.dp))
                        Row(modifier = Modifier
                            .padding(start = 26.dp),
                            verticalAlignment = Alignment.Bottom){
                            Icon(painter = painterResource(R.drawable.ice_cube),
                                contentDescription = null,
                                tint = colorResource(R.color.alternativeWhite),
                                modifier = Modifier
                                    .padding(bottom = 6.dp)
                                    .padding(end = 1.dp))
                            Icon(painter = painterResource(R.drawable.ice_cube),
                                contentDescription = null,
                                tint = colorResource(R.color.alternativeWhite))
                        }
                        Column(modifier = Modifier
                            .padding(start = 26.dp),
                            horizontalAlignment = Alignment.CenterHorizontally){
                            Icon(painter = painterResource(R.drawable.ice_cube),
                                contentDescription = null,
                                tint = colorResource(R.color.alternativeWhite))
                            Row{
                                Icon(painter = painterResource(R.drawable.ice_cube),
                                    contentDescription = null,
                                    tint = colorResource(R.color.alternativeWhite))
                                Icon(painter = painterResource(R.drawable.ice_cube),
                                    contentDescription = null,
                                    tint = colorResource(R.color.alternativeWhite))
                            }
                        }
                    }
                }
                Canvas(modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth()
                    .height(1.dp)){
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = RewardLine
                    )
                }
                Row(modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.coffee_lovers_encyclopedia),
                        color = colorResource(R.color.MainColor),
                        fontFamily = dmSans,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp)
                    Icon(painter = painterResource(R.drawable.more_icon),
                        contentDescription = null,
                        tint = colorResource(R.color.MainColor))
                }
                Row(
                    modifier = Modifier
                        .padding(top = 57.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(R.string.total_amount),
                        color = Theme.colors.backIconColor,
                        fontFamily = roboto,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "250 ₽",
                        color = Theme.colors.totalPriceColor,
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(600)
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(Navigation.MyOrderScreen)
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp)),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Theme.colors.nextButton
                    )
                ) {
                    Text(
                        text = stringResource(R.string.next),
                        color = Color.White,
                        fontWeight = FontWeight(600),
                        fontFamily = roboto,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    )
                }
            }
        }
        if (state.selectMilk || state.selectSyrup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(modifier = Modifier
                    .padding(bottom = 32.dp)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Theme.colors.designerSelectTypeColor,
                                shape = RoundedCornerShape(13.dp)
                            )
                    ) {
                        Text(
                            text = if (state.selectMilk)
                                stringResource(R.string.what_type_of_milk_do_you_prefer)
                            else
                                stringResource(R.string.what_flavor_of_syrup_do_you_prefer),
                            color = Theme.colors.milkSyrupTitle,
                            fontWeight = FontWeight(400),
                            fontFamily = poppins,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        ) {
                            drawLine(
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                color = BorderDesigner
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable {
                                    if (state.selectMilk)
                                        vm.onEvent(DesignerEvent.SelectMilk)
                                    else
                                        vm.onEvent(DesignerEvent.SelectSyrup)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.No),
                                color = Theme.colors.selectDesigner,
                                fontSize = 20.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(400)
                            )
                        }
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        ) {
                            drawLine(
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                color = BorderDesigner
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable {
                                    if (state.selectMilk)
                                        vm.onEvent(DesignerEvent.SelectMilk)
                                    else
                                        vm.onEvent(DesignerEvent.SelectSyrup)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (state.selectMilk)
                                    stringResource(R.string.cow)
                                else
                                    stringResource(R.string.amaretto),
                                color = Theme.colors.selectDesigner,
                                fontSize = 20.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(400)
                            )
                        }
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        ) {
                            drawLine(
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                color = BorderDesigner
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable {
                                    if (state.selectMilk)
                                        vm.onEvent(DesignerEvent.SelectMilk)
                                    else
                                        vm.onEvent(DesignerEvent.SelectSyrup)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (state.selectMilk)
                                    stringResource(R.string.lactose_free)
                                else
                                    stringResource(R.string.coconut),
                                color = Theme.colors.selectDesigner,
                                fontSize = 20.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(400)
                            )
                        }
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        ) {
                            drawLine(
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                color = BorderDesigner
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable {
                                    if (state.selectMilk)
                                        vm.onEvent(DesignerEvent.SelectMilk)
                                    else
                                        vm.onEvent(DesignerEvent.SelectSyrup)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (state.selectMilk)
                                    stringResource(R.string.low_fat)
                                else
                                    stringResource(R.string.vanilla),
                                color = Theme.colors.selectDesigner,
                                fontSize = 20.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(400)
                            )
                        }
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        ) {
                            drawLine(
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                color = BorderDesigner
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable {
                                    if (state.selectMilk)
                                        vm.onEvent(DesignerEvent.SelectMilk)
                                    else
                                        vm.onEvent(DesignerEvent.SelectSyrup)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (state.selectMilk)
                                    stringResource(R.string.vegetable)
                                else
                                    stringResource(R.string.caramel),
                                color = Theme.colors.selectDesigner,
                                fontSize = 20.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(400)
                            )
                        }
                    }
                    Button(
                        onClick = {
                            if (state.selectMilk)
                                vm.onEvent(DesignerEvent.SelectMilk)
                            else
                                vm.onEvent(DesignerEvent.SelectSyrup)
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(13.dp))
                            .background(
                                color = Theme.colors.cancelButton,
                                shape = RoundedCornerShape(13.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Theme.colors.cancelButton,
                            contentColor = Theme.colors.oppositeColor
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.cancellation),
                            color = Theme.colors.oppositeColor,
                            fontWeight = FontWeight(700),
                            fontFamily = montserrat,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}