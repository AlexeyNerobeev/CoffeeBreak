package com.example.cofeebreak

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cofeebreak.feature_app.presentation.Menu.MenuScreen
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Tests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun allRight(){
        val password = "Password"
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController, Navigation.SignUpScreen){
                composable<Navigation.SignUpScreen> {
                    SignUpScreen(navController)
                }
                composable<Navigation.MenuScreen> {
                    MenuScreen(navController)
                }
            }
        }
        composeTestRule.onNodeWithText("Имя пользователя").assertIsDisplayed()
            .performTextInput("user")
        composeTestRule.onNodeWithText("Номер мобильного телефона").assertIsDisplayed()
            .performTextInput("+79871234567")
        composeTestRule.onNodeWithText("Адрес электронной почты").assertIsDisplayed()
            .performTextInput("qwe@mail.ru")
        composeTestRule.onNodeWithText("Пароль").assertIsDisplayed()
            .performTextInput(password)
        isPasswordStrong(password)
        composeTestRule.onNodeWithTag("button").assertIsDisplayed()
            .performClick()
    }

    fun isPasswordStrong(password: String): Boolean{
        return password.length >= 9 &&
                password.any { it.isUpperCase() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isDigit() } &&
                password.any { it.isWhitespace() } &&
                password.any { !it.isLetterOrDigit() && !it.isWhitespace() }
    }
}