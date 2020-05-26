package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val nums = intArrayOf(3, 2, 2, 3)
    val value = 3
    val result = RemoveElement().removeElement(nums, value)
    println("Solution : $result, ${nums.toList()}, ${nums.indexOf(value)}")
}

class RemoveElement() {
    fun removeElement(nums: IntArray, value: Int): Int {
        var removedElementCount = 0

        nums.mapIndexed { index, i ->
            if (i == value) {
                return@mapIndexed
            }

            removedElementCount++

            val position = nums.indexOfFirst { it == value }
            if (position < 0 || index < position) {
                return@mapIndexed
            }

            nums[position] = i
            nums[index] = value
        }

        return removedElementCount
    }
}

