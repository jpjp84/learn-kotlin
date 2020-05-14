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
    val num = 0
    print("solution ${PalindromeNumber().isPalindrome(num)}")
}

class PalindromeNumber() {

    fun isPalindrome(x: Int): Boolean {
        if (x < 0) {
            return false
        }

        var number = x
        var rev = 0
        while (number != 0) {
            rev = (rev * 10) + (number % 10)
            number /= 10
        }

        return rev == x
    }
}

