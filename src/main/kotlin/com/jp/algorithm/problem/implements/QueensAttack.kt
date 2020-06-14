package com.jp.algorithm.problem.implements

import java.util.*
import kotlin.math.abs

/*
queen : (4, 4)
147 |7 - 4| = 3
246 |6 - 4| = 2
345 |5 - 4| = 1

(3, 5)?  |3 - 4| = 1, (3, 4) -> (3, 5), (3, 3)
 */


fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
    var totalAttackRange = 0
    for (x in 1..n) {
        if (x == r_q) {
            totalAttackRange += (n - 1)
            continue
        }
        val incStep = abs(x - r_q)
        totalAttackRange++
        if (c_q - incStep >= 1) {
            totalAttackRange++
        }
        if (c_q + incStep <= n) {
            totalAttackRange++
        }
    }

    println("total : $totalAttackRange")

    obstacles.map {
        if (r_q == it[0]) {
            totalAttackRange -= preventAttackCount(n, it[1], c_q)
            return@map
        }

        val incStep = abs(it[0] - r_q)
        if (c_q != it[1] && c_q - incStep != it[1] && c_q + incStep != it[1]) {
            return@map
        }
        totalAttackRange -= preventAttackCount(n, r_q, it[0])
    }

    return totalAttackRange
}

fun preventAttackCount(n: Int, obstaclePosition: Int, queenPosition: Int): Int {
    if (obstaclePosition < queenPosition) {
        return obstaclePosition
    }

    if (obstaclePosition > queenPosition) {
        return (n - obstaclePosition) + 1
    }
    return 0
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nk = scan.nextLine().split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

    val r_qC_q = scan.nextLine().split(" ")

    val r_q = r_qC_q[0].trim().toInt()

    val c_q = r_qC_q[1].trim().toInt()

    val obstacles = Array<Array<Int>>(k, { Array<Int>(2, { 0 }) })

    for (i in 0 until k) {
        obstacles[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
    }

    val result = queensAttack(n, k, r_q, c_q, obstacles)

    println(result)
}
