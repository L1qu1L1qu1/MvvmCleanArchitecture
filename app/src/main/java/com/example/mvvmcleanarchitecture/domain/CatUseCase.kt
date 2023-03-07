package com.example.mvvmcleanarchitecture.domain

import com.example.mvvmcleanarchitecture.data.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatUseCase @Inject constructor(var catRepository: CatRepository){
    fun invoke():Flow<Cat>{
        return catRepository.getCats()
    }
}