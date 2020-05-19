package com.jp.leetcode


fun main() {
    val strs = mutableListOf("aca", "cba")
//    val strs = emptyList<String>()
    print("solution ${LongestCommonPrefix().longestCommonPrefix(strs.toTypedArray())}")
}

class LongestCommonPrefix() {

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) {
            return ""
        }
        return longestCommonPrefix(strs, 0, strs.size - 1)
    }

    fun longestCommonPrefix(strs: Array<String>, l: Int, r: Int): String {
        if (l == r) {
            return strs[l]
        }

        val mid = (l + r) / 2
        val left = longestCommonPrefix(strs, l, mid)
        val right = longestCommonPrefix(strs, mid + 1, r)

        return compare(left, right)
    }

    private fun compare(left: String, right: String): String {
        var extractStr = ""
        left.zip(right) { a, b ->
            if (a != b) {
                return extractStr
            }
            extractStr += a
        }

        return extractStr
    }

}

