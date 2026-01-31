package com.example.cofeebreak.feature_app.presentation.Authorization

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.ErrorAlertDialog
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.ui.theme.Theme
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.GoogleDialogType
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

@Composable
fun AuthorizationScreen(navController: NavController, vm: AuthorizationVM = hiltViewModel()) {
    val state = vm.state.value
    val action = supabase.composeAuth.rememberSignInWithGoogle(
        type = GoogleDialogType.BOTTOM_SHEET,
        onResult = { result ->
            when (result) {
                is NativeSignInResult.Success -> {
                    Log.e("google", "success")
                    vm.onEvent(AuthorizationEvent.ProgressIndicator)
                    vm.onEvent(AuthorizationEvent.SignInWithGoogle)
                }
                is NativeSignInResult.ClosedByUser -> {
                    Log.e("google", "close")

                }
                is NativeSignInResult.Error -> {
                    Log.e("google", "error ${result.message}")

                }
                is NativeSignInResult.NetworkError -> {
                    Log.e("google", "network error")

                }
            }
        }
    )

    LaunchedEffect(key1 = !state.isComplete) {
        if(state.isComplete)
            navController.navigate(Navigation.StartupScreen){
                popUpTo(0){
                    inclusive = true
                }
            }
    }
    if (state.error) {
        ErrorAlertDialog(error = stringResource(R.string.incorrect_email_or_password)) {
            vm.onEvent(AuthorizationEvent.ClearError)
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Theme.colors.mainBackgroundColor)
                .fillMaxSize()
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
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.SignIn),
                    fontSize = 22.sp,
                    fontFamily = roboto,
                    color = Theme.colors.tfIconsColor,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Text(
                    text = stringResource(R.string.Welcome),
                    color = Theme.colors.alternativeBlack,
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .align(Alignment.Start)
                )
                TextField(
                    value = state.email,
                    singleLine = true,
                    onValueChange = {
                        vm.onEvent(AuthorizationEvent.EnteredEmail(it))
                        vm.onEvent(AuthorizationEvent.EmailValid)
                    },
                    modifier = Modifier
                        .padding(top = 57.dp)
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
                        if(state.validEmail == true){
                            Icon(painter = painterResource(R.drawable.valid_email_icon),
                                contentDescription = null,
                                tint = Color.Unspecified)
                        } else if(state.validEmail == false){
                            Icon(painter = painterResource(R.drawable.not_valid_email_icon),
                                contentDescription = null,
                                tint = Theme.colors.tfIconsColor)
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
                    singleLine = true,
                    onValueChange = {
                        vm.onEvent(AuthorizationEvent.EnteredPassword(it))
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
                            vm.onEvent(AuthorizationEvent.PasswordVisible)
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
                    visualTransformation = if(!state.passwordVisible) PasswordVisualTransformation('*') else VisualTransformation.None,
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
                    text = stringResource(R.string.forgot_password),
                    fontFamily = roboto,
                    fontSize = 14.sp,
                    color = colorResource(R.color.Red),
                    modifier = Modifier
                        .padding(top = 27.dp)
                        .clickable{
                            navController.navigate(Navigation.ForgotPasswordScreen)
                        }
                )
                Button(
                    onClick = {
                        vm.onEvent(AuthorizationEvent.ProgressIndicator)
                        vm.onEvent(AuthorizationEvent.SignIn)
                    },
                    modifier = Modifier
                        .padding(top = 136.dp)
                        .align(Alignment.End)
                        .clip(CircleShape)
                        .size(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.MainColor)
                    ),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    if(state.progressIndicator){
                        CircularProgressIndicator(
                            color = Theme.colors.mainBackgroundColor,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.arrow_right),
                            contentDescription = null,
                            tint = Theme.colors.authArrowIconColor
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.SignInWith),
                    color = Theme.colors.oppositeColor,
                    fontSize = 14.sp,
                    fontFamily = roboto,
                    modifier = Modifier
                        .padding(top = 21.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .size(38.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.yandex),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    val context = LocalContext.current
                    val coroutineScope = rememberCoroutineScope()
                    IconButton(
                        onClick = {
                            action.startFlow()
//                            val credentialManager = CredentialManager.create(context)
//
//                            val rawNonce = UUID.randomUUID().toString()
//                            val bytes = rawNonce.toByteArray()
//                            val md = MessageDigest.getInstance("SHA-256")
//                            val digest = md.digest(bytes)
//                            val hashedNonce = digest.fold("") {str, it -> str + "%02x".format(it)}
//
//                            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
//                                .setFilterByAuthorizedAccounts(false)
//                                .setServerClientId("624576438845-ia64o3ee7hdd9e3h8jpfohk0138vrjiv.apps.googleusercontent.com")
//                                .setNonce(hashedNonce)
//                                .build()
//
//                            val request: GetCredentialRequest = GetCredentialRequest.Builder()
//                                .addCredentialOption(googleIdOption)
//                                .build()
//
//                            coroutineScope.launch {
//                                try{
//                                    val result = credentialManager.getCredential(
//                                        request = request,
//                                        context = context
//                                    )
//                                    val credential = result.credential
//
//                                    val googleIdTokenCredential = GoogleIdTokenCredential
//                                        .createFrom(credential.data)
//
//                                    val googleIdToken = googleIdTokenCredential.idToken
//                                    Log.i("googleToken", googleIdToken)
//
//                                    Toast.makeText(context, "Sign in!", Toast.LENGTH_SHORT).show()
//                                } catch (e: GetCredentialException){
//                                    Log.e("getCredentialsException", e.message.toString())
//                                } catch (e: GoogleIdTokenParsingException){
//                                    Log.e("googleIdTokenParsingException", e.message.toString())
//                                }
//                            }
                        },
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(38.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.google),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(38.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.vk),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .align(Alignment.Start)
                ) {
                    Text(
                        text = stringResource(R.string.ForTheFirstTime),
                        color = colorResource(R.color.Gray),
                        fontFamily = roboto,
                        fontSize = 14.sp
                    )
                    Text(
                        text = stringResource(R.string.GoToSignUp),
                        color = colorResource(R.color.AlternativeBlack),
                        fontFamily = roboto,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable{
                                navController.navigate(Navigation.SignUpScreen)
                            }
                    )
                }
            }
        }
    }
}