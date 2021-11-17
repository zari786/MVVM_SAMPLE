package com.example.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.repository.FavoritesRepository
import java.lang.IllegalArgumentException

class FavoritesViewModelFactory(private val favoritesRepository: FavoritesRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            FavoritesViewModel(this.favoritesRepository) as T
        } else {
            throw IllegalArgumentException("Invalid ViewModel found")
        }
    }
}