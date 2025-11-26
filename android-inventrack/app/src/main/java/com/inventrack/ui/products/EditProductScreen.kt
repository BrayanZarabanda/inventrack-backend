package com.inventrack.ui.products

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun EditProductScreen(productId: Int, navController: NavController, productsVm: com.inventrack.ui.products.ProductsViewModel = hiltViewModel(), editorVm: com.inventrack.ui.products.ProductEditorViewModel = hiltViewModel()) {
    val product = productsVm.productsFlow.collectAsState().value.firstOrNull { it.id == productId }
    var name by remember { mutableStateOf(product?.name ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var quantity by remember { mutableStateOf(product?.quantity?.toString() ?: "0") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Edit Product", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val qty = quantity.toIntOrNull() ?: 0
            val prod = com.inventrack.domain.model.Product(id = productId, name = name, description = description, sku = product?.sku, barcode = product?.barcode, category = product?.category, quantity = qty, warehouseId = product?.warehouseId, imageUrl = product?.imageUrl)
            editorVm.update(productId, prod) { res -> if (res is com.inventrack.core.Resource.Success) navController.popBackStack() }
        }, modifier = Modifier.fillMaxWidth()) { Text("Save") }
    }
}
