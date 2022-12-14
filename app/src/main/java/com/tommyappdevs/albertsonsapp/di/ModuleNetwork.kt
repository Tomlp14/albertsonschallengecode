package com.tommyappdevs.albertsonsapp.di

import com.tommyappdevs.albertsonsapp.model.newtork.AcronymService
import com.tommyappdevs.albertsonsapp.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ModuleNetwork {

    @Provides
    fun provideService(retrofit: Retrofit): AcronymService =
        retrofit.create(AcronymService::class.java)

    @Provides
    fun provideRetrofit(client: OkHttpClient,
                        converterFactory: Converter.Factory) =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()


    @Provides
    fun provideOkHttpClient(interceptor: Interceptor) =
        OkHttpClient.Builder()
            .addInterceptor(
                interceptor
            )
            .build()

    @Provides
    fun provideConverterFactory():Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun provideInterceptor():Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
}