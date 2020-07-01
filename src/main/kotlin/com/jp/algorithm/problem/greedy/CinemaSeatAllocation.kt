package com.jp.algorithm.problem.greedy


/**
 * https://www.notion.so/Prime-Number-of-Set-Bits-in-Binary-Representation-d87911f4f100466099543582416b1dc0
 */

fun main() {
    val L = 3
    val R = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 8),
            intArrayOf(2, 6),
            intArrayOf(3, 1),
            intArrayOf(3, 1)
    )
    val R2 = arrayOf(
            intArrayOf(2, 1),
            intArrayOf(1, 8),
            intArrayOf(2, 6)
    )
    val L2 = 4
    val R3 = arrayOf(
            intArrayOf(4, 3),
            intArrayOf(1, 4),
            intArrayOf(4, 6),
            intArrayOf(1, 7))

    val L3 = 2
    val R4 = arrayOf(
            intArrayOf(2, 1),
            intArrayOf(1, 8),
            intArrayOf(2, 6)
    )
    println("Solution : ${CinemaSeatAllocation().maxNumberOfFamilies(L, R)}")
}

class CinemaSeatAllocation {

    fun maxNumberOfFamilies(n: Int, reservedSeats: Array<IntArray>): Int {
        val availableSeatRanges = arrayOf((2..5), (4..7), (6..9))
        var count: Int
        reservedSeats
                .groupBy { it[0] }
                .let {
                    count = (n - it.keys.size) * 2
                    it
                }
                .map {
                    var seatRange = availableSeatRanges.toList()
                    it.value.map loop@{ position ->
                        seatRange = seatRange.filter { intRange -> position[1] !in intRange }
                    }

                    if (seatRange.size > 1) {
                        seatRange = seatRange.filter { intRange -> intRange != availableSeatRanges[1] }
                    }

                    count += seatRange.size
                }
        return count
    }
}
