package com.jp.programmers.bruteforce

import java.util.*
import kotlin.math.max


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
//    val answers = intArrayOf(1, 2, 3, 4, 5)
    val answers = intArrayOf(1, 3, 2, 4, 2)
    println("Solution : ${SimulatingTest().solution(answers)}")
    /**
     * 1, 1, 1, 2, 1
     * 1, 1, 1, 2, 1
     * 1, 1, 1, 2, 1
     */
}

class SimulatingTest {
    private val player1Pattern = intArrayOf(1, 2, 3, 4, 5)
    private val player2Pattern = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
    private val player3Pattern = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

    fun solution(answers: IntArray): IntArray {
        val players = answers.indices.map {
            intArrayOf(player1Pattern[it % player1Pattern.size], player2Pattern[it % player2Pattern.size], player3Pattern[it % player3Pattern.size])
        }

        return answers.foldIndexed(Array(3) { 0 }) { index, acc, answer ->
            players[index].withIndex().filter { it.value == answer }.map { acc[it.index]++ }
            acc
        }.let { answerCorrects ->
            val maxVal = answerCorrects.max()
            answerCorrects.withIndex().filter { it.value == maxVal }.map { it.index + 1 }.toIntArray()
        }
    }
}

