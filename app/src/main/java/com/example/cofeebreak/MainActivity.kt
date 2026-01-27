package com.example.cofeebreak

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cofeebreak.feature_app.presentation.Additives.AdditivesScreen
import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationScreen
import com.example.cofeebreak.feature_app.presentation.Barista.BaristaScreen
import com.example.cofeebreak.feature_app.presentation.Cafe.CafeScreen
import com.example.cofeebreak.feature_app.presentation.CoffeeCountry.CoffeeCountryScreen
import com.example.cofeebreak.feature_app.presentation.CoffeeType.CoffeeTypeScreen
import com.example.cofeebreak.feature_app.presentation.Designer.DesignerScreen
import com.example.cofeebreak.feature_app.presentation.ForgotPassword.ForgotPasswordScreen
import com.example.cofeebreak.feature_app.presentation.Menu.MenuScreen
import com.example.cofeebreak.feature_app.presentation.MyOrder.MyOrderScreen
import com.example.cofeebreak.feature_app.presentation.OrderOptions.OrderOptionsScreen
import com.example.cofeebreak.feature_app.presentation.Profile.ProfileScreen
import com.example.cofeebreak.feature_app.presentation.Redeem.RedeemScreen
import com.example.cofeebreak.feature_app.presentation.ResetPassword.ResetPasswordScreen
import com.example.cofeebreak.feature_app.presentation.Reward.RewardScreen
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpScreen
import com.example.cofeebreak.feature_app.presentation.Startup.StartupScreen
import com.example.cofeebreak.feature_app.presentation.TwoFactorVerification.TwoFactorVerificationScreen
import com.example.cofeebreak.feature_app.presentation.Welcome.WelcomeScreen
import com.example.cofeebreak.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)    
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Navigation.WelcomeScreen) {
                    composable<Navigation.WelcomeScreen> {
                        WelcomeScreen(navController)
                    }
                    composable<Navigation.StartupScreen> {
                        StartupScreen(navController)
                    }
                    composable<Navigation.AuthorizationScreen> {
                        AuthorizationScreen(navController)
                    }
                    composable<Navigation.SignUpScreen> {
                        SignUpScreen(navController)
                    }
                    composable<Navigation.MenuScreen> {
                        MenuScreen(navController)
                    }
                    composable<Navigation.ForgotPasswordScreen> {
                        ForgotPasswordScreen(navController)
                    }
                    composable<Navigation.TwoFactorVerificationScreen>{
                        TwoFactorVerificationScreen(navController)
                    }
                    composable<Navigation.ResetPasswordScreen> {
                        ResetPasswordScreen(navController)
                    }
                    composable<Navigation.CafeScreen> {
                        CafeScreen(navController)
                    }
                    composable<Navigation.MyOrderScreen> {
                        MyOrderScreen(navController)
                    }
                    composable<Navigation.ProfileScreen> {
                        ProfileScreen(navController)
                    }
                    composable<Navigation.RewardScreen> {
                        RewardScreen(navController)
                    }
                    composable<Navigation.RedeemScreen> {
                        RedeemScreen(navController)
                    }
                    composable<Navigation.OrderOptionsScreen> { backStackEntry ->
                        val route = backStackEntry.toRoute<Navigation.OrderOptionsScreen>()
                        OrderOptionsScreen(navController, route.imageUrl)
                    }
                    composable<Navigation.DesignerScreen> {
                        DesignerScreen(navController)
                    }
                    composable<Navigation.BaristaScreen> {
                        BaristaScreen(navController)
                    }
                    composable<Navigation.AdditivesScreen> {
                        AdditivesScreen(navController)
                    }
                    composable<Navigation.CoffeeCountryScreen> {
                        CoffeeCountryScreen(navController)
                    }
                    composable<Navigation.CoffeeTypeScreen> {
                        CoffeeTypeScreen(navController)
                    }
                }
            }
        }
    }
}