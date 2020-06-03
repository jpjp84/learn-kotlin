package com.jp.leetcode

import kotlin.math.min


/**
 * https://www.notion.so/Find-Pivot-Index-6c0023ab78b645c1a13276de6ea7f8cd
 */

fun main() {
//    val nums = intArrayOf(1, 7, 3, 6, 5, 6)
//    val nums = intArrayOf(1, 2, 3)
//    val nums = intArrayOf(2, 7, 3, 3, 3, 6, 6, 5, 6)
//    val nums = intArrayOf(-1, -1, -1, -1, 0, 1)
//    val nums = intArrayOf(1, 0, -1, -1, -1, -1)
//    val nums = intArrayOf(-1, -1, 0, 1, 0, -1)
//    val nums = intArrayOf(-1, -1, 0, 1, 1, 0)
    val nums = intArrayOf(-1, 0, 0, 0, 0, -1)

    println("Solution : ${FindPivotIndex().pivotIndex(nums)}")
}

class FindPivotIndex {

    fun pivotIndex(nums: IntArray): Int {val sum = nums.sum()
        var prefixSum = 0
        for (i in nums.indices) {
            if (prefixSum == sum - nums[i] - prefixSum) {
                return i
            }
            prefixSum += nums[i]
        }
        return -1
    }
}

