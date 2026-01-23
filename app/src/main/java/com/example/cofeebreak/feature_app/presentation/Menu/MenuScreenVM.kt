package com.example.cofeebreak.feature_app.presentation.Menu

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeListUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetUserAvatarUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetUserNameUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuScreenVM @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase,
    private val getCoffeeListUseCase: GetCoffeeListUseCase,
    private val getUserAvatarUseCase: GetUserAvatarUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MenuScreenState())
    val state: State<MenuScreenState> = _state

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadCurrentUserIdUseCase.invoke().id.orEmpty()
                val name = getUserNameUseCase.invoke(userId).name
                val avatar = getUserAvatarUseCase.invoke(Profile(user_id = userId)).avatar_url
                val coffeeList = getCoffeeListUseCase()

                _state.value = _state.value.copy(
                    name = name,
                    avatar_url = avatar,
                    coffeeList = coffeeList
                )
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    serverError = true
                )
                Log.e("MENU_VM", e.message.toString())
            }
        }
    }

    fun onEvent(event: MenuScreenEvent){
        when(event){
            MenuScreenEvent.ChangeError -> {
                _state.value = state.value.copy(
                    serverError = false
                )
            }
        }
    }
}