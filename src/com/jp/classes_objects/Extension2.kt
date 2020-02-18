package com.jp.classes_objects

fun main() {
    val str = "name"
    println("${str.isEmpty()}")

    val extension = Extension2()
}

class Extension2 {
    init {
        val str = "Extension"
        println("${str.isEmpty()}")
    }
}