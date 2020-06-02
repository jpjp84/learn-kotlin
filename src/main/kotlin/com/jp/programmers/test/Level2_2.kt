package com.jp.programmers.test

import java.util.*
import kotlin.math.max
import kotlin.math.pow


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val nums = "{{123}}"
//    val nums = "{{4,2,3},{3},{2,3,4,1},{2,3}}"
    println("Solution : ${Prac2_2().solution(nums).toList()}")
}

class Prac2_2 {
    fun solution(s: String): IntArray {
        var answer = mutableListOf<Int>()
        val preSplitedStrs = s.split("},{")
        val splitedStrs = preSplitedStrs.map {
            val str = it.replace("""[{}]""".toRegex(), "")
            str.split(",")
        }

        splitedStrs.sortedBy { it.size }.map { sortedStr ->
            if (sortedStr.size == 1) {
                answer.add(sortedStr[0].toInt())
                return@map
            }

            val intersection = sortedStr.map { it.toInt() } - answer
            answer.add(intersection[0])
        }

        return answer.toIntArray()
    }
}
