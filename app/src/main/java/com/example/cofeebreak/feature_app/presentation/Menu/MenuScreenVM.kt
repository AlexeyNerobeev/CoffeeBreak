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
    savedStateHandle: SavedStateHandle,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase,
    private val getCoffeeListUseCase: GetCoffeeListUseCase
): ViewModel() {
    private val _state = mutableStateOf(MenuScreenState())
    val state: State<MenuScreenState> = _state
    //val t = savedStateHandle.toRoute<Navigation.MenuScreen>()

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                _state.value = state.value.copy(
//                    name = getUserNameUseCase.invoke(loadCurrentUserIdUseCase.invoke().id.toString()).name,
//                    coffeeList = getCoffeeListUseCase.invoke()
//                )
//            } catch (ex: Exception){
//                Log.e("supabase", ex.message.toString())
//            }
//        }
//    }

    fun onEvent(event: MenuScreenEvent){
        when(event){
            MenuScreenEvent.GetDrinks -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        _state.value = state.value.copy(
                            coffeeList = getCoffeeListUseCase.invoke()
                        )
                    }catch (ex: Exception){
                        Log.e("supabase", ex.message.toString())
                    }
                }
            }
            MenuScreenEvent.GetUserName -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        _state.value = state.value.copy(
                            userId = loadCurrentUserIdUseCase.invoke().id,
                            name = getUserNameUseCase.invoke(state.value.userId.toString()).name
                        )
                    } catch (ex: Exception){
                        Log.e("supabase", ex.message.toString())
                    }
                }
            }
        }
    }
}