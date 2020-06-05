package com.jp.programmers.align

import java.lang.StringBuilder

fun main() {
//    val array = intArrayOf(6, 10, 2)
//    val array = intArrayOf(3, 30, 34, 5, 9)
//    val array = intArrayOf(4, 3, 30, 32, 5, 9)
    val array = intArrayOf(2, 20, 20)
//    val array = intArrayOf(34, 344, 9, 91, 0, 99, 0, 99)
//        val array = intArrayOf(9, 5, 4, 44, 41, 40, 3 )
//        val array = intArrayOf(9, 5, 46, 4, 44, 41, 40, 3 )
//    val array = intArrayOf(0, 0, 0, 0)
//    val array = intArrayOf(4, 40, 41, 442, 5, 3, 9)
//    val array = intArrayOf(9, 5, 436, 4, 41, 40, 3)
    println("Solution : ${BiggestNumber().solution(array)}")
}

class BiggestNumber {
    fun solution(numbers: IntArray): String {
        return numbers
                .sortedWith(Comparator { o1, o2 -> "$o2$o1".toInt() - "$o1$o2".toInt() })
                .apply { if (sum() == 0) return "0" }
                .joinToString(separator = "")
    }
}

