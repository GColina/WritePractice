package com.example.writepractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.writepractice.model.LettersModel
import com.example.writepractice.model.LettersProvider

class LettersViewModel : ViewModel() {
    val lettersModel = MutableLiveData<LettersModel>()

    fun randomLetters(){
        val currentLetters : LettersModel  = LettersProvider.nextLetter()
        lettersModel.postValue(currentLetters)
    }
}