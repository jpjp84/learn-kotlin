package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Best-Time-to-Buy-and-Sell-Stock-II-fd5a636d767946d4854e4ef503830592
 */

fun main() {
    val numbers = intArrayOf(1, 2, 1)
    println("Solution : ${NextGreaterElement2().nextGreaterElements(numbers).toList()}")
}

class NextGreaterElement2 {
    fun nextGreaterElements(nums: IntArray): IntArray {
        val array = mutableListOf<Int>()

        nums.mapIndexed { index, num ->
            var travelIndex = index + 1

            while (travelIndex != index) {
                if (travelIndex > nums.size - 1) {
                    travelIndex = 0
                    continue
                }
                if (num < nums[travelIndex]) {
                    array.add(nums[travelIndex])
                    return@mapIndexed
                }

                travelIndex++
            }
            array.add(-1)
        }

        return array.toIntArray()
    }
}

