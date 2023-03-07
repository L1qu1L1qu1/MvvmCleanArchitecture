package com.example.mvvmcleanarchitecture.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcleanarchitecture.domain.Cat
import com.example.mvvmcleanarchitecture.domain.CatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(var catUseCase: CatUseCase): ViewModel() {
    var list = mutableStateListOf<Cat>()
    fun getCats(){
        list.clear()
        viewModelScope.launch {
            catUseCase.invoke().collect{
                list.add(it)
                println(it)
            }
        }
    }
}