package com.jp.programmers.greedy

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {

    val name = "AABAAAABBBAAA"
//    AA b AAAAAA BBB
    // AABAAAABBBAAA
    // AJJ : 0A2 -> 2 : 8
    // AJ        : 0A1 -> 1 : 7
    // JEEAEEEEE : 3A8 -> 12 : 11
    // JEEAEAA : 3A3 -> 7 : 11
    // JAEAAEEAEN : 1A8 -> (0+8) : 9
    // JEAEAEEAEN : 2A7 -> (2+7) : 9
    // JEEAAAEEAEN : 3A5 -> (4+5) : 10
    // JEEEAEAEN : 4A4 -> (6+4) : 10
    // JEEEEAAEEEN : 5A4 -> (8+4) : 10
    // ((n-1) * 2) + Remain
    println("Solution : ${Joystick().solution(name)}")
}

class Joystick {

    fun solution(name: String): Int {
        var answer = findMinimumPath(name)
        name.toList().map {
            val startToValue = it.toInt() - 'A'.toInt()
            val endToValue = 'Z'.toInt() - it.toInt() + 1
            answer += startToValue.coerceAtMost(endToValue)
        }
        return answer
    }

    private fun findMinimumPath(name: String): Int {
        val match = """[A]+""".toRegex().findAll(name)
        var result = name.length - 1

        match.toList().map {
            if (it.range.first == 0) {
                result = result.coerceAtMost((name.length - 1) - it.range.last)
                return@map
            }
            if (it.range.last == name.length - 1) {
                result = result.coerceAtMost((name.length - 1) - (it.range.last - it.range.first))
                return@map
            }
            val returnPath = ((it.range.first - 1) * 2) + (name.length - 1) - it.range.last
            result = result.coerceAtMost(returnPath)
        }
        return result
    }
}
