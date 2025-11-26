package com.inventrack.ui.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inventrack.core.Routes

@Composable
fun AnonymousDashboard(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Guest Dashboard") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Button(onClick = { navController.navigate(Routes.PRODUCTS) }) { Text("View Products") }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate(Routes.SCANNER) }) { Text("Scan Barcode") }
        }
    }
}
