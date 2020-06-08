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
        val answer = Array(alice.size) { 1 }
        alice.mapIndexed { index, aliceScore ->
            scores.toSet().map scoreLoop@{ score ->
                if (aliceScore >= score) {
                    return@scoreLoop
                }
                answer[index] += 1
            }
        }
        return answer
    }
}
