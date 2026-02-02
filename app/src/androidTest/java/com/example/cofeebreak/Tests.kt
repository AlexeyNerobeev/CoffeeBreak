package com.example.cofeebreak

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cofeebreak.FakeRepository.FakeAuthRepository
import com.example.cofeebreak.FakeRepository.FakeProfileRepository
import com.example.cofeebreak.common.EmptyMenuScreen
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import com.example.cofeebreak.feature_app.presentation.Menu.MenuScreen
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpScreen
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpVM
import com.example.cofeebreak.feature_app.presentation.Startup.StartupScreen
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Tests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var vm: SignUpVM

    private val authRepository = FakeAuthRepository()
    private val profileRepository = FakeProfileRepository()

    @Before
    fun setup() {
        vm = SignUpVM(
            signUpUseCase = SignUpUseCase(authRepository),
            createProfileUseCase = CreateProfileUseCase(profileRepository),
            isPasswordStrongUseCase = IsPasswordStrongUseCase(),
            isEmailValidUseCase = IsEmailValidUseCase()
        )
    }

    @Test
    fun allRight() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController, Navigation.SignUpScreen) {
                composable<Navigation.SignUpScreen> {
                    SignUpScreen(navController, vm)
                }
                composable<Navigation.StartupScreen> {
                    StartupScreen(navController)
                }
            }
        }
        composeTestRule.onNodeWithTag("UserName").assertIsDisplayed()
            .performTextInput("user")
        composeTestRule.onNodeWithTag("Mobile Phone Number").assertIsDisplayed()
            .performTextInput("+79871234567")
        composeTestRule.onNodeWithTag("Email Address").assertIsDisplayed()
            .performTextInput("qwe@mail.ru")
        composeTestRule.onNodeWithTag("Password").assertIsDisplayed()
            .performTextInput("Password_ 123$")
        composeTestRule.onNodeWithTag("signUpButton").assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("AlertError").assertIsNotDisplayed()
    }

    @Test
    fun passwordNotValid() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Navigation.SignUpScreen) {
                composable<Navigation.SignUpScreen> {
                    SignUpScreen(navController, vm)
                }
            }
        }
        composeTestRule.onNodeWithTag("UserName").assertIsDisplayed()
            .performTextInput("user name")
        composeTestRule.onNodeWithTag("Mobile Phone Number").assertIsDisplayed()
            .performTextInput("+79871234567")
        composeTestRule.onNodeWithTag("Email Address").assertIsDisplayed()
            .performTextInput("qwe@mail.ru")
        composeTestRule.onNodeWithTag("Password").assertIsDisplayed()
            .performTextInput("not valid")
        composeTestRule.onNodeWithTag("signUpButton").assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("AlertError").assertIsDisplayed()
    }

    @Test
    fun emailNotValid(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Navigation.SignUpScreen){
                composable<Navigation.SignUpScreen> {
                    SignUpScreen(navController, vm)
                }
            }
        }
        composeTestRule.onNodeWithTag("UserName").assertIsDisplayed()
            .performTextInput("user name")
        composeTestRule.onNodeWithTag("Mobile Phone Number").assertIsDisplayed()
            .performTextInput("+79871234567")
        composeTestRule.onNodeWithTag("Email Address").assertIsDisplayed()
            .performTextInput("not valid")
        composeTestRule.onNodeWithTag("Password").assertIsDisplayed()
            .performTextInput("Password_ 123$")
        composeTestRule.onNodeWithTag("signUpButton").assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("AlertError").assertIsDisplayed()
    }

    @Test
    fun allFieldsFill(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Navigation.SignUpScreen){
                composable<Navigation.SignUpScreen> {
                    SignUpScreen(navController, vm)
                }
            }
        }
        composeTestRule.onNodeWithTag("UserName").assertIsDisplayed()
            .performTextInput("fill")
        composeTestRule.onNodeWithTag("Mobile Phone Number").assertIsDisplayed()
            .performTextInput("fill")
        composeTestRule.onNodeWithTag("Email Address").assertIsDisplayed()
            .performTextInput("fill")
        composeTestRule.onNodeWithTag("Password").assertIsDisplayed()
            .performTextInput("fill")
        composeTestRule.onNodeWithTag("signUpButton").assertIsEnabled()
    }

    @Test
    fun navigation(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Navigation.SignUpScreen){
                composable<Navigation.SignUpScreen> {
                    SignUpScreen(navController, vm)
                }
                composable<Navigation.StartupScreen> {
                    EmptyMenuScreen(navController)
                }
            }
        }
        composeTestRule.onNodeWithTag("UserName").assertIsDisplayed()
            .performTextInput("user name")
        composeTestRule.onNodeWithTag("Mobile Phone Number").assertIsDisplayed()
            .performTextInput("+79871234567")
        composeTestRule.onNodeWithTag("Email Address").assertIsDisplayed()
            .performTextInput("qwe@mail.ru")
        composeTestRule.onNodeWithTag("Password").assertIsDisplayed()
            .performTextInput("Password_ 123$")
        composeTestRule.onNodeWithTag("signUpButton").assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithTag("UserName").assertIsNotDisplayed()
    }
}