package com.inventrack.core.di

import com.inventrack.domain.usecase.*
import com.inventrack.domain.repository.AuthRepository
import com.inventrack.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepo: AuthRepository): LoginUseCase = LoginUseCase(authRepo)

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepo: AuthRepository): RegisterUseCase = RegisterUseCase(authRepo)

    @Provides
    @Singleton
    fun provideGetProductsUseCase(productRepo: ProductRepository): GetProductsUseCase = GetProductsUseCase(productRepo)

    @Provides
    @Singleton
    fun provideCreateProductUseCase(productRepo: ProductRepository): CreateProductUseCase = CreateProductUseCase(productRepo)

    @Provides
    @Singleton
    fun provideUpdateProductUseCase(productRepo: ProductRepository): UpdateProductUseCase = UpdateProductUseCase(productRepo)

    @Provides
    @Singleton
    fun provideDeleteProductUseCase(productRepo: ProductRepository): DeleteProductUseCase = DeleteProductUseCase(productRepo)

    @Provides
    @Singleton
    fun provideGetWarehousesUseCase(repo: com.inventrack.domain.repository.WarehouseRepository): com.inventrack.domain.usecase.GetWarehousesUseCase =
        com.inventrack.domain.usecase.GetWarehousesUseCase(repo)

    @Provides
    @Singleton
    fun provideCreateWarehouseUseCase(repo: com.inventrack.domain.repository.WarehouseRepository): com.inventrack.domain.usecase.CreateWarehouseUseCase =
        com.inventrack.domain.usecase.CreateWarehouseUseCase(repo)

    @Provides
    @Singleton
    fun provideRegisterEntryUseCase(repo: com.inventrack.domain.repository.StockRepository): com.inventrack.domain.usecase.RegisterEntryUseCase =
        com.inventrack.domain.usecase.RegisterEntryUseCase(repo)

    @Provides
    @Singleton
    fun provideRegisterExitUseCase(repo: com.inventrack.domain.repository.StockRepository): com.inventrack.domain.usecase.RegisterExitUseCase =
        com.inventrack.domain.usecase.RegisterExitUseCase(repo)

    @Provides
    @Singleton
    fun provideGetStockHistoryUseCase(repo: com.inventrack.domain.repository.StockRepository): com.inventrack.domain.usecase.GetStockHistoryUseCase =
        com.inventrack.domain.usecase.GetStockHistoryUseCase(repo)
}
