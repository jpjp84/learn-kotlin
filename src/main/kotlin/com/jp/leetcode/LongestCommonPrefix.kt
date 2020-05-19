package com.jp.leetcode


fun main() {
    val strs = mutableListOf("aca", "cba")
//    val strs = emptyList<String>()
    print("solution ${LongestCommonPrefix().longestCommonPrefix(strs.toTypedArray())}")

    //Binary Search
    val numbers = listOf(1, 3, 4, 5, 8, 14, 15)
    print("solution ${LongestCommonPrefix().binarySearch(numbers, 15)}")
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

    fun binarySearch(numbers: List<Int>, target: Int): Int {
        return find(numbers, 0, numbers.size - 1, target)
    }

    fun find(numbers: List<Int>, start: Int, end: Int, target: Int): Int {
        val mid = (start + end) / 2
        if (numbers.get(mid) == target) {
            return mid
        }

        if (numbers.get(mid) < target) {
            return find(numbers, mid + 1, numbers.size - 1, target)
        }
        return find(numbers, 0, mid - 1, target)
    }
}

