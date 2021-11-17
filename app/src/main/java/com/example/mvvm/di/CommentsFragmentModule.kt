package com.example.mvvm.di

import com.example.mvvm.fragments.CommentsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class CommentsFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCommentsFragment(): CommentsFragment
}