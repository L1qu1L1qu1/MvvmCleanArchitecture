package com.example.mvvmcleanarchitecture.data.di

import com.example.mvvmcleanarchitecture.data.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CatModule {
    @Provides
    fun createCat():CatRepository{
        return CatRepository()
    }
}