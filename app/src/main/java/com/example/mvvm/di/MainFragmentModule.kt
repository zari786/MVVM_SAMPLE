package com.example.mvvm.di

import com.example.mvvm.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentModule {


    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}