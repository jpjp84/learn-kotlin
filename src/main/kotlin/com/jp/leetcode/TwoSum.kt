package com.jp.leetcode

/**
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

fun main() {
    val nums = intArrayOf(3, 3)
    val target = 6
    print("solution ${solution(nums, target).toList()}")
}

fun solution(nums: IntArray, target: Int): IntArray {
    val map: MutableMap<Int, Int> = mutableMapOf()
    nums.mapIndexed { index, num ->
        val number = target - num
        if (map.containsKey(number)) {
            return intArrayOf(map[number]!!, index)
        }
        map.put(num, index)
    }
    return intArrayOf()
}
