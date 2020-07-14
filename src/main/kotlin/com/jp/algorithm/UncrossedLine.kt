package com.jp.algorithm


fun main() {
    val a = intArrayOf(1, 4, 2, 4)
    val b = intArrayOf(1, 2, 4, 3)
    println("Solution : ${UncrossedLine().maxUncrossedLines(a, b)}")
}

class UncrossedLine {

    fun maxUncrossedLines(A: IntArray, B: IntArray): Int {
        val m: Int = A.size
        val n: Int = B.size
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            for (j in 1..n) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = dp[i][j - 1].coerceAtLeast(dp[i - 1][j])
                }
            }
        }
        return dp[m][n]
    }
}
