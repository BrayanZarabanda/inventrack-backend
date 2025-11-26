package com.inventrack.ui.warehouse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.core.Resource
import com.inventrack.domain.model.Warehouse
import com.inventrack.domain.usecase.CreateWarehouseUseCase
import com.inventrack.domain.usecase.GetWarehousesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WarehouseUiState(val loading:Boolean=false, val warehouses: List<Warehouse> = emptyList(), val error:String?=null)

@HiltViewModel
class WarehouseViewModel @Inject constructor(
    private val getWarehouses: GetWarehousesUseCase,
    private val createWarehouse: CreateWarehouseUseCase
): ViewModel() {
    private val _ui = MutableStateFlow(WarehouseUiState())
    val ui: StateFlow<WarehouseUiState> = _ui

    fun load() {
        _ui.value = _ui.value.copy(loading = true)
        viewModelScope.launch {
            when(val res = getWarehouses()) {
                is Resource.Success -> _ui.value = WarehouseUiState(loading=false, warehouses = res.data)
                is Resource.Error -> _ui.value = WarehouseUiState(loading=false, error = res.message)
                else -> _ui.value = WarehouseUiState(loading=false)
            }
        }
    }

    fun create(name:String, address:String?, onComplete:(Resource<com.inventrack.domain.model.Warehouse>)->Unit = {}) {
        viewModelScope.launch {
            val res = createWarehouse(name, address)
            onComplete(res)
            if (res is Resource.Success) load()
        }
    }
}
