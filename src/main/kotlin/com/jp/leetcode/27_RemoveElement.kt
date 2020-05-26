package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val nums = intArrayOf(0,1,2,2,3,0,4,2)
    val value = 2
    val result = RemoveElement().removeElement(nums, value)
    println("Solution : $result, ${nums.toList()}, ${nums.indexOf(value)}")
}

class RemoveElement() {
    fun removeElement(nums: IntArray, value: Int): Int {
        if (!nums.contains(value)) {
            return nums.size
        }

        nums.mapIndexed { index, i ->
            val position = nums.indexOfFirst { it == value }
            if (i == value || index < position) {
                return@mapIndexed
            }

            nums[position] = i
            nums[index] = value
        }

        return nums.indexOf(value)
    }
}

