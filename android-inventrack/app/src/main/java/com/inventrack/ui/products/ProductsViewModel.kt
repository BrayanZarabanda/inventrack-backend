package com.inventrack.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.core.Resource
import com.inventrack.domain.model.Product
import com.inventrack.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase
) : ViewModel() {

    val productsFlow: StateFlow<List<Product>> = getProducts.local()
        .map { it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun refresh(query:String? = null, onComplete:(Resource<List<Product>>)->Unit = {}) {
        viewModelScope.launch {
            val res = getProducts.remote(query)
            onComplete(res)
        }
    }
}
