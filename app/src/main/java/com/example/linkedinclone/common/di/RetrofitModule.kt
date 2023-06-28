package com.example.linkedinclone.common.di

import com.example.linkedinclone.BuildConfig
import com.example.linkedinclone.common.network.ApiClient
import com.example.linkedinclone.common.network.ApiServices
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.MINUTES)
        .connectTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
        .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideApiClient(apiServices: ApiServices): ApiClient = ApiClient(apiServices)

}