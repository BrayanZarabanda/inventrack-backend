package com.inventrack.core

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val RECOVER_PASSWORD = "recover_password"
    const val RESET_PASSWORD = "reset_password/{token}"
    const val HOME = "home"
    const val PRODUCTS = "products"
    const val PRODUCT_DETAIL = "product_detail/{productId}"
    const val CREATE_PRODUCT = "create_product"
    const val EDIT_PRODUCT = "edit_product/{productId}"
    const val SCANNER = "scanner"
    const val WAREHOUSES = "warehouses"
    const val CREATE_WAREHOUSE = "create_warehouse"
    const val EDIT_WAREHOUSE = "edit_warehouse/{warehouseId}"
    const val STOCK_ENTRY = "stock_entry"
    const val STOCK_EXIT = "stock_exit"
    const val STOCK_HISTORY = "stock_history"
    const val PROFILE = "profile"
}
