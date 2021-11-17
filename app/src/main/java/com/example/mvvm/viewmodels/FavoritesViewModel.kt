package com.example.mvvm.viewmodels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.models.Post
import com.example.mvvm.repository.FavoritesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val favoritesRepository: FavoritesRepository) : ViewModel() {
    val favoritesList = MutableLiveData<List<Post>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val isInternet = MutableLiveData<Boolean>()

    fun getAllPosts() {
        loading.value = true
        val response = favoritesRepository.getAllPosts()
        response.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                loading.value = false
                favoritesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                loading.value = false
                error.postValue(t.message)
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkInternet(context: Context) {
        isInternet.value = favoritesRepository.checkInternet(context)
    }
}