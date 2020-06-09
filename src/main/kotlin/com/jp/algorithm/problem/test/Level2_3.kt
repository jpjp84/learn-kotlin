package com.jp.algorithm.problem.test

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val nums = intArrayOf(1, 5, 3, 6, 7, 6, 5)

    println("Solution : ${Prac2_3().solution(nums).toList()}")
}

class Prac2_3 {
    fun solution(heights: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        heights.mapIndexed { sendIndex, sendHeight ->
            val stack = Stack<Int>()
            heights.slice(0 until sendIndex).mapIndexed { index, height ->
                if (height > sendHeight) {
                    stack.push(index)
                }
            }
            answer.add(if (stack.isEmpty()) 0 else stack.pop() + 1)
        }

        return answer.toIntArray()
    }
}
