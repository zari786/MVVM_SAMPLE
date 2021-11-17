package com.example.mvvm.api

import com.example.mvvm.models.Comment
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CommentsApi {

    @GET("comments")
    fun getAllComments():Call<List<Comment>>


    /*companion object {
        fun getInstance(): CommentsApi {
            var instance: CommentsApi? = null

            if (instance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/posts/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                instance = retrofit.create(CommentsApi::class.java)
            }
            return instance!!
        }
    }*/
}