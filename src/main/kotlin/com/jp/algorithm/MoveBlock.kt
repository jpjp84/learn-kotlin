package com.jp.algorithm

import java.util.*


/**
 * https://www.notion.so/Prime-Number-of-Set-Bits-in-Binary-Representation-d87911f4f100466099543582416b1dc0
 */

fun main() {

    val seq = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 1, 1, 1, 0),
            intArrayOf(0, 1, 1, 1, 1, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 0, 0, 0)
    )
//    val seq = arrayOf(
//            intArrayOf(0, 0, 0, 1, 1),
//            intArrayOf(0, 0, 0, 1, 0),
//            intArrayOf(0, 1, 0, 1, 1),
//            intArrayOf(1, 1, 0, 0, 1),
//            intArrayOf(0, 0, 0, 0, 0)
//    )
    println("Solution : ${MoveBlock().solution(seq)}")
}

class MoveBlock {
    data class Robot(var position: Pair<Int, Int>, var orientation: Orientation, var distance: Int, var process: String) {
        enum class Orientation {
            HORIZONTAL, VERTICAL
        }
    }

    fun solution(board: Array<IntArray>): Int {
        val stack = Stack<Robot>()
        val visit = Array(board.size) { Array(board[0].size) { mutableMapOf(Robot.Orientation.HORIZONTAL to false, Robot.Orientation.VERTICAL to false) } }
        stack.push(Robot(Pair(0, 0), Robot.Orientation.HORIZONTAL, 1, "(0, 0, h) -> "))

        while (stack.isNotEmpty()) {
            val currentRobot = stack.pop()
            visit[currentRobot.position.first][currentRobot.position.second][currentRobot.orientation] = true

            directions(currentRobot).map {
                if (visit[it.position.first][it.position.second][it.orientation]!!) {
                    return@map
                }
                if ((it.orientation == Robot.Orientation.HORIZONTAL && it.position.second + 1 == board[0].size) ||
                        (it.orientation == Robot.Orientation.VERTICAL && it.position.first + 1 == board.size)) {
                    println(it.process)
                    return it.distance
                }
                if (currentRobot.orientation != it.orientation) {
                    if (isAvailableRotate(currentRobot, board)) {
                        stack.push(it)
                    }
                    return@map
                }

                if (it.orientation == Robot.Orientation.HORIZONTAL) {
                    if (it.position.second + 1 >= board[0].size ||
                            board[it.position.first][it.position.second] != 0 ||
                            board[it.position.first][it.position.second + 1] != 0) {
                        return@map
                    }
                    stack.push(it)
                } else {
                    if (it.position.first + 1 >= board.size ||
                            board[it.position.first][it.position.second] != 0 ||
                            board[it.position.first + 1][it.position.second] != 0) {
                        return@map
                    }
                    stack.push(it)
                }
            }
        }
        return -1
    }

    private fun isAvailableRotate(robot: Robot, board: Array<IntArray>): Boolean {
        if (robot.orientation == Robot.Orientation.HORIZONTAL) {
            return board[robot.position.first + 1][robot.position.second] == 0 &&
                    board[robot.position.first + 1][robot.position.second + 1] == 0
        }

        return board[robot.position.first][robot.position.second + 1] == 0 &&
                board[robot.position.first + 1][robot.position.second + 1] == 0
    }

    private fun directions(robot: Robot): Array<Robot> {
        if (robot.orientation == Robot.Orientation.HORIZONTAL) {
            return arrayOf(
                    Robot(Pair(robot.position.first, robot.position.second + 1), Robot.Orientation.HORIZONTAL, robot.distance + 1, "${robot.process} (${robot.position.first}, ${robot.position.second + 1}, h) -> "),
                    Robot(Pair(robot.position.first + 1, robot.position.second), Robot.Orientation.HORIZONTAL, robot.distance + 1, "${robot.process} (${robot.position.first + 1}, ${robot.position.second}, h) -> "),
                    Robot(Pair(robot.position.first, robot.position.second + 1), Robot.Orientation.VERTICAL, robot.distance + 1, "${robot.process} (${robot.position.first}, ${robot.position.second + 1}, v) -> ")
            )
        }

        return arrayOf(
                Robot(Pair(robot.position.first, robot.position.second + 1), Robot.Orientation.VERTICAL, robot.distance + 1, "${robot.process} (${robot.position.first}, ${robot.position.second + 1}, v) -> "),
                Robot(Pair(robot.position.first + 1, robot.position.second), Robot.Orientation.VERTICAL, robot.distance + 1, "${robot.process} (${robot.position.first + 1}, ${robot.position.second}, v) -> "),
                Robot(Pair(robot.position.first + 1, robot.position.second), Robot.Orientation.HORIZONTAL, robot.distance + 1, "${robot.process} (${robot.position.first + 1}, ${robot.position.second}, h) -> ")
        )
    }
}
