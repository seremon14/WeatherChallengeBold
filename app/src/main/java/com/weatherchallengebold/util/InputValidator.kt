package com.weatherchallengebold.util

object InputValidator {
    /**
     * Valida que el texto solo contenga letras, números, espacios y caracteres acentuados
     * No permite caracteres especiales que puedan representar vulnerabilidades
     */
    fun isValidLocationInput(input: String): Boolean {
        // Permite letras (incluyendo acentos), números, espacios, guiones y comas
        val pattern = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s,-]+$".toRegex()
        return pattern.matches(input)
    }

    /**
     * Filtra caracteres especiales del input, manteniendo solo caracteres válidos
     */
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

