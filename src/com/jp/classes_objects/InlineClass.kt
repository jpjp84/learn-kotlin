package com.jp.classes_objects

fun main() {
    val password: Password = Password("abcd")
}

inline class Password(val value: String) {
    fun getHint(): String {
        return "test password"
    }
}