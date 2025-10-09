package com.example.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSomething(): String {
        return "Hello, Hilt!"
    }

}