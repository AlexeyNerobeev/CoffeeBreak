package com.example.cofeebreak.feature_app.presentation.Redeem

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetRedeemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class RedeemVM @Inject constructor(
    private val getRedeemUseCase: GetRedeemUseCase
): ViewModel() {
    private val _state = mutableStateOf(RedeemState())
    val state: State<RedeemState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val redeemList = getRedeemUseCase.invoke()
                val formattedList = redeemList.map { redeem ->
                    redeem.copy(
                        really_up_to = redeem.really_up_to.formatDate()
                    )
                }
                _state.value = state.value.copy(
                    redeemList = formattedList,
                    isLoaded = true
                )
            } catch (ex: Exception){
                _state.value = state.value.copy(
                    serverError = true
                )
                Log.e("supabase", ex.message.toString())
            }
        }
    }

    private fun String.formatDate(): String {
        return LocalDate.parse(this)
            .format(DateTimeFormatter.ofPattern("dd.MM.yy"))
    }

    fun onEvent(event: RedeemEvent){
        when(event){
            RedeemEvent.ChangeError -> {
                _state.value = state.value.copy(
                    serverError = false
                )
            }
        }
    }
}