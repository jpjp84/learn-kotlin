package com.jp.algorithm.problem.test


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
//    val nums = "{{20,111},{111}}"
    val nums = "{{4,2,3},{3},{2,3,4,1},{2,3}}"
    println("Solution : ${Prac2_2().solution(nums).toList()}")
}

class Prac2_2 {
    fun solution(s: String): IntArray {
        val answer = mutableListOf<Int>()
        val splitStrs = s.split("},{").map {
            it.replace("""[{}]""".toRegex(), "").split(",")
        }

        splitStrs.sortedBy { it.size }.map { sortedStr ->
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
