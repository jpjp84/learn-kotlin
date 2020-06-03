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

    var answer: Int? = null

    fun pivotIndex(nums: IntArray): Int {
        val isContainNegative = nums.filter { it < 0 }.count() > 0
        findPivot(nums, 0, nums.size, isContainNegative)

        return answer ?: -1
    }

    private fun findPivot(nums: IntArray, firstIndex: Int, lastIndex: Int, isContainNegative: Boolean) {
        if (firstIndex >= lastIndex) {
            return
        }

        var midIndex = (lastIndex + firstIndex) / 2
        for (i in midIndex..firstIndex) {
            if (nums[i] != 0) {
                break
            }
            midIndex = i
        }

        var beforeSum = 0
        var afterSum = 0

        nums.mapIndexed { index, num ->
            when {
                index == midIndex -> return@mapIndexed
                index < midIndex -> beforeSum += num
                index > midIndex -> afterSum += num
            }
        }

        if (beforeSum == afterSum) {
            answer = if (answer == null) midIndex else (answer!!).coerceAtMost(midIndex)
        }

        findPivot(nums, firstIndex, midIndex, isContainNegative)
        findPivot(nums, midIndex + 1, lastIndex, isContainNegative)
    }
}

