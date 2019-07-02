package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit: Date? = null,
    val isOnline:Boolean = false

) {

    constructor(id:String, firstName:String?, lastName:String?) : this(
        id=id,
        firstName=firstName,
        lastName=lastName,
        avatar=null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println("It's Alive!!!\n" +
                "${if (lastName==="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n")
    }

    companion object Factory {
        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id="$lastId", firstName = firstName, lastName = lastName)
        }
    }

    data class Builder(
        var id:String,
        var firstName:String?,
        var lastName:String?,
        var avatar:String?,
        var rating:Int = 0,
        var respect:Int = 0,
        var lastVisit: Date? = null,
        var isOnline:Boolean = false

    ) {
        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun avatar(avatar: String) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating}
        fun respect(respect: Int) = apply { this.respect = respect}
        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit}
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline}
        fun build() = User(id, firstName, lastName)

    }
}

/*
Необходимо реализовать Builder для класса User
+2
Реализуй паттерн Builder для класса User.
User.Builder().id(s)
.firstName(s)
.lastName(s)
.avatar(s)
.rating(n)
.respect(n)
.lastVisit(d)
.isOnline(b)
.build() должен вернуть объект User*/
