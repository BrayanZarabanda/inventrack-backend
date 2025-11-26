package com.inventrack.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.core.Resource
import com.inventrack.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(val loading:Boolean=false, val error:String?=null)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(name:String?, email:String, password:String, onSuccess:()->Unit = {}) {
        _uiState.value = RegisterUiState(loading = true)
        viewModelScope.launch {
            when(val res = registerUseCase(name, email, password)) {
                is Resource.Success -> {
                    _uiState.value = RegisterUiState(loading = false)
                    onSuccess()
                }
                is Resource.Error -> {
                    _uiState.value = RegisterUiState(loading = false, error = res.message)
                }
                else -> _uiState.value = RegisterUiState(loading = false, error = "Unknown error")
            }
        }
    }
}
