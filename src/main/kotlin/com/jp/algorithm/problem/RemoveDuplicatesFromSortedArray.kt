package com.jp.algorithm.problem

import com.jp.algorithm.DeleteColumns2

fun main() {
//    val nums = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)
    val nums = intArrayOf(0, 0, 1, 1, 1, 1, 2, 3, 3)
    println("Solution : ${RemoveDuplicatesFromSortedArray().removeDuplicates(nums)}")
}
/*
(1, 1, 1, 1, 2, 2, 2, 3)
(1, 1, 2, 2, 1, 1, 2, 3)

 */

class RemoveDuplicatesFromSortedArray {

    fun removeDuplicates(nums: IntArray): Int {
        var orderedCursor = 2
        for (i in 2 until nums.size) {
            if (nums[i] == nums[orderedCursor - 2]) {
                continue
            }

            nums[orderedCursor] = nums[i].also { nums[i] }
            orderedCursor++
        }

        return orderedCursor
    }
}
