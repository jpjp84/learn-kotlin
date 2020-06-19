package com.jp.algorithm.problem

import java.awt.Point
import java.util.*
import javax.xml.crypto.Data
import kotlin.properties.Delegates

/**
 *
 */

fun main() {

    val nodeinfo = arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0)
    )
    val moves = intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)

    println("Solution : ${CraneGame().solution(nodeinfo, moves)}")
}

class CraneGame {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0
        val stack = Stack<Int>()
        val visit = Array(board.size) { 0 }.apply {
            board.map { list ->
                list.withIndex().filter { it.value == 0 }.forEach { this[it.index]++ }
            }
        }
        moves.map {
            if (visit[it - 1] >= board.size) {
                return@map
            }

            val pick = board[visit[it - 1]][it - 1]
            if (stack.isNotEmpty() && stack.peek() == pick) {
                stack.pop()
                answer += 2
            } else {
                stack.push(pick)
            }
            visit[it - 1]++
        }

        return answer
    }
}
