package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

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
    0с - 1с "только что"                (0-1)               "только что"
    1с - 45с "несколько секунд назад"   (>1-45)             "несколько секунд назад"
    45с - 75с "минуту назад"            (>45-75)            "минуту назад"
   *75с - 45мин "N минут назад"         (>75-45*60)         (2-45)минут
    45мин - 75мин "час назад"           (>45*60-75*60)      "час назад"
   *75мин 22ч "N часов назад"           (>75*60-22*3600)
    22ч - 26ч "день назад"
   *26ч - 360д "N дней назад"
    >360д "более года назад"
*/
    //TODO("not implemented")
    val diff = (this.time - date.time) / 1000
    var humanize: String
    var mins: Int

    println(diff)
    humanize = when (diff) {
        0L,-1L -> "только что"
        in -45L..-2L -> "несколько секунд назад"
        in -75L..-46L -> "минуту назад"
        in -75*60 until -45*60 -> "час назад"
        in -26*3600 until -22*3600 -> "день назад"
        else -> ""
    }
    humanize = if (diff < -360*24*3600) "более года назад" else humanize



    if (diff >=-45*60 && diff < -75L) { // минуты
        mins = if (abs(diff / 60) < 100) abs(diff / 60).toInt() else "$diff".substring("$diff".length-2,"$diff".length).toInt()

        val str_mins = "$mins"
        val ending: String
        if (mins <= 10) {
            ending = when(mins) {
                1L -> "а"
                2L,3L,4L -> "ы"
                else -> ""
            }
        } else {
            val dec = str_mins[str_mins.length-2].toInt()

        }
    }

    return "$mins"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}