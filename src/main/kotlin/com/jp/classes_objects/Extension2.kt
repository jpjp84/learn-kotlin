package com.jp.classes_objects

fun main() {
    val str = "name"
    println("${str.isEmpty()}")

    val extension = Extension2()

    fun Extension3.getName(newName: String): String {
        return "Extension3 in Extension2"
    }

    println(Extension3().getName("new name"))
}

class Extension2 {
    init {
        val str = "Extension"
        println("${str.isEmpty()}")
    }
}