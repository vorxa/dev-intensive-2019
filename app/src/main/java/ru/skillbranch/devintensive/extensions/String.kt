package ru.skillbranch.devintensive.extensions

fun String.truncate(payload: Int = 16): String {

    val str = this.trim()
    if (str.length <= payload) {
        return str
    } else {
        return "${str.substring(0, payload).trimEnd()}..."
    }

}