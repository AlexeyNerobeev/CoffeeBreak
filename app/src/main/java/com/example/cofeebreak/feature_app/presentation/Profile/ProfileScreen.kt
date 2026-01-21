package com.example.cofeebreak.feature_app.presentation.Profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cofeebreak.R
import com.example.cofeebreak.common.dmSans
import com.example.cofeebreak.common.poppins
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun ProfileScreen(navController: NavController, vm: ProfileVM = hiltViewModel()) {
    val state = vm.state.value
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)) {
            if(!state.qrVisible) {
                Box(
                    modifier = Modifier
                        .padding(top = 21.dp)
                        .padding(start = 26.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_icon),
                            contentDescription = null,
                            tint = Theme.colors.backProfileIcon
                        )
                    }
                    Text(
                        text = stringResource(R.string.profile),
                        fontFamily = roboto,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                        color = Theme.colors.oppositeColor,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 29.dp)
                        .padding(horizontal = 33.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        color = Theme.colors.profileBackgroundIcon,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.profile_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.activeBottomBarIcon,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.name),
                                    color = Theme.colors.titleTextProfile,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight(500),
                                    fontSize = 10.sp
                                )
                                if (state.name.isEmpty()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(14.dp),
                                        color = Theme.colors.profileMainText
                                    )
                                } else {
                                    Text(
                                        text = state.name,
                                        color = Theme.colors.profileMainText,
                                        fontWeight = FontWeight(600),
                                        fontSize = 14.sp,
                                        fontFamily = roboto
                                    )
                                }
                            }
                        }
                        Icon(
                            painter = painterResource(R.drawable.edit_icon),
                            contentDescription = null,
                            tint = Theme.colors.activeBottomBarIcon,
                            modifier = Modifier
                                .clickable {

                                })
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        color = Theme.colors.profileBackgroundIcon,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.phone_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.activeBottomBarIcon
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.phone_number),
                                    color = Theme.colors.titleTextProfile,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight(500),
                                    fontSize = 10.sp
                                )
                                if (state.phone.isEmpty()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(14.dp),
                                        color = Theme.colors.profileMainText
                                    )
                                } else {
                                    Text(
                                        text = state.phone,
                                        color = Theme.colors.profileMainText,
                                        fontWeight = FontWeight(600),
                                        fontSize = 14.sp,
                                        fontFamily = roboto
                                    )
                                }
                            }
                        }
                        Icon(
                            painter = painterResource(R.drawable.edit_icon),
                            contentDescription = null,
                            tint = Theme.colors.activeBottomBarIcon,
                            modifier = Modifier
                                .clickable {

                                })
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        color = Theme.colors.profileBackgroundIcon,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.message_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.activeBottomBarIcon
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.email),
                                    color = Theme.colors.titleTextProfile,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight(500),
                                    fontSize = 10.sp
                                )
                                if (state.email.isEmpty()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(14.dp),
                                        color = Theme.colors.profileMainText
                                    )
                                } else {
                                    Text(
                                        text = state.email,
                                        color = Theme.colors.profileMainText,
                                        fontWeight = FontWeight(600),
                                        fontSize = 14.sp,
                                        fontFamily = roboto
                                    )
                                }
                            }
                        }
                        Icon(
                            painter = painterResource(R.drawable.edit_icon),
                            contentDescription = null,
                            tint = Theme.colors.activeBottomBarIcon,
                            modifier = Modifier
                                .clickable {

                                })
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        color = Theme.colors.profileBackgroundIcon,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.location_profile_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.activeBottomBarIcon
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.magic_coffee_address),
                                    color = Theme.colors.titleTextProfile,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight(500),
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = if (state.address.isEmpty()) "-" else state.address.toString(),
                                    color = Theme.colors.profileMainText,
                                    fontWeight = FontWeight(600),
                                    fontSize = 14.sp,
                                    fontFamily = roboto
                                )
                            }
                        }
                        Icon(
                            painter = painterResource(R.drawable.edit_icon),
                            contentDescription = null,
                            tint = Theme.colors.activeBottomBarIcon,
                            modifier = Modifier
                                .clickable {

                                })
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .fillMaxWidth()
                            .clickable {
                                vm.onEvent(ProfileEvent.QRVisibleChange)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        color = Theme.colors.profileBackgroundIcon,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.qr_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.activeBottomBarIcon
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.qr_code),
                                    color = Theme.colors.titleTextProfile,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight(500),
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = stringResource(R.string.to_receive_your_order),
                                    color = Theme.colors.profileMainText,
                                    fontWeight = FontWeight(600),
                                    fontSize = 14.sp,
                                    fontFamily = roboto
                                )
                            }
                        }
                        Icon(
                            painter = painterResource(R.drawable.more_icon),
                            contentDescription = null,
                            tint = Theme.colors.activeBottomBarIcon,
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            } else{
                Box(
                    modifier = Modifier
                        .padding(top = 21.dp)
                        .padding(start = 26.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            vm.onEvent(ProfileEvent.QRVisibleChange)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_icon),
                            contentDescription = null,
                            tint = Theme.colors.backProfileIcon
                        )
                    }
                    Text(
                        text = stringResource(R.string.profile),
                        fontFamily = roboto,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                        color = Theme.colors.oppositeColor,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Column(modifier = Modifier
                    .padding(top = 27.dp)
                    .padding(horizontal = 43.dp)
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.your_personal_qr_code),
                        color = Theme.colors.signUpTextColor,
                        fontSize = 20.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center
                    )
                    state.qrBitmap?.let { bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .size(247.dp)
                        )
                    }
                    Text(text = stringResource(R.string.show_your_qr_code_to_receive_your_order),
                        color = Theme.colors.signUpTextColor,
                        fontFamily = roboto,
                        fontWeight = FontWeight(500),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(top = 20.dp))
                }
            }
        }
    }
}