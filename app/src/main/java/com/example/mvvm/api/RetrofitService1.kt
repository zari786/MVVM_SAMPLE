package com.example.mvvm.api

import com.example.mvvm.models.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService1 {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    /*companion object{
        fun getInstance(): RetrofitService1 {
            var retrofitService1: RetrofitService1? = null
            if (retrofitService1 == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService1 = retrofit.create(RetrofitService1::class.java)
            }
            return retrofitService1!!
        }
    }*/
}