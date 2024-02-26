package com.gcolina.writepractice.model

class LettersProvider {

    companion object {

        private var index = 0

        fun nextLetter(): LettersModel {
            val letters = getLetters()

            if (index >= letters.size) {
                index = 0
            }

            return letters[index++]
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



/*
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
}*/
