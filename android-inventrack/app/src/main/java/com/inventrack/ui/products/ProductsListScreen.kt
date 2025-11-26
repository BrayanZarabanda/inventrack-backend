package com.inventrack.ui.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inventrack.domain.model.Product
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.inventrack.core.Routes

@Composable
fun ProductsListScreen(navController: NavController, vm: ProductsViewModel = hiltViewModel()) {
    val products by vm.productsFlow.collectAsState()
    var search by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(value = search, onValueChange = { search = it }, label = { Text("Buscar...") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { vm.refresh(search) }, modifier = Modifier.fillMaxWidth()) {
            Text("Refrescar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products) { product ->
                ProductRow(product = product, onClick = {
                    navController.navigate(Routes.PRODUCT_DETAIL.replace("{productId}", product.id.toString()))
                })
            }
        }
    }
}

@Composable
fun ProductRow(product: Product, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .clickable { onClick() }) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(product.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Qty: ${product.quantity}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
