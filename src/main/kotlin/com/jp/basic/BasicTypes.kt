package com.jp.basic

fun main() {
    val x = (1 shl 2) and 0x000FF000
    val y = (1 shl 2)
    val z = "000FF000".toInt(16)

    class ArrModule {
        var a: Int? = null
        var b: String? = null
    }

    fun arrayCalculator(i: Int): Int {
        return i + 33
    }

    val arr = Array(5) { it ->
        arrayCalculator(it)
    }

    println("$x $y $z ${arr[0]}")

    val text = """
    |for (c in "foo")
    |    print(c)
    """.trimMargin()

    println("$text");


    val text2 = """
    >for (c in "foo")
    |    print(c)
    """.trimMargin(">")

    println("$text2");
    val price = """
${'$'}9.99
"""
    println(price)
    println("${'$'}9.99")
}
