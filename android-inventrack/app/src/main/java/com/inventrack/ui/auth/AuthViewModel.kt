package com.inventrack.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.core.Resource
import com.inventrack.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(val loading:Boolean=false, val error:String?=null)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email:String, password:String, onSuccess:()->Unit = {}) {
        _uiState.value = AuthUiState(loading = true)
        viewModelScope.launch {
            when(val res = loginUseCase(email, password)) {
                is Resource.Success -> {
                    _uiState.value = AuthUiState(loading = false)
                    onSuccess()
                }
                is Resource.Error -> {
                    _uiState.value = AuthUiState(loading = false, error = res.message)
                }
                else -> {
                    _uiState.value = AuthUiState(loading = false, error = "Unknown error")
                }
            }
        }
    }
}
