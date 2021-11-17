package com.example.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.repository.CommentsRepository
import java.lang.IllegalArgumentException

class CommentsViewModelFactory(private val commentsRepository: CommentsRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(this.commentsRepository) as T
        } else {
            throw IllegalArgumentException("invalid viewModel class")
        }
    }
}