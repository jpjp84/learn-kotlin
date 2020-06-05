package com.jp.programmers.align

fun main() {
//    val citations = intArrayOf(3, 0, 6, 1, 5)
//    val citations = intArrayOf(2, 6, 6, 5, 1, 5, 7)
//    val citations = intArrayOf(2, 2, 2, 2, 2, 3, 0)
//    val citations = intArrayOf(0, 0, 0, 0, 2)
    val citations = intArrayOf(0, 1, 1, 1, 1, 3, 3, 4)
//    val citations = intArrayOf(5, 5, 5, 5)

    println("Solution : ${HIndex().solution(citations)}")
}

class HIndex {
    fun solution(citations: IntArray): Int = citations.indices
            .sortedDescending()
            .find { index -> citations.count { citation -> citation >= index + 1 } >= index + 1 }
            .let { if (it == null) 0 else it + 1 }
}

