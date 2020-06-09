package com.jp.algorithm.problem.bfsNdfs


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {

    val computers = arrayOf(
            intArrayOf(1, 0, 0, 0),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(0, 0, 0, 1)
    )

//    val computers = arrayOf(
//            intArrayOf(1, 1, 1),
//            intArrayOf(1, 1, 0),
//            intArrayOf(1, 0, 1)
//    )
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
    fun solution(i: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val visit = BooleanArray(computers.size) { false }

        computers.mapIndexed { x, _ ->
            if (visit[x]) {
                return@mapIndexed
            }

            dfsStack(x, visit, computers)
            answer++
        }

        return answer
    }

    private fun dfsStack(x: Int, visit: BooleanArray, computers: Array<IntArray>) {
        visit[x] = true

        computers[x].mapIndexed { y, value ->
            if (visit[y] || value == 0) {
                return@mapIndexed
            }
            dfsStack(y, visit, computers)
        }
    }
}

