package com.ak.di

import com.ak.BuildConfig
import com.ak.data.remote.ApiInterface
import com.ak.data.repository.AKRepository
import com.ak.data.repository.AKRepositoryImpl
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
object AppModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val logging = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.setLevel(logging)
        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRepository(api: ApiInterface): AKRepository {
        return AKRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

}