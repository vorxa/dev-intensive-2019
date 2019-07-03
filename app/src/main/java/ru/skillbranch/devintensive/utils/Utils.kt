package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.replaceFirst(" ", "#")?.trimIndent()?.split("#")
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
        val symCyr = charArrayOf(' ','а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у',
            'ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я','А','Б','В','Г','Д','Е','Ё','Ж','З','И','Й','К','Л','М','Н',
            'О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i',
            'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','-'
        )
        val symLat = arrayOf(divider,"a","b","v","g","d","e","e","zh","z","i","i","k","l","m","n","o","p","r","s","t","u",
            "f","h","c","ch","sh","sh","","i","","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","I","K","L","M",
            "N","O","P","R","S","T","U","F","H","C","Ch","Sh","Sh","","I","","E","Ju","Ja","a","b","c","d","e","f","g",
            "h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","-"
        )
        val builder = StringBuilder()
        for (i in 0 until payload.length) {
            for (x in symCyr.indices) {
                if (payload[i] == symCyr[x]) {
                    builder.append(symLat[x])
                }
            }
        }
        return builder.toString()
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
        val fName = firstName?.trim()
        val lName = lastName?.trim()
        val firstInit: Char? = fName?.let{if (fName.isNotEmpty()) fName[0] else null}
        val lastInit: Char? = lName?.let{if (lName.isNotEmpty()) lName[0] else null}
        val initials: String?
        if (firstInit == null && lastInit == null) {
            initials = null
        } else {
            initials = "${if (firstInit == null) "" else firstInit}${if (lastInit == null) "" else lastInit}".toUpperCase()
        }

        return initials
    }
}