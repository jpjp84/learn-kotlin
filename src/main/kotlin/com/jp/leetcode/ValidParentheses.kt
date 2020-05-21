package com.jp.leetcode

import java.util.*

/**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

Input: "{[]}"
Output: true
 */

fun main() {
    val input = "]"
    print("solution ${ValidParentheses().isValid(input)}")
}

class ValidParentheses() {
    private val bracketSet = mapOf("(" to ")", "[" to "]", "{" to "}")

    fun isValid(s: String): Boolean {
        if (s.length % 2 != 0) {
            return false
        }

        val openBracketStack = Stack<String>()
        var tempS = s

        Array(s.length) {
            val sUnit = tempS.take(1)
            tempS = tempS.drop(1)

            if (bracketSet.containsKey(sUnit)) {
                openBracketStack.push(sUnit)
                return@Array
            }

            if (openBracketStack.isEmpty() || bracketSet[openBracketStack.pop()] != sUnit) {
                return false
            }
        }
        return openBracketStack.isEmpty()
    }
}

