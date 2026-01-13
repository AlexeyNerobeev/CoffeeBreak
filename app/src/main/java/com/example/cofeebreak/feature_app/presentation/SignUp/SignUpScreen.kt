package com.example.cofeebreak.feature_app.presentation.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationEvent
import org.koin.androidx.compose.koinViewModel

@Preview(locale = "en-en")
@Composable
fun PrevSignUp() {
    SignUpScreen(rememberNavController())
}

@Composable
fun SignUpScreen(navController: NavController, vm: SignUpVM = koinViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete) {
            navController.navigate(Navigation.StartupScreen) {
                popUpTo(0) {
                    inclusive = true
                }
            }
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
                .background(Color.White)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 46.dp)
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = stringResource(R.string.SignUp),
                    color = Color.Black,
                    fontFamily = roboto,
                    fontSize = 22.sp
                )
                Text(
                    text = stringResource(R.string.CreateAnAccountHere),
                    color = colorResource(R.color.AlternativeBlack),
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 57.dp)
                )
                TextField(
                    value = state.name,
                    onValueChange = {
                        vm.onEvent(SignUpEvent.EnteredName(it))
                    },
                    modifier = Modifier
                        .padding(top = 57.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = colorResource(R.color.TfColor),
                        unfocusedLabelColor = colorResource(R.color.TfColor),
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = colorResource(R.color.TfColor),
                        unfocusedIndicatorColor = colorResource(R.color.TfColor)
                    ),
                    leadingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.email_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
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
                    placeholder = {
                        Text(
                            text = stringResource(R.string.UserName),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor)
                        )
                    }
                )
                TextField(
                    value = state.phone,
                    onValueChange = {
                        vm.onEvent(SignUpEvent.EnteredPhone(it))
                    },
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = colorResource(R.color.TfColor),
                        unfocusedLabelColor = colorResource(R.color.TfColor),
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = colorResource(R.color.TfColor),
                        unfocusedIndicatorColor = colorResource(R.color.TfColor)
                    ),
                    leadingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.email_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
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
                    placeholder = {
                        Text(
                            text = stringResource(R.string.MobilePhoneNumber),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor)
                        )
                    }
                )
                TextField(
                    value = state.emailAddress,
                    onValueChange = {
                        vm.onEvent(SignUpEvent.EnteredEmailAddress(it))
                    },
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = colorResource(R.color.TfColor),
                        unfocusedLabelColor = colorResource(R.color.TfColor),
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = colorResource(R.color.TfColor),
                        unfocusedIndicatorColor = colorResource(R.color.TfColor)
                    ),
                    leadingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.email_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
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
                    placeholder = {
                        Text(
                            text = stringResource(R.string.email_address),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor)
                        )
                    }
                )
                TextField(
                    value = state.password,
                    onValueChange = {
                        vm.onEvent(SignUpEvent.EnteredPassword(it))
                    },
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = colorResource(R.color.TfColor),
                        unfocusedLabelColor = colorResource(R.color.TfColor),
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = colorResource(R.color.TfColor),
                        unfocusedIndicatorColor = colorResource(R.color.TfColor)
                    ),
                    leadingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.password_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
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
                            vm.onEvent(SignUpEvent.PasswordVisible)
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.eye_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    },
                    visualTransformation = if (!state.passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.password_placeholder),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor)
                        )
                    }
                )
                Text(
                    text = stringResource(R.string.AgreementToTermsOfUse),
                    color = colorResource(R.color.AlternativeBlack),
                    fontSize = 14.sp,
                    fontFamily = roboto,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
                Button(
                    onClick = {
                        vm.onEvent(SignUpEvent.SignUp)
                    },
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .align(Alignment.End)
                        .clip(CircleShape)
                        .size(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.MainColor)
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 92.dp)
                ) {
                    Text(
                        text = stringResource(R.string.AlreadyRegistered),
                        fontFamily = roboto,
                        fontSize = 14.sp,
                        color = colorResource(R.color.AlternativeBlack)
                    )
                    Text(
                        text = stringResource(R.string.GoToSignIn),
                        fontFamily = roboto,
                        fontSize = 14.sp,
                        color = colorResource(R.color.AlternativeBlack),
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Navigation.AuthorizationScreen)
                            })
                }
            }
        }
    }
}