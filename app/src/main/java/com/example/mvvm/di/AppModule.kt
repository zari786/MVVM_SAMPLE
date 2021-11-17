package com.example.mvvm.di

import android.content.Context
import com.example.mvvm.api.CommentsApi
import com.example.mvvm.api.RetrofitService
import com.example.mvvm.api.RetrofitService1
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(val context: Context) {


    @Provides
    fun provideContext():Context{
        return context
    }


    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://howtodoandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService1(): RetrofitService1 {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService1::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService2(): CommentsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/posts/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CommentsApi::class.java)
    }
}