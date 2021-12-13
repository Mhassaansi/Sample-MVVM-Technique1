package com.ecommercemvvmpractice.di

import android.app.Application
import com.ecommercemvvmpractice.data.remote.ApiService
import com.ecommercemvvmpractice.data.repository.EcommerceRepository
import com.ecommercemvvmpractice.data.repository.EcommerceRepositoryImpl
import com.ecommercemvvmpractice.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideEcommerceRepo(stringUtils: StringUtils, apiService: ApiService):
            EcommerceRepository {
        return EcommerceRepositoryImpl(stringUtils, apiService)
    }
}