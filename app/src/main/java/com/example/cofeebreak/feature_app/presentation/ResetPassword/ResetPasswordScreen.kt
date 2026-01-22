package com.example.cofeebreak.feature_app.presentation.ResetPassword

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun ResetPasswordScreen(navController: NavController, vm: ResetPasswordVM = hiltViewModel()) {
    val state = vm.state.value
    if(state.emptyFieldError){
        ErrorAlertDialog(stringResource(R.string.password_error_text)) {
            vm.onEvent(ResetPasswordEvent.ChangeEmptyFieldError)
        }
    }
    if(state.passwordError){
        ErrorAlertDialog(stringResource(R.string.password_error_text)) {
            vm.onEvent(ResetPasswordEvent.ChangePasswordError)
        }
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)) {
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
                    text = stringResource(R.string.reset_password),
                    fontSize = 22.sp,
                    fontFamily = roboto,
                    color = Theme.colors.signUpTextColor
                )
                Text(
                    text = stringResource(R.string.enter_new_password),
                    color = Theme.colors.alternativeBlack,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
                TextField(
                    value = state.password,
                    singleLine = true,
                    onValueChange = {
                        vm.onEvent(ResetPasswordEvent.EnteredPassword(it))
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
                                painter = painterResource(R.drawable.password_icon),
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
                        IconButton(onClick = {
                            vm.onEvent(ResetPasswordEvent.PasswordVisible)
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.eye_icon),
                                contentDescription = null,
                                tint = Theme.colors.eyeIconColor
                            )
                            if(state.passwordVisible){
                                Icon(painterResource(R.drawable.line_eye_icon),
                                    contentDescription = null,
                                    tint = Theme.colors.eyeIconColor)
                            }
                        }
                    },
                    visualTransformation = if (!state.passwordVisible) PasswordVisualTransformation('*') else VisualTransformation.None,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.password_placeholder),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor)
                        )
                    }
                )
                Button(
                    onClick = {
                        if(state.password.isNotEmpty()){
                            vm.onEvent(ResetPasswordEvent.IsPasswordStrong)
                            if(state.isPasswordStrong){
                                navController.navigate(Navigation.MenuScreen)
                            } else{
                                vm.onEvent(ResetPasswordEvent.ChangePasswordError)
                            }
                        } else {
                            vm.onEvent(ResetPasswordEvent.ChangeEmptyFieldError)
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