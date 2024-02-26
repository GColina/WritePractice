package com.gcolina.writepractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gcolina.writepractice.model.LettersModel
import com.gcolina.writepractice.model.LettersProvider

class LettersViewModel : ViewModel() {
    val lettersModel = MutableLiveData<LettersModel>()

    fun randomLetters(){
        val currentLetters : LettersModel  = LettersProvider.nextLetter()
        lettersModel.postValue(currentLetters)
    }
}