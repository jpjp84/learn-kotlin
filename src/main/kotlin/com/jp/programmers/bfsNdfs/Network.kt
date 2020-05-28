package com.jp.programmers.bfsNdfs

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {

//    val computers = arrayOf(
//            intArrayOf(1, 0, 0, 0),
//            intArrayOf(0, 1, 1, 0),
//            intArrayOf(0, 1, 1, 0),
//            intArrayOf(0, 0, 0, 1)
//    )

    val computers = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1)
    )
//    val computers = arrayOf(
//            intArrayOf(1, 1, 0),
//            intArrayOf(1, 1, 1),
//            intArrayOf(0, 1, 1)
//    )

//    val computers = arrayOf(
//            intArrayOf(1, 0, 0, 0, 0),
//            intArrayOf(0, 1, 0, 0, 1),
//            intArrayOf(0, 0, 1, 0, 0),
//            intArrayOf(0, 0, 0, 1, 0),
//            intArrayOf(0, 1, 0, 0, 1)
//    )
    val computers2 = arrayOf(
            intArrayOf(1, 0, 0, 1),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(1, 0, 0, 1)
    )

//    val computers = arrayOf(
//            intArrayOf(1, 1, 1, 1, 1),
//            intArrayOf(1, 1, 1, 1, 1),
//            intArrayOf(1, 1, 1, 1, 1),
//            intArrayOf(1, 1, 1, 1, 1),
//            intArrayOf(1, 1, 1, 1, 1)
//    )

//    val computers = arrayOf(
//            intArrayOf(1)
//    )
    println("Solution : ${Network().solution(3, computers)}")
    println("Solution : ${Network().solution(4, computers2)}")
}

class Network {

    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val visited = Array(n) { BooleanArray(n) { false } }

        for (i in 0 until n) {
            var isChange = false

            for (j in 0 until n) {
                if (visited[i][j] || computers[i][j] == 0) {
                    continue
                }

                isChange = stackDFS(computers, i, j, n, visited)
            }

            if (isChange || visited[i].count { it } == 1) {
                answer++
            }
        }

        return answer
    }

    private fun stackDFS(computers: Array<IntArray>, i: Int, j: Int, n: Int, visited: Array<BooleanArray>): Boolean {
        val stack = Stack<Pair<Int, Int>>()
        var isChange = false
        stack.add(Pair(i, j))

        while (!stack.isEmpty()) {
            val popPosition = stack.pop()
            val x = popPosition.first
            val y = popPosition.second

            if (visited[x][y]) {
                continue
            }

            visited[x][y] = true
            if (x != y) {
                isChange = true
                visited[y][x] = true
            }

            Array(4) {
                var dx = x
                var dy = y
                when (it) {
                    0 -> dx -= 1
                    1 -> dy -= 1
                    2 -> dx += 1
                    3 -> dy += 1
                }

                if (dx < 0 || dx >= n || dy < 0 || dy >= n) {
                    return@Array
                }

                val dxyValue = computers[dx][dy]
                if (dxyValue == 0) {
                    return@Array
                }

                stack.add(Pair(dx, dy))
            }
        }
        return isChange
    }
}

