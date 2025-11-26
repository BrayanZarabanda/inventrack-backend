package com.inventrack.ui.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.core.Resource
import com.inventrack.domain.model.StockMovement
import com.inventrack.domain.usecase.GetStockHistoryUseCase
import com.inventrack.domain.usecase.RegisterEntryUseCase
import com.inventrack.domain.usecase.RegisterExitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StockUiState(val loading:Boolean=false, val history: List<StockMovement> = emptyList(), val error:String?=null)

@HiltViewModel
class StockViewModel @Inject constructor(
    private val getHistory: GetStockHistoryUseCase,
    private val registerEntry: RegisterEntryUseCase,
    private val registerExit: RegisterExitUseCase
): ViewModel() {
    private val _ui = MutableStateFlow(StockUiState())
    val ui: StateFlow<StockUiState> = _ui

    fun loadHistory() {
        _ui.value = _ui.value.copy(loading = true)
        viewModelScope.launch {
            when(val res = getHistory()) {
                is Resource.Success -> _ui.value = StockUiState(loading=false, history = res.data)
                is Resource.Error -> _ui.value = StockUiState(loading=false, error = res.message)
                else -> _ui.value = StockUiState(loading=false)
            }
        }
    }

    fun entry(productId:Int, qty:Int, warehouseId:Int?, note:String?, onComplete:(Resource<StockMovement>)->Unit = {}) {
        viewModelScope.launch {
            val res = registerEntry(productId, qty, warehouseId, note)
            onComplete(res)
            loadHistory()
        }
    }

    fun exit(productId:Int, qty:Int, warehouseId:Int?, note:String?, onComplete:(Resource<StockMovement>)->Unit = {}) {
        viewModelScope.launch {
            val res = registerExit(productId, qty, warehouseId, note)
            onComplete(res)
            loadHistory()
        }
    }
}
