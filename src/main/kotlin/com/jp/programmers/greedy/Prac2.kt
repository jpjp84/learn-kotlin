package com.jp.programmers.greedy


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val brown = 304
    val yello = 5000
    println("Solution : ${Carpet().solution(brown, yello).toList()}")
}

class Carpet {
    fun solution(brown: Int, yellow: Int): IntArray {
        var line = 0
        var countPerLine = 0

        while (line++ < yellow) {
            if (yellow % line != 0) {
                continue
            }

            countPerLine = yellow / line
            val brownCount = ((countPerLine + 2) * 2) + (line * 2)

            if (brown == brownCount) {
                break
            }
        }

        val width = if (countPerLine > line) countPerLine else line
        val height = if (countPerLine < line) countPerLine else line
        return intArrayOf(width + 2, height + 2)
    }
}
