package com.example.cofeebreak.feature_app.presentation.TwoFactorVerification

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.feature_app.presentation.ForgotPassword.ForgotPasswordEvent
import com.example.cofeebreak.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TwoFactorVerificationScreen(navController: NavController, vm: TwoFactorVerificationVM = koinViewModel()) {
    val state = vm.state.value
    if(state.emptyFieldsError){
        ErrorAlertDialog(stringResource(R.string.all_fields_must_be_filled_in)) {
            vm.onEvent(TwoFactorVerificationEvent.ChangeEmptyFieldsError)
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
                    text = stringResource(R.string.verification),
                    fontSize = 22.sp,
                    fontFamily = roboto,
                    color = Theme.colors.signUpTextColor
                )
                Text(
                    text = stringResource(R.string.enter_the_code_we_sent_you_by_email),
                    color = Theme.colors.alternativeBlack,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
                Row(modifier = Modifier
                    .padding(top = 38.dp)
                    .align(Alignment.CenterHorizontally)) {
                    OutlinedTextField(
                        value = state.firstNumber,
                        onValueChange = {
                            if(state.firstNumber.length <= 1){
                                vm.onEvent(TwoFactorVerificationEvent.EnteredFirstNumber(it))
                            }
                        },
                        modifier = Modifier
                            .size(48.dp, 61.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = if(state.firstNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedContainerColor = if(state.firstNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedBorderColor = if(state.firstNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedBorderColor = if(state.firstNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedTextColor = Theme.colors.mainBackgroundColor,
                            unfocusedTextColor = Theme.colors.mainBackgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = roboto,
                            fontSize = 28.sp
                        ),
                    )
                    OutlinedTextField(
                        value = state.secondNumber,
                        onValueChange = {
                            if(state.secondNumber.length <= 1){
                                vm.onEvent(TwoFactorVerificationEvent.EnteredSecondNumber(it))
                            }
                        },
                        modifier = Modifier
                            .padding(start = 22.dp)
                            .size(48.dp, 61.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = if(state.secondNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedContainerColor = if(state.secondNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedBorderColor = if(state.secondNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedBorderColor = if(state.secondNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedTextColor = Theme.colors.mainBackgroundColor,
                            unfocusedTextColor = Theme.colors.mainBackgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = roboto,
                            fontSize = 28.sp
                        ),
                    )
                    OutlinedTextField(
                        value = state.thirdNumber,
                        onValueChange = {
                            if(state.thirdNumber.length <= 1){
                                vm.onEvent(TwoFactorVerificationEvent.EnteredThirdNumber(it))
                            }
                        },
                        modifier = Modifier
                            .padding(start = 22.dp)
                            .size(48.dp, 61.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = if(state.thirdNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedContainerColor = if(state.thirdNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedBorderColor = if(state.thirdNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedBorderColor = if(state.thirdNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedTextColor = Theme.colors.mainBackgroundColor,
                            unfocusedTextColor = Theme.colors.mainBackgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = roboto,
                            fontSize = 28.sp
                        ),
                    )
                    OutlinedTextField(
                        value = state.fourthNumber,
                        onValueChange = {
                            if(state.fourthNumber.length <= 1){
                                vm.onEvent(TwoFactorVerificationEvent.EnteredFourthNumber(it))
                            }
                        },
                        modifier = Modifier
                            .padding(start = 22.dp)
                            .size(48.dp, 61.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = if(state.fourthNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedContainerColor = if(state.fourthNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedBorderColor = if(state.fourthNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            unfocusedBorderColor = if(state.fourthNumber.isEmpty()) Theme.colors.outlinedTfColor
                            else Theme.colors.enteredOutlinedTextFieldColor,
                            focusedTextColor = Theme.colors.mainBackgroundColor,
                            unfocusedTextColor = Theme.colors.mainBackgroundColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = roboto,
                            fontSize = 28.sp
                        ),
                    )
                }
                Text(text = stringResource(R.string.resend_in_00_30),
                    color = Theme.colors.resendInTextColor,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 46.dp)
                        .align(Alignment.CenterHorizontally))
                Button(
                    onClick = {
                        if(state.firstNumber.isNotEmpty() &&
                            state.secondNumber.isNotEmpty() &&
                            state.thirdNumber.isNotEmpty() &&
                            state.fourthNumber.isNotEmpty()){
                            navController.navigate(Navigation.ResetPasswordScreen)
                        } else {
                            vm.onEvent(TwoFactorVerificationEvent.ChangeEmptyFieldsError)
                        }
                    },
                    modifier = Modifier
                        .padding(top = 62.dp)
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
                        tint = Theme.colors.authArrowIconColor
                    )
                }
            }
        }
    }
}