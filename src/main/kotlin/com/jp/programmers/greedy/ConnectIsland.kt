package com.jp.programmers.greedy

import kotlin.math.cos


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val n = 5
//    val costs = arrayOf(
//            intArrayOf(0, 1, 1),
//            intArrayOf(0, 2, 2),
//            intArrayOf(1, 2, 5),
//            intArrayOf(1, 3, 3),
//            intArrayOf(2, 3, 8),
//            intArrayOf(3, 4, 1)
//    )
    val costs = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(2, 3, 1),
            intArrayOf(3, 4, 2),
            intArrayOf(1, 2, 2),
            intArrayOf(0, 4, 100)
    )
//    {01.1, 12.2, 23.1, 34.2},
//    [0, 1, 1],
//    [2, 3, 1],
//    [3, 4, 2],
//    [1, 2, 2],
//    [0, 4, 100]

    println("Solution : ${ConnectIsland().solution(n, costs)}")
}

class ConnectIsland {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        costs.sortBy { it[2] }

        val minimumCost = costs[0]
        val visit = Array(n) { it == minimumCost[0] || it == minimumCost[1] }
        var answer = minimumCost[2]

        while (visit.contains(false)) {
            answer += findCost(costs, visit) ?: break
        }

        return answer
    }

    private fun findCost(costs: Array<IntArray>, visit: Array<Boolean>): Int? {
        costs.filterIndexed { i, _ -> i != 0 }.map {
            if (visit[it[0]] == visit[it[1]]) {
                return@map
            }

            visit[it[0]] = true
            visit[it[1]] = true
            return it[2]
        }
        return null
    }
}
