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
    val diff = abs((this.time - date.time) / 1000)
    val inFuture = if (((this.time - date.time) / 1000) < 0) true else false
    var humanize: String = ""
    var amount = 0


    if (diff > 360*24*3600) return "более года назад"
    when {
        diff in 0L..1L -> return "только что"
        diff in 2L..45L -> return "несколько секунд назад"
        diff in 46L..75L -> return "минуту назад"
        diff in 45*60+1..75*60 -> return "час назад"
        diff in 22*3600+1..26*3600 -> return "день назад"
    }

    var decs: Int = 0
    when {
        // 76..45*60 "N минут назад"
        diff in 76..45*60 -> {
            amount = (diff / 60).toInt()
            decs = if (diff / 60 < 100) (diff / 60).toInt() else "$diff".substring("$diff".length-2,"$diff".length).toInt()
            when {
                decs == 1 || (decs % 10) == 1 -> humanize = "минуту"
                decs in 5..19 || (decs % 10)  in 5..9 || (decs % 10) == 0 -> humanize = "минут"
                (decs % 10)  in 2..4 -> humanize = "минуты"
            }
        }
        // 75*60+1..22*3600 "N часов назад"
        diff in 75*60+1..22*3600 -> {
            amount = (diff / 3600).toInt()
            decs = if (diff / 3600 < 100) (diff / 3600).toInt() else "$diff".substring("$diff".length-2,"$diff".length).toInt()
            when {
                decs == 1 || (decs % 10) == 1 -> humanize = "час"
                decs in 5..19 || (decs % 10)  in 5..9 || (decs % 10) == 0 -> humanize = "часов"
                (decs % 10)  in 2..4 -> humanize = "часа"
            }
        }
        // 26*3600+1..360*24*3600 "N дней назад"
        diff in 26*3600+1..360*24*3600 -> {
            amount = (diff / (3600 * 24)).toInt()
            decs = if (diff / (3600 * 24) < 100) (diff / (3600 * 24)).toInt() else "$amount".substring("$amount".length-2,"$amount".length).toInt()
            when {
                decs == 1 || (decs % 10) == 1 -> humanize = "день"
                decs in 5..19 || (decs % 10)  in 5..9 || (decs % 10) == 0 -> humanize = "дней"
                (decs % 10)  in 2..4 -> humanize = "дня"
            }
        }
    }

    return "$amount $humanize назад"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}