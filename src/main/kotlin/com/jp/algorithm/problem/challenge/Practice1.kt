package com.jp.algorithm.problem.challenge

/**
 *
 */

fun main() {
    val moves = arrayOf("U", "U", "R", "D", "L", "L", "L", "U", "R", "D", "D", "D", "L", "U", "R", "R", "R", "D", "L", "U")
    println("Solution : ${Practice1().solution(moves)}")
}

class Practice1 {
    fun solution(moves: Array<String>): Int {
        var answer = 0
        var xMin = Int.MAX_VALUE
        var xMax = Int.MIN_VALUE
        var yMin = Int.MAX_VALUE
        var yMax = Int.MIN_VALUE
        var currentPosition = Pair(0, 0)

        val moveList = moves.map {
            var cpStr = "${currentPosition.first}${currentPosition.second}"

            val position = when (it) {
                "U" -> {
                    val upPosition = currentPosition.first - 1

                    cpStr += "${upPosition}${currentPosition.second}"
                    xMin = xMin.coerceAtMost(upPosition)
                    Pair(upPosition, currentPosition.second)
                }
                "D" -> {
                    val downPosition = currentPosition.first + 1
                    cpStr += "${downPosition}${currentPosition.second}"
                    xMax = xMin.coerceAtLeast(downPosition)
                    Pair(downPosition, currentPosition.second)
                }
                "L" -> {
                    val leftPosition = currentPosition.second - 1
                    cpStr += "${currentPosition.first}${leftPosition}"
                    yMin = xMin.coerceAtMost(leftPosition)
                    Pair(currentPosition.first, leftPosition)
                }
                "R" -> {
                    val rightPosition = currentPosition.second + 1
                    cpStr += "${currentPosition.first}${rightPosition}"
                    yMax = yMax.coerceAtLeast(rightPosition)
                    Pair(currentPosition.first, rightPosition)
                }

                else -> Pair(0, 0)
            }
            val result = arrayOf(currentPosition, position)
            currentPosition = position
            result
        }.flatMap { listOf(listOf(it[0], it[1])) }
//        moveList.map {
//            println(it.toList())
//        }
        println(moveList.toList())

        (xMin..xMax).map { x ->
            (yMin..yMax).map { y ->
                if (moveList.contains(listOf(Pair(0, 0), Pair(-1, 0)))) {

                    println("find!!$x, $y")
                }
//                if (
//                        moveList.contains("$x$y${x}${y + 1}") &&
//                        moveList.contains("${x}${y + 1}$x$y") &&
//                        moveList.contains("$x$y${x + 1}${y}") &&
//                        moveList.contains("${x + 1}${y}$x$y") &&
//                        moveList.contains("$x$y${x + 1}${y + 1}") &&
//                        moveList.contains("${x + 1}${y + 1}$x$y")
//                ) {
//                    answer++
            }
        }
//
        return answer

    }
}
