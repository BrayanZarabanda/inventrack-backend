package com.inventrack.ui.stock

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StockExitScreen(vm: com.inventrack.ui.stock.StockViewModel = hiltViewModel()) {
    var productId by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Stock Exit", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = productId, onValueChange = { productId = it }, label = { Text("Product ID") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = qty, onValueChange = { qty = it }, label = { Text("Quantity") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val pid = productId.toIntOrNull() ?: return@Button
            val q = qty.toIntOrNull() ?: return@Button
            vm.exit(pid, q, null, null) { res -> /* handle result */ }
        }, modifier = Modifier.fillMaxWidth()) { Text("Register Exit") }
    }
}
