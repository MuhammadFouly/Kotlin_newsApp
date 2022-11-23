package com.example.kotlin_newsapp.di

import com.example.kotlin_newsapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideBaseUrl()= BASE_URL

    @Provides
    @Singleton
    fun provideLogginInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    @Provides
    @Singleton
    fun providOkHttpClient(logging:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient):NewsAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            // TODO add the client
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }
}