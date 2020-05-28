package com.jp.programmers.bfsNdfs

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    println("Solution : ${TargetNumber().solution(intArrayOf(1, 1, 1, 1, 1), 3)}")
//    println("Solution : ${TargetNumber().solution(intArrayOf(1, 1, 1, 2, 1), 2)}")
    /**
     * 1, 1, 1, 2, 1
     * 1, 1, 1, 2, 1
     * 1, 1, 1, 2, 1
     */
}

class TargetNumber {

    fun solution(numbers: IntArray, target: Int): Int {
        val stack = Stack<Pair<Int, Int>>()
        var count = 0
        stack.push(Pair(0, numbers[0]))
        stack.push(Pair(0, numbers[0] * -1))

        while (!stack.isEmpty()) {
            val popNumber = stack.pop()
            val index = popNumber.first

            if (index == numbers.size - 1) {
                count = if (popNumber.second == target) count + 1 else count
                continue
            }

            stack.push(Pair(index + 1, popNumber.second + numbers[index + 1]))
            stack.push(Pair(index + 1, popNumber.second - numbers[index + 1]))
        }

        return count
    }
}

