package com.jp.algorithm.problem.challenge

import java.util.*

/**
 *
 */

fun main() {
    val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 2, 1, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 2, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )
    println("Solution : ${Practice2().solution(board)}")
}

class Practice2 {
    data class PadukNode(var x: Int, var y: Int, var count: Int, var direction: Int? = null)

    companion object DIRECTION {
        const val LEFT = 0
        const val BOTTOM = 1
    }

    private fun directionFun(it: Int): (PadukNode) -> Triple<Int, Int, Int> {
        if (it == LEFT) {
            return { Triple(it.x + 1, it.y, LEFT) }
        }
        return { Triple(it.x, it.y + 1, BOTTOM) }
    }

    fun solution(board: Array<IntArray>): Int {
        val visit: Array<Array<Array<Boolean>>> = Array(board.size) {
            Array(board[0].size) { Array(2) { false } }
        }

        (board.indices).map { x ->
            board[x].indices.map loop@{ y ->
                if (board[x][y] == 0) {
                    return@loop
                }

                (LEFT..BOTTOM).map directionLoop@{
                    val rootNode = PadukNode(x, y, 1)
                    if (visit[x][y][it]) {
                        return@directionLoop
                    }
                    if (findFive(board, visit, rootNode, directionFun(it)) == 5) {
                        return board[x][y]
                    }
                }
            }
        }

        return 0
    }

    private fun findFive(board: Array<IntArray>, visit: Array<Array<Array<Boolean>>>, rootNode: PadukNode, direction: (PadukNode) -> Triple<Int, Int, Int>): Int {
        val stack = Stack<PadukNode>()
        stack.push(rootNode)

        while (stack.isNotEmpty()) {
            val popNode = stack.pop()

            direction(popNode).let {
                if (board[it.first][it.second] != board[rootNode.x][rootNode.y]) {
                    return popNode.count
                }

                stack.push(PadukNode(it.first, it.second, popNode.count + 1))
                visit[it.first][it.second][it.third] = true
            }
        }
        return 0
    }

}
