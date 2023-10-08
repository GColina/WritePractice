package com.example.writepractice.model

class LettersProvider {

    companion object {

        fun random(): LettersModel {
            val position = (0..getLetters().lastIndex).random()
            return getLetters()[position]
        }

        private fun getLetters(): List<LettersModel> {
            val allLetters = mutableListOf<LettersModel>()
            for (letter in 'a'..'z') {
                allLetters.add(LettersModel(letter.toString()))
            }
            return allLetters.toList()
        }
    }
}