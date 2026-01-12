package com.example.cofeebreak.feature_app.presentation.Authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cofeebreak.R
import com.example.cofeebreak.common.roboto

@Composable
fun AuthorizationScreen(navController: NavController) {
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)) {
            IconButton(onClick = {
                navController.popBackStack()
            },
                modifier = Modifier
                    .padding(top = 21.dp)
                    .padding(start = 26.dp)) {
                Icon(painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Color.Unspecified)
            }
            Column(modifier = Modifier
                .padding(top = 46.dp)
                .padding(horizontal = 41.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.SignIn),
                    fontSize =  22.sp,
                    fontFamily = roboto,
                    color = colorResource(R.color.Red),
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Text(text = stringResource(R.string.Welcome),
                    color = Color.Black,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .align(Alignment.Start))
                TextField(value = "",
                    onValueChange = {

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
                        Row(verticalAlignment = Alignment.CenterVertically){
                           Icon(painter = painterResource(R.drawable.email_icon),
                               contentDescription = null,
                               tint = Color.Unspecified)
                            Box(modifier = Modifier
                                .padding(start = 9.dp)
                                .background(colorResource(R.color.TfColor))
                                .clip(RectangleShape)
                                .height(25.dp)
                                .width(1.dp))
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.email_address),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor))
                    }
                )
                TextField(value = "",
                    onValueChange = {

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
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Icon(painter = painterResource(R.drawable.password_icon),
                                contentDescription = null,
                                tint = Color.Unspecified)
                            Box(modifier = Modifier
                                .padding(start = 9.dp)
                                .background(colorResource(R.color.TfColor))
                                .clip(RectangleShape)
                                .height(25.dp)
                                .width(1.dp))
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {

                        }) {
                            Icon(painter = painterResource(R.drawable.eye_icon),
                                contentDescription = null,
                                tint = Color.Unspecified)
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.password_placeholder),
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            color = colorResource(R.color.PlaceholderColor))
                    }
                )
                Text(text = stringResource(R.string.forgot_password),
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    color = colorResource(R.color.Red),
                    modifier = Modifier
                        .padding(top = 27.dp))
                Button(onClick = {

                },
                    modifier = Modifier
                        .padding(top = 136.dp)
                        .align(Alignment.End)
                        .clip(CircleShape)
                        .size(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.MainColor)
                    )) {
                    Icon(painter = painterResource(R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Color.Unspecified)
                }
                Text(text = stringResource(R.string.SignInWith),
                    color = Color.Black,
                    fontSize =  14.sp,
                    fontFamily = roboto,
                    modifier = Modifier
                        .padding(top = 21.dp))
                Row(modifier = Modifier
                    .padding(top = 14.dp)){
                    IconButton(onClick = {

                    }) {
                        Icon(painterResource(R.drawable.yandex),
                            contentDescription = null,
                            tint = Color.Unspecified)
                    }
                    IconButton(onClick = {

                    },
                        modifier = Modifier
                            .padding(start = 10.dp)) {
                        Icon(painterResource(R.drawable.google),
                            contentDescription = null,
                            tint = Color.Unspecified)
                    }
                    IconButton(onClick = {

                    },
                        modifier = Modifier
                            .padding(start = 10.dp)) {
                        Icon(painterResource(R.drawable.vk),
                            contentDescription = null,
                            tint = Color.Unspecified)
                    }
                }
                Row(modifier = Modifier
                    .padding(top = 36.dp)
                    .align(Alignment.Start)){
                    Text(text = stringResource(R.string.ForTheFirstTime),
                        color = colorResource(R.color.Gray),
                        fontFamily = roboto,
                        fontSize = 14.sp)
                    Text(text = stringResource(R.string.GoToSignUp),
                        color = Color.Black,
                        fontFamily = roboto,
                        fontSize = 14.sp)
                }
            }
        }
    }
}