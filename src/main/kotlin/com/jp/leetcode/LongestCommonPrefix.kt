package com.jp.leetcode


fun main() {
    val strs = mutableListOf("c", "c")
    print("solution ${LongestCommonPrefix().longestCommonPrefix(strs.toTypedArray())}")
}

class LongestCommonPrefix() {
    fun longestCommonPrefix(strs: Array<String>): String {
        var index = 1
        var pivotStr = ""
        loop@ while (true) {
            try {
                val temp = strs[0].substring(0, index)
                val filtered = strs
                        .filterIndexed { strIndex, _ -> strIndex != 0 }
                        .filter { it.length >= index }
                        .filter { it.substring(0, index) == temp }

                if (filtered.size != strs.size - 1) {
                    break@loop
                }
                pivotStr = temp
                index++
            } catch (e: Exception) {
                break@loop
            }
        }

        return pivotStr
    }
}

