package com.example.mvvm.di

import com.example.mvvm.fragments.FavoritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoritesFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragment(): FavoritesFragment
}