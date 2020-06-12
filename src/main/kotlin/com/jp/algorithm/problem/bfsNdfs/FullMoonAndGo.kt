package com.jp.algorithm.problem.bfsNdfs

import com.jp.classes_objects.SingletonObjects
import com.jp.classes_objects.SingletonObjects.x
import java.util.*


fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val first = scan.nextLine().trim().split(" ")
    val n = first[0].toInt()
    val m = first[1].toInt()

    val maze = Array(n) { Array(m) { '.' } }

    for (i in 0 until n) {
        maze[i] = scan.nextLine().trim().toList().map { it }.toTypedArray()
    }

    println(FullMoonAndGo().solution(maze))
}

class FullMoonAndGo {
    var count = -1

    data class Path(val x: Int, val y: Int, var count: Int, var haveKey: Int = 0) {
        fun print() {
            println("$x, $y -> $count, Have ${Integer.toBinaryString(haveKey)}")
        }
    }

    fun solution(maze: Array<Array<Char>>): Int {
        var root: Path? = null
        var keyBit = 1
        loop@ for (x in maze.indices) {
            for (y in maze[x].indices) {
                if (maze[x][y] == '0') {
                    root = Path(x, y, 0)
                }
                if (maze[x][y] in 'a'..'z') {
                    keyBit = keyBit.shl(1)
                }
            }
        }

        root?.let { searchKeyOrExit(maze, it.apply { it.haveKey = keyBit - 1 }) }
        return count
    }

    private fun searchKeyOrExit(maze: Array<Array<Char>>, path: Path) {
        val queue: Queue<Path> = LinkedList()
        queue.offer(path)
        val visit = Array(maze.size) { Array(maze[0].size) { Array(path.haveKey + 1) { false } } }

        while (queue.isNotEmpty() && count == -1) {
            val pollPath = queue.poll()
            visit[pollPath.x][pollPath.y][pollPath.haveKey] = true
            pollPath.print()

            run directionLoop@{
                directions(pollPath).map {
                    if (it.x < 0 || it.x >= maze.size || it.y < 0 || it.y >= maze[0].size || visit[it.x][it.y][it.haveKey]) {
                        return@map
                    }

                    val value = maze[it.x][it.y]
                    if (value == '#' ||
                            (value in 'A'..'Z' && !isFind(value, it.haveKey)) ||
                            (value in 'a'..'z' && isFind(value, it.haveKey))
                    ) {
                        return@map
                    }

                    if (value == '1') {
                        count = it.count
                        return@directionLoop
                    }

                    if (value in 'a'..'z' && !isFind(value, it.haveKey)) {
                        it.haveKey = it.haveKey.xor(1.shl(value - 'a'))
                    }

                    queue.offer(it)
                }
            }
        }
        return
    }

    private fun isFind(char: Char, findKey: Int): Boolean {
        val charToInt = 1.shl(char.toLowerCase() - 'a')
        return charToInt.and(findKey) != charToInt
    }

    private fun directions(position: Path): Array<Path> = arrayOf(
            Path(position.x, position.y - 1, position.count + 1, position.haveKey),
            Path(position.x - 1, position.y, position.count + 1, position.haveKey),
            Path(position.x, position.y + 1, position.count + 1, position.haveKey),
            Path(position.x + 1, position.y, position.count + 1, position.haveKey)
    )
}
