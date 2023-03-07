package com.example.mvvmcleanarchitecture.data

import com.example.mvvmcleanarchitecture.domain.Cat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepository{
    fun getCats(): Flow<Cat> = flow{
        var cats = listOf<Cat>(Cat("Cat1"), Cat("Cat2"), Cat("Cat3"), Cat("Cat4"), Cat("Cat5"))
        for (i in cats){
            delay(600)
            emit(i)
        }
    }
}