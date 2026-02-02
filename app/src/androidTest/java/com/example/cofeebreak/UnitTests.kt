package com.example.cofeebreak

import com.example.cofeebreak.FakeRepository.FakeAuthRepository
import com.example.cofeebreak.FakeRepository.FakeProfileRepository
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpEvent
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpVM
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class SignUpVMTest {

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
    fun emailValid_passwordValid_nameAndPhoneNotEmpty_success() = runTest {
        vm.onEvent(SignUpEvent.EnteredName("Alex"))
        vm.onEvent(SignUpEvent.EnteredPhone("123456789"))
        vm.onEvent(SignUpEvent.EnteredEmailAddress("test@mail.com"))
        vm.onEvent(SignUpEvent.EnteredPassword("Strong 1!"))

        vm.onEvent(SignUpEvent.SignUp)
//        advanceUntilIdle()

        assertTrue(vm.state.value.isComplete)
        assertFalse(vm.state.value.error)
        assertFalse(vm.state.value.passwordError)
    }

    @Test
    fun emailValid_passwordInvalid_nameAndPhoneNotEmpty_passwordError() = runTest {
        vm.onEvent(SignUpEvent.EnteredName("Alex"))
        vm.onEvent(SignUpEvent.EnteredPhone("123456789"))
        vm.onEvent(SignUpEvent.EnteredEmailAddress("test@mail.com"))
        vm.onEvent(SignUpEvent.EnteredPassword("weak"))

        vm.onEvent(SignUpEvent.SignUp)
//        delay(5000)
//        advanceUntilIdle()

        assertTrue(vm.state.value.passwordError)
        assertFalse(vm.state.value.isComplete)
    }

    @Test
    fun emailInvalid_passwordValid_nameAndPhoneNotEmpty_emailError() = runTest {
        vm.onEvent(SignUpEvent.EnteredName("Alex"))
        vm.onEvent(SignUpEvent.EnteredPhone("123456789"))
        vm.onEvent(SignUpEvent.EnteredEmailAddress("wrong-email"))
        vm.onEvent(SignUpEvent.EnteredPassword("Strong 1!"))

        vm.onEvent(SignUpEvent.SignUp)
//        delay(5000)
//        advanceUntilIdle()

        assertTrue(vm.state.value.error)
        assertFalse(vm.state.value.isComplete)
    }
}
