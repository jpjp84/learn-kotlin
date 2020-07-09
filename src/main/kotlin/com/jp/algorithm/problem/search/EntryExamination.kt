package com.jp.algorithm.problem.search


fun main() {
    println("Solution : ${EntryExamination().solution(6, intArrayOf(7, 10, 2, 3, 4, 51, 22, 3, 1, 4, 5))}")
}

class EntryExamination {
    fun solution(n: Int, times: IntArray): Long {
        return searchTime(times, 0, (times.max()!!.toLong()) * n, n)
    }

    private tailrec fun searchTime(times: IntArray, start: Long, end: Long, n: Int): Long {
        val mid = ((start + end) / 2)
        val sum = times.sumBy { (mid / it).toInt() }

        if (sum == n) {
            return mid
        }
        if (sum > n) {
            return searchTime(times, start, mid - 1, n)
        }
        if (sum < n) {
            return searchTime(times, mid + 1, end, n)
        }
        return 0
    }
}
