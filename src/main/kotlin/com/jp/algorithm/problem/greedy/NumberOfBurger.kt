package com.jp.algorithm.problem.greedy

/**
 * https://www.notion.so/Number-of-Burgers-with-No-Waste-of-Ingredients-8b533fa11f6b4ed78f443acd0237446b
 */

fun main() {
    val tomatoSlices = 16
    val cheeseSlices = 7
    println("Solution : ${NumberOfBurger().numOfBurgers(tomatoSlices, cheeseSlices)}")
}

class NumberOfBurger {
    fun numOfBurgers(tomatoSlices: Int, cheeseSlices: Int): List<Int> {
        if (tomatoSlices % 2 != 0 || cheeseSlices * 2 > tomatoSlices || tomatoSlices > cheeseSlices * 4) {
            return emptyList()
        }
        return listOf((tomatoSlices / 2 - cheeseSlices), (cheeseSlices * 2) - (tomatoSlices / 2))
    }
}
