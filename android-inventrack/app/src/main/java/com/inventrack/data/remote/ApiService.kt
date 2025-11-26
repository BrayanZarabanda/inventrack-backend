package com.inventrack.data.remote

import com.inventrack.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<AuthResponseDto>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto): Response<AuthResponseDto>

    @GET("auth/profile")
    suspend fun profile(): Response<UserDto>

    @POST("auth/recover")
    suspend fun recoverPassword(@Body emailDto: EmailDto): Response<Void>

    @POST("auth/reset")
    suspend fun resetPassword(@Body resetDto: ResetPasswordDto): Response<Void>

    @GET("products")
    suspend fun getProducts(@Query("q") query: String? = null,
                            @Query("category") category: String? = null,
                            @Query("warehouse") warehouseId: Int? = null): Response<List<ProductDto>>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<ProductDto>

    @POST("products")
    suspend fun createProduct(@Body dto: CreateProductDto): Response<ProductDto>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body dto: UpdateProductDto): Response<ProductDto>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Void>

    // Warehouses
    @GET("warehouses")
    suspend fun getWarehouses(): Response<List<WarehouseDto>>

    @POST("warehouses")
    suspend fun createWarehouse(@Body dto: CreateWarehouseDto): Response<WarehouseDto>

    @PUT("warehouses/{id}")
    suspend fun updateWarehouse(@Path("id") id: Int, @Body dto: CreateWarehouseDto): Response<WarehouseDto>

    @DELETE("warehouses/{id}")
    suspend fun deleteWarehouse(@Path("id") id: Int): Response<Void>

    // Stock movements
    @POST("stock/entry")
    suspend fun registerEntry(@Body dto: CreateStockDto): Response<StockMovementDto>

    @POST("stock/exit")
    suspend fun registerExit(@Body dto: CreateStockDto): Response<StockMovementDto>

    @GET("stock/history")
    suspend fun getStockHistory(): Response<List<StockMovementDto>>
}
