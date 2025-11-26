package com.inventrack.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel(), navController: androidx.navigation.NavController? = null) {
    val state by viewModel.ui.collectAsState()
    val scaffoldState = rememberScaffoldState()
    androidx.compose.material3.Scaffold(topBar = { androidx.compose.material3.TopAppBar(title = { androidx.compose.material3.Text("Profile") }) }, modifier = Modifier.fillMaxSize()) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            if (state.loading) {
                androidx.compose.material3.CircularProgressIndicator()
            }
            state.user?.let { user ->
                Text("Name: ${user.name}")
                Text("Email: ${user.email}")
                Text("Role: ${user.role}")
            } ?: Text("No profile loaded")

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.logout {
                    navController?.navigate(com.inventrack.core.Routes.LOGIN) {
                        popUpTo(com.inventrack.core.Routes.LOGIN) { inclusive = true }
                    }
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Logout") }
        }
    }
    LaunchedEffect(true) { viewModel.loadProfile() }
}
