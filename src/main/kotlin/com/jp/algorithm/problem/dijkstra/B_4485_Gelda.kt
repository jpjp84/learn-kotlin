package com.jp.algorithm.problem.dijkstra

import java.util.*

fun main() {
    val V = 7
    val node = arrayOf(
            intArrayOf(9, 0, 5, 1, 1, 5, 3),
            intArrayOf(4, 1, 2, 1, 6, 5, 3),
            intArrayOf(0, 7, 6, 1, 6, 8, 5),
            intArrayOf(1, 1, 7, 8, 3, 2, 3),
            intArrayOf(9, 4, 0, 7, 6, 4, 1),
            intArrayOf(5, 8, 3, 2, 4, 8, 3),
            intArrayOf(7, 4, 8, 4, 8, 3, 4)
    )

    println("Solution : ${Gelda().solution(V, node).toList()}")
}

class Gelda {

    private fun directions(current: Triple<Int, Int, Int>, blackCoin: Int): Array<Triple<Int, Int, Int>> = arrayOf(
            Triple(current.first + 1, current.second, blackCoin),
            Triple(current.first, current.second + 1, blackCoin)
    )

    fun solution(v: Int, node: Array<IntArray>): Array<Int> {
        val answer = Array(v) { Array(v) { Int.MAX_VALUE } }
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
        queue.add(Triple(0, 0, 0))

        while (queue.isNotEmpty()) {
            val front = queue.poll()
            val blackCoin = front.third + node[front.first][front.second]

            if (blackCoin > answer[front.first][front.second]) {
                continue
            }
            answer[front.first][front.second] = blackCoin
            directions(front, blackCoin).filter { it.first < v && it.second < v }.map { queue.offer(it) }
        }

        return emptyArray()
    }
}
