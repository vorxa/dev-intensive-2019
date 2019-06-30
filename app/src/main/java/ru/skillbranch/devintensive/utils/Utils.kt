package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.trimIndent()?.split(" ")
        val firstName = when (parts?.getOrNull(0)) {
            null -> null
            "" -> null
            else -> parts?.getOrNull(0)
        }
        val lastName = parts?.getOrNull(1)

        //return Pair(firstName, lastName)
        return firstName to lastName
    }
}