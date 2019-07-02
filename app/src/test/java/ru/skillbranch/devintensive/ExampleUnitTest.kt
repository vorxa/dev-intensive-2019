package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils.toInitials
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
    fun test_toInitials() {
        val initials = toInitials(" asd ", null)
        println("Initials: \"$initials\"")
    }
}
