package com.jp.classes_objects

fun main() {
    var user = User("john", 10)
    println(user.name)

    val user2 = user.copy(age=30)
    val user3 = user.copy()

    println("copied user2 is equal? $user $user2 ${user == user2} ${user === user2}")
    println("copied user3 is equal? ${user == user3} ${user === user3}")

    println()
    user2.nickname = "user2"
    val (nickname) = user2
    println("user2's nickname is $nickname")

//    val (name, age) = user2
    val (age) = user2
    println("user2 is $age")

    val (nnaammee, aaggee) = user2
    println("unknown params $nnaammee $aaggee")
}

data class User(var name: String, var age: Int) {
    var nickname: String = ""
}