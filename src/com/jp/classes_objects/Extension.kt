package com.jp.classes_objects

fun Extension.getName(): String {
    return "NOT NAME"
}

fun main() {
    val str = "name"
    println("${str.isEmpty()}")

    println()
    val extension = Extension()
    println("${extension.getName()}")
//    println("${extension.getName("newnew")}")

    val ml = mutableListOf<Int>(1, 2, 3)
}

//private fun String.isEmpty(): Boolean {
//    println("extension string")
//    return true
//}

class Extension {

    private fun String.isEmpty(): Boolean {
        println("extension string")
        return true
    }

    init {
        val str = "Extension"
        println("${str.isEmpty()}")
    }

    fun getName(): String {
        return "extension"
    }
}

class Extension3 {
    fun Extension.getName(newName: String) : String {
        return "Extension3"
    }
}