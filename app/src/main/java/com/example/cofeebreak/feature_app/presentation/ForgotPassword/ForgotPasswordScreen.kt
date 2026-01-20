package com.example.cofeebreak.feature_app.presentation.ForgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun ForgotPasswordScreen(navController: NavController, vm: ForgotPasswordVM = hiltViewModel()) {
    val state = vm.state.value
    if (state.error) {
        ErrorAlertDialog(stringResource(R.string.the_email_address_field_is_required)) {
            vm.onEvent(ForgotPasswordEvent.ChangeError)
        }
    }
    if(state.errorValidEmail){
        ErrorAlertDialog(stringResource(R.string.incorrect_email)) {
            vm.onEvent(ForgotPasswordEvent.ErrorValidEmail)
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
                .background(Theme.colors.mainBackgroundColor)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
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
            Column(
                modifier = Modifier
                    .padding(top = 46.dp)
                    .padding(horizontal = 41.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    fontSize = 22.sp,
                    fontFamily = roboto,
                    color = Theme.colors.signUpTextColor
                )
                Text(
                    text = stringResource(R.string.enter_your_email_address),
                    color = Theme.colors.alternativeBlack,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
                TextField(
                    value = state.email,
                    singleLine = true,
                    onValueChange = {
                        vm.onEvent(ForgotPasswordEvent.EnteredEmail(it))
                        vm.onEvent(ForgotPasswordEvent.ValidEmail)
                    },
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent.copy(alpha = 0.1f),
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(R.color.TfColor),
                        unfocusedIndicatorColor = colorResource(R.color.TfColor),
                        focusedTextColor = Theme.colors.oppositeColor,
                        unfocusedTextColor = Theme.colors.oppositeColor
                    ),
                    leadingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.email_icon),
                                contentDescription = null,
                                tint = Theme.colors.tfIconsColor
                            )
                            Box(
                                modifier = Modifier
                                    .padding(start = 9.dp)
                                    .background(colorResource(R.color.TfColor))
                                    .clip(RectangleShape)
                                    .height(25.dp)
                                    .width(1.dp)
                            )
                        }
                    },
                    trailingIcon = {
                        if (state.validEmail == true) {
                            Icon(
                                painter = painterResource(R.drawable.valid_email_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        } else if (state.validEmail == false) {
                            Icon(
                                painter = painterResource(R.drawable.not_valid_email_icon),
                                contentDescription = null,
                                tint = Theme.colors.tfIconsColor
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.email_address),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor)
                        )
                    }
                )
                Button(
                    onClick = {
                        if(state.email.isNotEmpty()){
                            if(state.validEmail == true){
                                if (!state.error && !state.errorValidEmail) {
                                    navController.navigate(Navigation.TwoFactorVerificationScreen)
                                }
                            } else {
                                vm.onEvent(ForgotPasswordEvent.ErrorValidEmail)
                            }
                        } else {
                            vm.onEvent(ForgotPasswordEvent.ChangeError)
                        }
                    },
                    modifier = Modifier
                        .padding(top = 153.dp)
                        .align(Alignment.End)
                        .clip(CircleShape)
                        .size(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.MainColor)
                    ),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Theme.colors.authArrowIconColor
                    )
                }
            }
        }
    }
}