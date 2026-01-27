package com.example.cofeebreak.feature_app.presentation.Barista

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetBaristaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BaristaVM @Inject constructor(
    private val getBaristaUseCase: GetBaristaUseCase
): ViewModel() {
    private val _state = mutableStateOf(BaristaState())
    val state: State<BaristaState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val baristaList = getBaristaUseCase.invoke()
                _state.value = state.value.copy(
                    baristaList = baristaList
                )
            } catch (ex: Exception){
                _state.value = state.value.copy(
                    error = true
                )
                Log.e("supabase", ex.message.toString())
            }
        }
    }

    fun onEvent(event: BaristaEvent){
        when(event){
            BaristaEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = false
                )
            }
        }
    }
}