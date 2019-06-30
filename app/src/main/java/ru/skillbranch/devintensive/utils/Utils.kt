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

    fun transliteration(payload: String, divider: String = " "): String {
/*
        Реализуй метод Utils.transliteration(payload divider) принимающий в качестве аргумента строку (divider по умолчанию " ")
        и возвращающий преобразованную строку из латинских символов, словарь символов соответствия алфовитов доступен в ресурсах к заданию
        Пример:
        Utils.transliteration("Женя Стереотипов") //Zhenya Stereotipov
        Utils.transliteration("Amazing Петр","_") //Amazing_Petr
*/
        //TODO("not implemented")
        return ""
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
/*
        Реализуй метод Utils.toInitials(firstName lastName) принимающий в качестве аргументов имя и фамилию пользователя (null, пустую строку)
        и возвращающий строку с первыми буквами имени и фамилии в верхнем регистре (если один из аргументов null то вернуть один инициал,
        если оба аргумента null вернуть null)
        Пример:
        Utils.toInitials("john" ,"doe") //JD
        Utils.toInitials("John", null) //J
        Utils.toInitials(null, null) //null
        Utils.toInitials(" ", "") //null
*/
        //TODO("not implemented")
        return ""
    }
}