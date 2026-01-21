package com.example.cofeebreak.feature_app.presentation.Menu

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeListUseCase
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
    private val getCoffeeListUseCase: GetCoffeeListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MenuScreenState())
    val state: State<MenuScreenState> = _state

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadCurrentUserIdUseCase().id.orEmpty()
                val name = getUserNameUseCase(userId).name
                val coffeeList = getCoffeeListUseCase()

                _state.value = _state.value.copy(
                    name = name,
                    coffeeList = coffeeList
                )
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    error = "Ошибка запроса к серверу"
                )
                Log.e("MENU_VM", e.message.toString())
            }
        }
    }

    fun onEvent(event: MenuScreenEvent){
        when(event){
            MenuScreenEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = ""
                )
            }
        }
    }
}