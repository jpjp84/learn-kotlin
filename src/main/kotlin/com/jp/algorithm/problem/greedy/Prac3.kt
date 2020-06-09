package com.jp.algorithm.problem.greedy


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val pipe = "()(((()())(())()))(())"

    println("Solution : ${CutPipe().solution(pipe)}")
}

class CutPipe {
    fun solution(arrangement: String): Int {
        var answer = 0
        val pipe = arrangement.replace("()", ".")
        var open = 0

        pipe.toList().map {
            when (it) {
                '.' -> answer += open
                ')' -> answer += open
                '(' -> open += 1
            }
        }
        return answer
    }
}
