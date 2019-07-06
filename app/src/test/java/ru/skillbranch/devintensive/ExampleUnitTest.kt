package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils.toInitials
import ru.skillbranch.devintensive.utils.Utils.transliteration
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instanse() {
        //val user = User("1")
        val user2 = User("2", "John", "Wick")
        //val user3 = User("3", "John", "Silverhand", null, lastVisit = Date.kt(), isOnline = true)

        //user.printMe()
        //user2.printMe()
        //user3.printMe()

        //println("$user $user2 $user3")
    }

    @Test
    fun test_factory() {
        //val user = User.makeUser("John Cena")
        val user = User.makeUser("John Wick")
        //val user3 = User.makeUser(null)
        val user2 = user.copy(id="2", lastName = "Cena", lastVisit = Date())

        println("$user\n$user2")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_copy() {
        val user=User.makeUser("John Wick")
        val user2=user.copy(lastVisit = Date())
        val user3=user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user4=user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent())

    }

    @Test
    fun test_data_mapping() {
        val user=User.makeUser("Петр Иванов")
        print(user)
        val userView = user.toUserView()

        userView.printMe()
    }
    @Test
    fun test_abstract_factory() {
        val user=User.makeUser("Петр Иванов")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type="text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type="image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())

   }

    @Test
    // module3 FAILED java.lang.AssertionError: expected:<(null, null)> but was:<(null, )
    fun test_toInitials() {
        val initials = toInitials(null, null)
        println("Initials: \"$initials\"")
    }

    @Test
    fun test_builder() {
        val user0 = User.Builder().id("2")
            .firstName("John")
            .lastName("Jhonson")
            .avatar("Avatar")
            .rating(5)
            .respect(3)
            .lastVisit(Date())
            .isOnline(false)
            .build()
        println(user0)
    }

    @Test
    fun test_transliteration() {
        val user=User.makeUser("Сайлыкмаа Эйли-Хем чуртуг БиЧе оолъ")
        println("${user.firstName} <> ${user.lastName}")
        println(transliteration("${user.firstName} ${user.lastName}", "_"))

    }

    @Test
    fun test_humanizeDiff() {

        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        println("только что: ${Date().add(-1, TimeUnits.SECOND).humanizeDiff()}")
        println("несколько секунд назад: ${Date().add(-45, TimeUnits.SECOND).humanizeDiff()}")
        println("минуту назад: ${Date().add(-46, TimeUnits.SECOND).humanizeDiff()}")
        println("1 минуту назад: ${Date().add(-76, TimeUnits.SECOND).humanizeDiff()}")
        println("минуту назад: ${Date().add(-1, TimeUnits.MINUTE).humanizeDiff()}")
        println("2 минуты назад: ${Date().add(-2, TimeUnits.MINUTE).humanizeDiff()}")
        println("3 минуты назад: ${Date().add(-3, TimeUnits.MINUTE).humanizeDiff()}")
        println("17 минут назад: ${Date().add(-17, TimeUnits.MINUTE).humanizeDiff()}")
        println("30 минут назад: ${Date().add(-30, TimeUnits.MINUTE).humanizeDiff()}")
        println("45 минут назад: ${Date().add(-45, TimeUnits.MINUTE).humanizeDiff()}")
        println("час назад: ${Date().add(-1, TimeUnits.HOUR).humanizeDiff()}")
        println("1 час назад: ${Date().add(-76, TimeUnits.MINUTE).humanizeDiff()}")
        println("2 часа назад: ${Date().add(-120, TimeUnits.MINUTE).humanizeDiff()}")
        println("3 часа назад: ${Date().add(-3, TimeUnits.HOUR).humanizeDiff()}")
        println("4 часа назад: ${Date().add(-4, TimeUnits.HOUR).humanizeDiff()}")
        println("5 часов назад: ${Date().add(-5, TimeUnits.HOUR).humanizeDiff()}")
        println("17 часов назад: ${Date().add(-17, TimeUnits.HOUR).humanizeDiff()}")
        println("1 день назад: ${Date().add(-27, TimeUnits.HOUR).humanizeDiff()}")
        println("4 дня назад: ${Date().add(-4, TimeUnits.DAY).humanizeDiff()}")
        println("5 дней назад: ${Date().add(-5, TimeUnits.DAY).humanizeDiff()}")
        println("20 день назад: ${Date().add(-20, TimeUnits.DAY).humanizeDiff()}")
        println("21 день назад: ${Date().add(-21, TimeUnits.DAY).humanizeDiff()}")
        println("99 день назад: ${Date().add(-99, TimeUnits.DAY).humanizeDiff()}")
        println("101 день назад: ${Date().add(-101, TimeUnits.DAY).humanizeDiff()}")
        println("273 дня назад: ${Date().add(-273, TimeUnits.DAY).humanizeDiff()}")
        println("360 дней назад: ${Date().add(-360, TimeUnits.DAY).humanizeDiff()}")
        println("более года назад: ${Date().add(-361, TimeUnits.DAY).humanizeDiff()}")

    }

    @Test
    fun test_humanizeDiff2() {
        assertEquals("только что", Date().add(-1, TimeUnits.SECOND).humanizeDiff())
        assertEquals("несколько секунд назад", Date().add(-45, TimeUnits.SECOND).humanizeDiff())
        assertEquals("минуту назад", Date().add(-46, TimeUnits.SECOND).humanizeDiff())
        assertEquals("1 минуту назад", Date().add(-76, TimeUnits.SECOND).humanizeDiff())
        assertEquals("минуту назад", Date().add(-1, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("2 минуты назад", Date().add(-2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("3 минуты назад", Date().add(-3, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("45 минут назад", Date().add(-45, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("час назад", Date().add(-1, TimeUnits.HOUR).humanizeDiff())
        assertEquals("1 час назад", Date().add(-76, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("2 часа назад", Date().add(-120, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("3 часа назад", Date().add(-3, TimeUnits.HOUR).humanizeDiff())
        assertEquals("4 часа назад", Date().add(-4, TimeUnits.HOUR).humanizeDiff())
        assertEquals("5 часов назад", Date().add(-5, TimeUnits.HOUR).humanizeDiff())
        assertEquals("день назад", Date().add(-26, TimeUnits.HOUR).humanizeDiff())
        assertEquals("1 день назад", Date().add(-27, TimeUnits.HOUR).humanizeDiff())
        assertEquals("4 дня назад", Date().add(-4, TimeUnits.DAY).humanizeDiff())
        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
        assertEquals("360 дней назад", Date().add(-360, TimeUnits.DAY).humanizeDiff())
        assertEquals("более года назад", Date().add(-361, TimeUnits.DAY).humanizeDiff())

// ----- Future ------

        assertEquals("только что", Date().add(1, TimeUnits.SECOND).humanizeDiff())
        assertEquals("через минуту", Date().add(1, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 3 минуты", Date().add(3, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 5 минут", Date().add(5, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через час", Date().add(1, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 2 часа", Date().add(2, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 3 часа", Date().add(3, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 4 часа", Date().add(4, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 5 часов", Date().add(5, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через день", Date().add(1, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 4 дня", Date().add(4, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 5 дней", Date().add(5, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 148 дней", Date().add(148, TimeUnits.DAY).humanizeDiff())
        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())

    }
    }
