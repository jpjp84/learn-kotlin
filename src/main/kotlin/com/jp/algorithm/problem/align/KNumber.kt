package com.jp.algorithm.problem.align

fun main() {
    val array = intArrayOf(1, 5, 2, 6, 3, 7, 4)
    val command = arrayOf(
            intArrayOf(2, 5, 3),
            intArrayOf(4, 4, 1),
            intArrayOf(1, 7, 3)
    )
    println("Solution : ${KNumber().solution(array, command)}")
}

class KNumber {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map { array.slice(it[0] - 1 until it[1]).sorted()[it[2] - 1] }.toIntArray()
    }
}

