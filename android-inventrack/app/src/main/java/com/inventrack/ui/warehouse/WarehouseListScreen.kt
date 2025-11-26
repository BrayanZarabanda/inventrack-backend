package com.inventrack.ui.warehouse

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.inventrack.ui.warehouse.WarehouseViewModel

@Composable
fun WarehouseListScreen(navController: NavController, vm: WarehouseViewModel = hiltViewModel()) {
    val state by vm.ui.collectAsState()
    LaunchedEffect(true) { vm.load() }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Warehouses", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate(com.inventrack.core.Routes.CREATE_WAREHOUSE) }) { Text("Create Warehouse") }
        Spacer(modifier = Modifier.height(8.dp))
        if (state.loading) CircularProgressIndicator()
        state.warehouses.forEach { w ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(w.name, style = MaterialTheme.typography.titleMedium)
                    Text(w.address ?: "-")
                }
            }
        }
    }
}
