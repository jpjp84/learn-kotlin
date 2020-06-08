package com.jp.hackrank

import java.util.*


fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

//    val scoresCount = scan.nextLine().trim().toInt()
//
//    val scores = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
//
//    val aliceCount = scan.nextLine().trim().toInt()
//
//    val alice = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    val scoresCount = 7
    val scores = arrayOf(100, 100, 50, 40, 40, 20, 10)
    val aliceCount = 4
    val alice = arrayOf(5, 25, 50, 120)

    val result = ClimbingtheLeaderboard().climbingLeaderboard(scores, alice)

    println(result.joinToString("\n"))
}

class ClimbingtheLeaderboard {
    fun climbingLeaderboard(scores: Array<Int>, alice: Array<Int>): Array<Int> {
        val scoreSet = scores.distinct()
        return alice.map {
            bSearch(scoreSet, 0, scoreSet.size - 1, it).plus(1)
        }.toTypedArray()
    }

    private fun bSearch(scores: List<Int>, first: Int, last: Int, aliceScore: Int): Int {
        val mid = (first + last) / 2

        if (first >= last) {
            if (aliceScore < scores[mid]) {
                return mid + 1
            }
            return mid
        }

        if (scores[mid] == aliceScore) {
            return mid
        }

        if (scores[mid] > aliceScore) {
            return bSearch(scores, mid + 1, last, aliceScore)
        }

        return bSearch(scores, first, mid, aliceScore)
    }
}
