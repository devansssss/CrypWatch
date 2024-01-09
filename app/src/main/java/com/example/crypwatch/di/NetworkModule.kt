package com.example.crypwatch.di

import com.example.crypwatch.api.CrypWatchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.coinpaprika.com/v1/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesCrypWatchAPI(retrofit: Retrofit): CrypWatchAPI{
        return retrofit.create(CrypWatchAPI::class.java)
    }
}