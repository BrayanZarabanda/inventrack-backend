package com.inventrack.ui.products

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ProductDetailScreen(productId: Int, navController: NavController, vm: ProductsViewModel = hiltViewModel()) {
    val product = vm.productsFlow.collectAsState().value.firstOrNull { it.id == productId }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        product?.let {
            Text(it.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(it.description ?: "-")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Quantity: ${it.quantity}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("edit_product/${it.id}") }) { Text("Edit") }
        } ?: Text("Product not found")
    }
}
