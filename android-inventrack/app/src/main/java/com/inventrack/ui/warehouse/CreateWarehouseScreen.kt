package com.inventrack.ui.warehouse

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateWarehouseScreen(navController: NavController, vm: com.inventrack.ui.warehouse.WarehouseViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Create Warehouse", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            vm.create(name, address) { res ->
                if (res is com.inventrack.core.Resource.Success) {
                    navController.popBackStack()
                } else if (res is com.inventrack.core.Resource.Error) {
                    // TODO: show error
                }
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Create") }
    }
}
