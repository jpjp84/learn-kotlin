package com.jp.algorithm.problem.dijkstra

import java.util.*

fun main() {
    val V = 5
    val E = 6
    val start = 1
    val node = arrayOf(
            intArrayOf(5, 1, 1),
            intArrayOf(1, 2, 2),
            intArrayOf(1, 3, 3),
            intArrayOf(2, 3, 4),
            intArrayOf(2, 4, 5),
            intArrayOf(3, 4, 6)
    )
    println("Solution : ${ShortestPath().solution(V, start, node).toList()}")
}

class ShortestPath {
    fun solution(v: Int, start: Int, node: Array<IntArray>): Array<Int> {
        val answer = Array(v + 1) { if (it == start) 0 else Int.MAX_VALUE }
        val queue: Queue<IntArray> = LinkedList()
        queue.add(intArrayOf(1, 1, 0))

        while (queue.isNotEmpty()) {
            val front = queue.poll()
            val distance = answer[front[0]] + front[2]
            (distance <= answer[front[1]])

            if (distance > answer[front[1]]) {
                continue
            }

            answer[front[1]] = distance
            node.filter { it[0] == front[1] }.map { queue.offer(it) }
        }

        return emptyArray()
    }
}
