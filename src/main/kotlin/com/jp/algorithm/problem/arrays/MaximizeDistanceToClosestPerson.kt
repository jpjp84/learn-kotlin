package com.jp.algorithm.problem.arrays


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
        val n: Int = seats.size
        var last = -1
        for (i in 0 until n) {
            if (seats[i] == 1) {
                res = if (last < 0) i else Math.max(res, (i - last) / 2)
                last = i
            }
        }
        res = Math.max(res, n - last - 1)
        return res
    }
}
