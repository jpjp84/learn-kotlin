package com.jp.leetcode

import kotlin.math.absoluteValue

/**
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
Input: 123
Output: 321

Example 2:
Input: -123
Output: -321

Example 3:
Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */

fun main() {
    val num = 123
    print("solution ${ReverseInteger().solution(num)}")
}

class ReverseInteger() {
    fun solution(num: Int): Int {
        try {
            val reversedNum = num.absoluteValue.toString().reversed()
            return if (num >= 0) reversedNum.toInt() else reversedNum.toInt() * -1
        } catch (e: NumberFormatException) {
        }
        return 0
    }
}

