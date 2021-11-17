package com.example.mvvm.viewmodels

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.repository.MainRepository
import com.example.mvvm.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val isIntentAvailable = MutableLiveData<Boolean>()


    fun getAllMovies() {
        loading.postValue(true)
        val response = mainRepository.getAllMovies()

        response.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                loading.postValue(false)
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                loading.postValue(false)
                errorMessage.postValue(t.message)
                Log.d("Error", "${t.message}")
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkInternet(context: Context) {
        isIntentAvailable.value = mainRepository.checkInternet(context)
    }
}