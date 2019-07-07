package ru.skillbranch.devintensive.extensions

fun String.truncate(payload: Int = 16): String {

    val str = this.trim()
    if (str.length <= payload) {
        return str
    } else {
        return "${str.substring(0, payload).trimEnd()}..."
    }

}

fun String.stripHtml(): String {
    //println("input=$this")
    val regex = """<.*?>""".toRegex()
    val amps = """&(\w|#)+?;""".toRegex()
    val spaces = """ +""".toRegex()
    var str = regex.replace(this, "")
    str = amps.replace(str, "")
    str = spaces.replace(str, " ")
    //println("output=$str")
    return str
}