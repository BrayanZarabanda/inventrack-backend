package com.inventrack.core.di

import android.content.Context
import com.inventrack.data.local.db.AppDatabase
import com.inventrack.data.remote.ApiService
import com.inventrack.data.repository.AuthRepositoryImpl
import com.inventrack.data.repository.ProductRepositoryImpl
import com.inventrack.domain.repository.AuthRepository
import com.inventrack.domain.repository.ProductRepository
import com.inventrack.core.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase = AppDatabase.getInstance(appContext)

    @Provides @Singleton
    fun provideAuthRepository(api: ApiService, dataStoreManager: com.inventrack.core.DataStoreManager): com.inventrack.domain.repository.AuthRepository =
        com.inventrack.data.repository.AuthRepositoryImpl(api, dataStoreManager)

    @Provides @Singleton
    fun provideProductRepository(api: ApiService, db: AppDatabase): com.inventrack.domain.repository.ProductRepository =
        com.inventrack.data.repository.ProductRepositoryImpl(api, db)
    
    @Provides @Singleton
    fun provideWarehouseRepository(api: ApiService): com.inventrack.domain.repository.WarehouseRepository =
        com.inventrack.data.repository.WarehouseRepositoryImpl(api)

    @Provides @Singleton
    fun provideStockRepository(api: ApiService): com.inventrack.domain.repository.StockRepository =
        com.inventrack.data.repository.StockRepositoryImpl(api)
}
