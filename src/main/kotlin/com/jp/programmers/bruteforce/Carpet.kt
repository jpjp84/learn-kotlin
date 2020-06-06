package com.jp.programmers.bruteforce

import java.util.*
import kotlin.math.max

fun main() {
//    val answers = intArrayOf(1, 2, 3, 4, 5)
    val brown = 8
    val yellow = 1
    println("Solution : ${Carpet().solution(brown, yellow).toList()}")
    // 4 x 6
    // 8 + 16
}

class Carpet {
    fun solution(brown: Int, yellow: Int): IntArray {
        return (1..yellow)
                .filter { yellow % it == 0 }
                .find { lineCount -> brown == (lineCount * 2) + ((yellow / lineCount + 2) * 2) }
                .let { intArrayOf((yellow / it!!) + 2, it + 2) }
    }
}

