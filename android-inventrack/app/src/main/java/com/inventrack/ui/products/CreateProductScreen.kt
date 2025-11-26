package com.inventrack.ui.products

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateProductScreen(navController: NavController, editorVm: com.inventrack.ui.products.ProductEditorViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("0") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Create Product", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val qty = quantity.toIntOrNull() ?: 0
            val product = com.inventrack.domain.model.Product(id = 0, name = name, description = description, sku = null, barcode = null, category = null, quantity = qty, warehouseId = null, imageUrl = null)
            editorVm.create(product) { res ->
                if (res is com.inventrack.core.Resource.Success) navController.popBackStack()
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Create") }
    }
}
