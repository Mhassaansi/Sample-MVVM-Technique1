package com.ecommercemvvmpractice.di

import com.ecommercemvvmpractice.data.remote.ApiResponseCallAdapterFactory
import com.ecommercemvvmpractice.data.remote.ApiService
import com.example.smartfoodorderingmvvm.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {





    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
                var newRequest = request.newBuilder().header("Authorization",
                    AppConstant.API_KEY)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logging)
            .build()
    }
    @Singleton
    @Provides

    fun providesOkHttpClientWithoutAuth(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesSmartFoodApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}