package com.example.mvvm.viewmodels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.Comment
import com.example.mvvm.repository.CommentsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommentsViewModel @Inject constructor(private val commentsRepository: CommentsRepository) : ViewModel() {
    val commentsList = MutableLiveData<List<Comment>>()
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<String>()
    val isInternetAvailable = MutableLiveData<Boolean>()


    fun getAllComments() {
        loadingState.value = true
        viewModelScope.launch {
            val response = commentsRepository.getAllComments()
            response.enqueue(object : Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    loadingState.value = false
                    if (response.isSuccessful) {
                        commentsList.value = response.body()
                    } else {
                        errorState.value = response.errorBody().toString()
                    }
                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    loadingState.value = false
                    errorState.value = t.message
                }

            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkInternet(context: Context) {
        isInternetAvailable.value = commentsRepository.checkInternet(context)
    }
}