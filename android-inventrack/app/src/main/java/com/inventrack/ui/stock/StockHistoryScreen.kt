package com.inventrack.ui.stock

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StockHistoryScreen(vm: com.inventrack.ui.stock.StockViewModel = hiltViewModel()) {
    val state by vm.ui.collectAsState()
    LaunchedEffect(true) { vm.loadHistory() }
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (state.loading) item { CircularProgressIndicator() }
        items(state.history) { it ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(it.productName ?: "-", style = MaterialTheme.typography.titleMedium)
                    Text("${it.type} ${it.quantity} - ${it.createdAt}")
                }
            }
        }
    }
}
