package com.weatherchallengebold.util

object InputValidator {
    fun isValidLocationInput(input: String): Boolean {
        val pattern = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s,-]+$".toRegex()
        return pattern.matches(input)
    }

    fun filterValidCharacters(input: String): String {
        return input.filter { char ->
            char.isLetterOrDigit() ||
                    char.isWhitespace() ||
                    char == '-' ||
                    char == ',' ||
                    char in "áéíóúÁÉÍÓÚñÑüÜ"
        }
    }
}
