package com.jp.algorithm.problem.arrays

/**
 * https://www.notion.so/Maximize-Distance-To-Closest-Person-a50aa7a181c04a81a6833edb0a9c6bd4
 */


fun main() {
    val seats = intArrayOf(1, 0, 0, 0, 0, 1, 0, 1)
    println("Solution : ${MaximizeDistanceToClosestPerson().maxDistToClosest(seats)}")
}

class MaximizeDistanceToClosestPerson {
    fun maxDistToClosest(seats: IntArray): Int {
        return """([0]+)""".toRegex().findAll(seats.joinToString("")).toList()
                .fold(0) { acc, group ->
                    if (group.range.first == 0 || group.range.last == seats.size - 1) {
                        acc.coerceAtLeast(group.value.length)
                    } else {
                        acc.coerceAtLeast((group.range.last - group.range.first + 2) / 2)
                    }
                }
    }

    fun bestSolution(seats: IntArray): Int {
        var res = 0
        var last = -1
        seats.indices.map {
            if (seats[it] == 1) {
                res = if (last < 0) it else res.coerceAtLeast((it - last) / 2)
                last = it
            }
        }
        res = res.coerceAtLeast(seats.size - last - 1)
        return res
    }
}
