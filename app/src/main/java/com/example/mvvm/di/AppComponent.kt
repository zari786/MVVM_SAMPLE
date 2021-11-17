package com.example.mvvm.di

import com.example.mvvm.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainFragmentModule::class,
        FavoritesFragmentModule::class,
        CommentsFragmentModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(application: MyApplication)
}