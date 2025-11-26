package com.inventrack.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inventrack.core.Resource
import com.inventrack.domain.model.Product
import com.inventrack.domain.usecase.CreateProductUseCase
import com.inventrack.domain.usecase.UpdateProductUseCase
import com.inventrack.domain.usecase.DeleteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductEditorState(val loading:Boolean=false, val error:String?=null)

@HiltViewModel
class ProductEditorViewModel @Inject constructor(
    private val createUseCase: CreateProductUseCase,
    private val updateUseCase: UpdateProductUseCase,
    private val deleteUseCase: DeleteProductUseCase
): ViewModel() {
    private val _ui = MutableStateFlow(ProductEditorState())
    val ui: StateFlow<ProductEditorState> = _ui

    fun create(product: Product, onComplete:(Resource<Product>)->Unit = {}) {
        _ui.value = ProductEditorState(loading = true)
        viewModelScope.launch {
            val res = createUseCase(product)
            _ui.value = ProductEditorState(loading = false, error = if (res is Resource.Error) res.message else null)
            onComplete(res)
        }
    }

    fun update(id:Int, product: Product, onComplete:(Resource<Product>)->Unit = {}) {
        _ui.value = ProductEditorState(loading = true)
        viewModelScope.launch {
            val res = updateUseCase(id, product)
            _ui.value = ProductEditorState(loading = false, error = if (res is Resource.Error) res.message else null)
            onComplete(res)
        }
    }

    fun delete(id:Int, onComplete:(Resource<Unit>)->Unit = {}) {
        _ui.value = ProductEditorState(loading = true)
        viewModelScope.launch {
            val res = deleteUseCase(id)
            _ui.value = ProductEditorState(loading = false, error = if (res is Resource.Error) res.message else null)
            onComplete(res)
        }
    }
}
