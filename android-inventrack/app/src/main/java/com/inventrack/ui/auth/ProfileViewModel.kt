package com.inventrack.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.domain.model.User
import com.inventrack.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(val loading:Boolean=false, val user: User?=null, val error:String?=null)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _ui = MutableStateFlow(ProfileUiState())
    val ui: StateFlow<ProfileUiState> = _ui

    fun loadProfile() {
        _ui.value = ProfileUiState(loading = true)
        viewModelScope.launch {
            when(val res = authRepository.fetchProfile()) {
                is com.inventrack.core.Resource.Success -> _ui.value = ProfileUiState(user = res.data)
                is com.inventrack.core.Resource.Error -> _ui.value = ProfileUiState(error = res.message)
                else -> _ui.value = ProfileUiState()
            }
        }
    }

    fun logout(onComplete:()->Unit = {}) {
        viewModelScope.launch {
            authRepository.logout()
            onComplete()
        }
    }
}
