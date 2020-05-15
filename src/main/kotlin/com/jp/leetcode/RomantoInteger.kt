package com.jp.leetcode

/**
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?
 */

fun main() {
    val num = "MCMXCIV"
    print("solution ${RomantoInteger().romanToInt(num)}")
}

class RomantoInteger() {
    private val romanIntMap = mutableMapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)

    fun romanToInt(s: String): Int {
        val romanKeys = romanIntMap.keys
        var previousRoman: Char? = null
        var sum = 0

        s.toList().reversed().map {currentRoman ->
            if (!romanIntMap.containsKey(currentRoman)) {
                return@map
            }

            previousRoman?.let {nonNullPreviousRoman ->
                if (romanKeys.indexOf(nonNullPreviousRoman) > romanKeys.indexOf(currentRoman)) {
                    sum -= romanIntMap[currentRoman]!!
                    return@map
                }
            }
            sum += romanIntMap[currentRoman]!!
            previousRoman = currentRoman
        }
        return sum
    }
}

