package com.jp.algorithm.problem.align


fun main() {
//    val array = intArrayOf(6, 10, 2)
    val array = intArrayOf(3, 30, 34, 5, 9, 8)
//    val array = intArrayOf(4, 3, 30, 32, 5, 9)
//    val array = intArrayOf(4,2,5,7)
//    val array = intArrayOf(34, 344, 9, 91, 0, 99, 0, 99)
//        val array = intArrayOf(9, 5, 4, 44, 41, 40, 3 )
//        val array = intArrayOf(9, 5, 46, 4, 44, 41, 40, 3 )
//    val array = intArrayOf(0, 0, 0, 0)
//    val array = intArrayOf(4, 40, 41, 442, 5, 3, 9)
//    val array = intArrayOf(9, 5, 436, 4, 41, 40, 3)
    SortArrayByParity().solution(array)
    println("Solution : ${SortArrayByParity().solution(array).toList()}")
}

class SortArrayByParity {
    fun solution(numbers: IntArray): IntArray {
        val even = numbers.filter { it % 2 == 0 }
        val odd = numbers.filter { it % 2 == 1 }
        return even.zip(odd).flatMap { listOf(it.first, it.second) }.toIntArray()
    }
}

