package com.example.cofeebreak.feature_app.presentation.Additives

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetAdditivesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdditivesVM @Inject constructor(
    private val getAdditivesUseCase: GetAdditivesUseCase
): ViewModel() {
    private val _state = mutableStateOf(AdditivesState())
    val state: State<AdditivesState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val additivesList = getAdditivesUseCase.invoke()
                _state.value = state.value.copy(
                    additivesList = additivesList
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }

    fun onEvent(event: AdditivesEvent){
        when(event){
            AdditivesEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }
}