package com.jp.basic

fun main() {
    ifTest()
    whenTest()
    forTest()
}

fun ifTest() {
    println("----- if Test -----")
    fun chooseAorB(a: Int, b: Int): Int {
        return if (a > b) {
            println("Choose a")
            a
        } else {
            println("Choose b")
            b
        }
    }

    val maxVal = chooseAorB(3, 5)
    println(maxVal)
}

fun whenTest() {
    println("----- if Test -----")
    fun chooseNumberByX(x: Int): String? {
        var nullableChooseNumber: String?;
        nullableChooseNumber = when (x) {
            1 -> "1!"
            2 -> "2!"
            else -> "not choose..."
        }
        return nullableChooseNumber
    }

    println(chooseNumberByX(3))


    fun passingCondition(x: Int) {
        run {
            println("start when...")
            when (x) {
                1 -> {
                    println("choose 1")
                    return@run
                }
                1 -> println("choose 2")
                else -> "not choose..."
            }
        }
    }

    passingCondition(1)
}

fun forTest() {
    println("----- for Test -----")
    for (i : Int in 10 downTo 1) {
        println("$i is ?")
    }
}