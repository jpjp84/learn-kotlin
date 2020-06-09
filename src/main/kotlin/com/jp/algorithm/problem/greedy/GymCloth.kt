package com.jp.algorithm.problem.greedy


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val n = 5
    val lost = intArrayOf(2, 4)
    val reserve = intArrayOf(3)
    println("Solution : ${GymCloth().solution(n, lost, reserve)}")
}

class GymCloth {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val noneHaveReserveLost = lost.toList().minus(reserve.toList())
        val remainReserve: MutableList<Int> = reserve.toList().minus(lost.toList()).toMutableList()
        var answer = n - noneHaveReserveLost.size

        noneHaveReserveLost.map { lostPosition ->
            remainReserve.remove(
                    remainReserve.find { lostPosition - 1 == it || lostPosition + 1 == it } ?: return@map
            )
            answer++
        }
        return answer
    }
}
