package com.inventrack.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inventrack.core.Routes
import com.inventrack.ui.auth.LoginScreen
import com.inventrack.ui.products.ProductsListScreen
import com.inventrack.ui.home.AdminDashboard

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.LOGIN) {
                    composable(Routes.LOGIN) { LoginScreen(navController) }
                    composable(Routes.REGISTER) { RegisterScreen(navController) }
                    composable(Routes.RECOVER_PASSWORD) { RecoverPasswordScreen(navController) }
                    composable(Routes.RESET_PASSWORD, arguments = listOf(navArgument("token") { type = NavType.StringType })) { backStack ->
                        val token = backStack.arguments?.getString("token") ?: ""
                        ResetPasswordScreen(token, navController)
                    }
                    composable(Routes.HOME) { com.inventrack.ui.home.HomeContainer(navController) }
                    composable(Routes.PRODUCTS) { ProductsListScreen(navController) }
                    composable(Routes.PRODUCT_DETAIL, arguments = listOf(navArgument("productId") { type = NavType.IntType })) { backStack ->
                        val id = backStack.arguments?.getInt("productId") ?: 0
                        ProductDetailScreen(id, navController)
                    }
                    composable(Routes.CREATE_PRODUCT) { CreateProductScreen(navController) }
                    composable(Routes.EDIT_PRODUCT, arguments = listOf(navArgument("productId") { type = NavType.IntType })) { backStack ->
                        val id = backStack.arguments?.getInt("productId") ?: 0
                        EditProductScreen(id, navController)
                    }
                    composable(Routes.SCANNER) { ScanBarcodeScreen(onBarcodeFound = { code -> navController.navigate(Routes.PRODUCTS + "?q=$code") }) }
                    composable(Routes.WAREHOUSES) { WarehouseListScreen(navController) }
                    composable(Routes.CREATE_WAREHOUSE) { CreateWarehouseScreen(navController) }
                    composable(Routes.EDIT_WAREHOUSE, arguments = listOf(navArgument("warehouseId") { type = NavType.IntType })) { backStack ->
                        val id = backStack.arguments?.getInt("warehouseId") ?: 0
                        EditWarehouseScreen(id, navController)
                    }
                    composable(Routes.STOCK_ENTRY) { StockEntryScreen() }
                    composable(Routes.STOCK_EXIT) { StockExitScreen() }
                    composable(Routes.STOCK_HISTORY) { StockHistoryScreen() }
                    composable(Routes.PROFILE) { ProfileScreen() }
                    composable("user_home") { UserDashboard(navController) }
                    composable("anon_home") { AnonymousDashboard(navController) }
                }
            }
        }
    }
}
