package com.jp.leetcode

/**
 * https://www.notion.so/Remove-Duplicates-from-Sorted-Array-dfcd83bd573f48a3ae69a9ef81dc7d88
 */

fun main() {
    val numbs = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)

    val result = RemoveDuplicates().removeDuplicates(numbs)
    println("Solution : $result, ${numbs.toList()}")
}

class RemoveDuplicates() {
    fun removeDuplicates(nums: IntArray): Int {
        var prevNum: Int? = null
        var currentIndex = 0

        nums.map { num ->
            if (prevNum == num) {
                return@map
            }
            nums[currentIndex] = num
            prevNum = num
            currentIndex++
        }

        return currentIndex
    }
}

