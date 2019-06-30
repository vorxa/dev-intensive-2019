package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time=this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
/*
    Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени) для форматирования вывода разницы между датами в человекообразном формате,
    с учетом склонения числительных. Временные интервалы преобразований к человекообразному формату доступны в ресурсах к заданию
    Пример:
    Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
    Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
    Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
    Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
    Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
    Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год
*/
    //TODO("not implemented")
    return ""
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}