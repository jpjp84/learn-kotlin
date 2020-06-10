package com.jp.algorithm.problem.bfsNdfs

import java.util.*
import kotlin.collections.HashSet

fun main() {
    val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 4, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 3, 0, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 2, 3, 0, 0, 0, 5, 5),
            intArrayOf(1, 2, 2, 2, 3, 3, 0, 0, 0, 5),
            intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0, 5)
    )
//    [(1, 5)(2, 3), 0, 4], [(0, 4, 5), (1), (2, 3)], [(0, 1), (2, 4), 3, 5]
    println("Solution : ${BlockGame().solution(board)}")
}

class BlockGame {
    fun solution(board: Array<IntArray>): Int {
        val visit = Array(board.size) { Array(board.size) { false } }
        val availableBlock = mutableSetOf<Int>()
        var blockCount = 0

        for (x in board.indices) {
            for (y in board.indices) {
                if (visit[x][y] || board[x][y] == 0) {
                    continue
                }

                val availableBlockList = findBlock(board, visit, x, y, availableBlock)
                if (availableBlockList.isEmpty()) {
                    blockCount++
                } else {
                    availableBlock.addAll(availableBlockList)
                }
            }
        }

        return blockCount
    }

    private fun findBlock(board: Array<IntArray>, visit: Array<Array<Boolean>>, x: Int, y: Int, availableBlock: MutableSet<Int>): List<Int> {
        val stack = Stack<Pair<Int, Int>>()
        val blockMap = mutableMapOf<Int, HashSet<Int>>()
        stack.push(Pair(x, y))

        while (stack.isNotEmpty()) {
            val blockElement = stack.pop()
            visit[blockElement.first][blockElement.second] = true
            blockMap.computeIfAbsent(blockElement.first - x) { HashSet() }.add(blockElement.second)

            directions(blockElement).map {
                if (it.first >= board.size || it.second < 0 || it.second >= board.size) {
                    return@map
                }

                if (visit[it.first][it.second] || board[it.first][it.second] != board[blockElement.first][blockElement.second]) {
                    return@map
                }

                stack.push(it)
            }
        }

        return getAvailableBlocks(blockMap, availableBlock)
    }

    private fun getAvailableBlocks(sizePerHeight: MutableMap<Int, HashSet<Int>>, availableBlock: MutableSet<Int>): List<Int> {
        val max = sizePerHeight.values.withIndex().maxBy { it.value.size }!!

        if (max.index != sizePerHeight.size - 1) {
            return max.value.toList()
        }

        sizePerHeight.values
                .filterIndexed { index, _ -> index < max.index }
                .flatMap { max.value - it }
                .map {
                    if (availableBlock.contains(it)) {
                        return max.value.toList()
                    }
                }
        return emptyList()
    }

    private fun directions(position: Pair<Int, Int>): Array<Pair<Int, Int>> {
        return arrayOf(
                Pair(position.first + 1, position.second),
                Pair(position.first, position.second - 1),
                Pair(position.first, position.second + 1)
        )
    }
}
