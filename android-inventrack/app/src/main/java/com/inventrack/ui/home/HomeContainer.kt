package com.inventrack.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.inventrack.core.DataStoreManager
import com.inventrack.core.Roles
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeContainer(navController: NavController) {
    val homeVm: HomeViewModel = hiltViewModel()
    val dataStoreManager = homeVm.dataStoreManager
    // Read role synchronously (small blocking call during navigation) - OK for simple skeleton
    val role = runBlocking { dataStoreManager.roleFlow().first() } ?: Roles.ANONYMOUS
    when(role) {
        Roles.ADMIN -> AdminDashboard(navController)
        Roles.USER -> UserDashboard(navController)
        else -> AnonymousDashboard(navController)
    }
}

// Helper viewmodel to obtain DataStoreManager via Hilt in composable; alternate could inject directly
@HiltViewModel
class HomeViewModel @Inject constructor(val dataStoreManager: DataStoreManager) : androidx.lifecycle.ViewModel()
