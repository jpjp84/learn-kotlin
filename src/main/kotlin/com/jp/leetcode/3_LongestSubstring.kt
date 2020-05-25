package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Longest-Substring-Without-Repeating-Characters-37275e9b37d74d729dd76fd5f47b2dad
 */

fun main() {
    val value = "dvdf"
//    val value = " "
    val result = LongestSubstring().lengthOfLongestSubstring(value)
    println("Solution : $result")
}

class LongestSubstring() {
    fun lengthOfLongestSubstring2(s: String): Int {
        var ans = 0
        val map: MutableMap<Char, Int> = HashMap()
        var j = 0
        var i = 0
        while (j < s.length) {
            if (map.containsKey(s[j])) {
                i = Math.max(map[s[j]]!!, i)
            }
            ans = Math.max(ans, j - i + 1)
            map[s[j]] = j + 1
            j++
        }
        return ans
    }

    fun lengthOfLongestSubstring(s: String): Int {
        var longestSubstring = 0
        var startIndex = 0
        var endIndex = 0

        while (endIndex != s.length) {
            val endCursorStr = s[endIndex]
            val prevEndCursorStr = s.substring(startIndex, endIndex)
            endIndex++

            if (!prevEndCursorStr.contains(endCursorStr)) {
                longestSubstring = Math.max(longestSubstring, endIndex - startIndex)
                continue
            }

            startIndex = s.substring(0, endIndex - 1).lastIndexOf(endCursorStr) + 1
        }

        return longestSubstring
    }
}

