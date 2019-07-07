package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
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
    fun parseFullNameTest(){
        /* skillBranch tests */
        assertEquals(null to null, Utils.parseFullName(null))
        assertEquals(null to null, Utils.parseFullName(""))
        assertEquals(null to null, Utils.parseFullName(" "))
        assertEquals("John" to null, Utils.parseFullName("John"))
        /* additional tests */
        assertEquals(null to null, Utils.parseFullName("     "))
        assertEquals("null" to null, Utils.parseFullName("null"))
        assertEquals("John" to null, Utils.parseFullName("John      "))
        assertEquals("John" to null, Utils.parseFullName(" John     "))
    }

    @Test
    fun parseFullNameTest2(){
        /* skillBranch tests */
        println("null to null ${Utils.parseFullName(null)}")
        println("null to null ${Utils.parseFullName("")}")
        println("null to null ${Utils.parseFullName(" ")}")
        println("John to null ${Utils.parseFullName("John")}")
        /* additional tests */
        println("null to null ${Utils.parseFullName("     ")}")
        println("null to null ${Utils.parseFullName("null")}")
        println("John to null ${Utils.parseFullName("John      ")}")
        println("John to null ${Utils.parseFullName(" John     ")}")
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

    @Test
    fun test_plural() {
        assertEquals("0 секунд", TimeUnits.SECOND.plural(0))
        assertEquals("1 секунду", TimeUnits.SECOND.plural(1))
        assertEquals("2 секунды", TimeUnits.SECOND.plural(2))
        assertEquals("7 секунд", TimeUnits.SECOND.plural(7))
        assertEquals("14 секунд", TimeUnits.SECOND.plural(14))
        assertEquals("24 секунды", TimeUnits.SECOND.plural(24))
        assertEquals("102 секунды", TimeUnits.SECOND.plural(102))
        assertEquals("112 секунд", TimeUnits.SECOND.plural(112))
        assertEquals("122 секунды", TimeUnits.SECOND.plural(122))
        assertEquals("311 секунд", TimeUnits.SECOND.plural(311))

        assertEquals("0 минут", TimeUnits.MINUTE.plural(0))
        assertEquals("1 минуту", TimeUnits.MINUTE.plural(1))
        assertEquals("2 минуты", TimeUnits.MINUTE.plural(2))
        assertEquals("7 минут", TimeUnits.MINUTE.plural(7))
        assertEquals("14 минут", TimeUnits.MINUTE.plural(14))
        assertEquals("24 минуты", TimeUnits.MINUTE.plural(24))
        assertEquals("102 минуты", TimeUnits.MINUTE.plural(102))
        assertEquals("112 минут", TimeUnits.MINUTE.plural(112))
        assertEquals("122 минуты", TimeUnits.MINUTE.plural(122))
        assertEquals("311 минут", TimeUnits.MINUTE.plural(311))

        assertEquals("0 часов", TimeUnits.HOUR.plural(0))
        assertEquals("1 час", TimeUnits.HOUR.plural(1))
        assertEquals("2 часа", TimeUnits.HOUR.plural(2))
        assertEquals("7 часов", TimeUnits.HOUR.plural(7))
        assertEquals("14 часов", TimeUnits.HOUR.plural(14))
        assertEquals("24 часа", TimeUnits.HOUR.plural(24))
        assertEquals("102 часа", TimeUnits.HOUR.plural(102))
        assertEquals("112 часов", TimeUnits.HOUR.plural(112))
        assertEquals("122 часа", TimeUnits.HOUR.plural(122))
        assertEquals("311 часов", TimeUnits.HOUR.plural(311))

        assertEquals("0 дней", TimeUnits.DAY.plural(0))
        assertEquals("1 день", TimeUnits.DAY.plural(1))
        assertEquals("2 дня", TimeUnits.DAY.plural(2))
        assertEquals("7 дней", TimeUnits.DAY.plural(7))
        assertEquals("14 дней", TimeUnits.DAY.plural(14))
        assertEquals("24 дня", TimeUnits.DAY.plural(24))
        assertEquals("102 дня", TimeUnits.DAY.plural(102))
        assertEquals("112 дней", TimeUnits.DAY.plural(112))
        assertEquals("122 дня", TimeUnits.DAY.plural(122))
        assertEquals("311 дней", TimeUnits.DAY.plural(311))
        assertEquals("1234 дня", TimeUnits.DAY.plural(1234))
    }

    @Test
    fun truncateTest() {
        /* skillBranch tests */
        assertEquals("Bender Bending R...", "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate())
        assertEquals("Bender Bending...", "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15))
        assertEquals("A", "A     ".truncate(3))

        /* additional tests */
        assertEquals("too lo...", "   too long line with lots of spaces before".truncate(6))
        assertEquals("too short", "too short".truncate(20))
        assertEquals("12345", "12345".truncate(5))
        assertEquals("1234...", "12345".truncate(4))
        assertEquals("12345", "12345  ".truncate(5))
        assertEquals("tab", "tab    ".truncate(5))
        assertEquals("dots......", "dots... a lot".truncate(7))
        assertEquals("abc", "abc    ".truncate(15))
        assertEquals("123456...", "123456789".truncate(6))
        assertEquals("123456789", "123456789".truncate(9))
        assertEquals("Bender Bending R...", "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate())
        assertEquals("Bender Bending...", "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15))
        assertEquals("1", "1     ".truncate(3))
    }

    @Test
    fun stripHtmlTest() {
        /* skillBranch tests */
        assertEquals("Образовательное IT-сообщество Skill Branch",
            "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
        assertEquals("Образовательное IT-сообщество Skill Branch",
            "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())

        /* additional tests */
        assertEquals("single", "&amp;&lt;&gt;single&#39;&quot;".stripHtml())
        assertEquals("", "&amp;&lt;&gt;&#39;&quot;".stripHtml())
        assertEquals(" ", "&amp;&lt;&gt;    &#39;&quot;".stripHtml())
        assertEquals("1978", "<path fill=\"Color\" d=\"M11.63 10z\"></svg><span>1978</span>".stripHtml())
        assertEquals("", "&gt;<head>&#39;&quot;</head>".stripHtml())
        assertEquals(" ", "&gt;<head> &quot; </head>".stripHtml())
        assertEquals("&игра; amp lt &gt 39; meters ()quot;", "&игра; amp lt &gt 39; meters ()quot;".stripHtml())
        assertEquals(" one two ", "  one   two ".stripHtml())
        assertEquals("null", "null".stripHtml())
        val longHtml = """
            <TD valign="top" style="padding-bottom:15px;"> <b>line1<b> </TD>
            <TD valign="top"> <span class="HeadTitleNews"> line2</span>
            <img src='http://2011WaterpoloF.jpg' >
            <div style="margin: 0in 0in 0pt">line3</div>
        """.trimIndent()
        assertEquals(" line1 \n line2\n\nline3", longHtml.stripHtml())
    }
    }

