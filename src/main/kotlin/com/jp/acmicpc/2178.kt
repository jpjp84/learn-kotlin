package com.jp.acmicpc

import java.util.*

fun main() {
    val target = intArrayOf(7, 7)
    val maze = arrayOf(
            intArrayOf(1, 0, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1)
    )

    println("Solution : ${SearchMaze().solution(target, maze)}")
    // 4 x 6
    // 8 + 16
}

class SearchMaze {
    val directionArray: (Triple<Int, Int, Int>) -> Array<Pair<Int, Int>> = {
        arrayOf(
                Pair(it.first + 1, it.second),
                Pair(it.first - 1, it.second),
                Pair(it.first, it.second + 1),
                Pair(it.first, it.second - 1)
        )
    }

    fun solution(target: IntArray, maze: Array<IntArray>): Int {

        return bfs(target, maze)
    }

    private fun bfs(target: IntArray, maze: Array<IntArray>): Int {
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
        val visit = Array(maze.size) { (Array(maze[0].size) { false }) }
        queue.offer(Triple(0, 0, 0))

        while (queue.isNotEmpty()) {
            val position = queue.poll()
            visit[position.first][position.second] = true
            if (target[0] - 1 == position.first && target[1] - 1 == position.second) {
                return position.third
            }

            directionArray(position).map {
                if (it.first < 0 || it.first >= maze.size || it.second < 0 || it.second >= maze[0].size) {
                    return@map
                }
                if (visit[it.first][it.second] || maze[it.first][it.second] == 0) {
                    return@map
                }

                queue.offer(Triple(it.first, it.second, position.third + 1))
            }
        }
        return -1
    }
}

