package com.jp.algorithm.problem.bfsNdfs

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

    data class Path(val x: Int, val y: Int, var count: Int)

    fun solution(maze: Array<Array<Char>>): Int {
        loop@ for (x in maze.indices) {
            for (y in maze[x].indices) {
                if (maze[x][y] == '0') {
                    searchKeyOrExit(maze, Path(x, y, 0), mutableListOf())
                    break@loop
                }
            }
        }

        return count
    }

    private fun searchKeyOrExit(maze: Array<Array<Char>>, path: Path, findKey: MutableList<Char>) {
        val queue: Queue<Path> = LinkedList()
        queue.offer(path)
        val visit = Array(maze.size) { Array(maze[0].size) { false } }

        while (queue.isNotEmpty() && count == -1) {
            val pollPath = queue.poll()
            visit[pollPath.x][pollPath.y] = true

            run directionLoop@{
                directions(pollPath).map {
                    if (it.x < 0 || it.x >= maze.size || it.y < 0 || it.y >= maze[0].size || visit[it.x][it.y]) {
                        return@map
                    }

                    if (maze[it.x][it.y] == '#' ||
                            (maze[it.x][it.y] in 'A'..'Z') && !findKey.contains(maze[it.x][it.y]) ||
                            (maze[it.x][it.y] in 'a'..'z' && findKey.contains(maze[it.x][it.y].toLowerCase()))) {
                        return@map
                    }

                    if (maze[it.x][it.y] in 'a'..'z' && !findKey.contains(maze[it.x][it.y].toUpperCase())) {
                        val newFindKey = findKey.toMutableList().apply { add(maze[it.x][it.y].toUpperCase()) }
                        searchKeyOrExit(maze, it, newFindKey)
                        return@map
                    }

                    if (maze[it.x][it.y] == '1') {
                        count = it.count
                        return@directionLoop
                    }

                    queue.offer(it)
                }
            }
        }
        return
    }

    private fun directions(position: Path): Array<Path> = arrayOf(
            Path(position.x, position.y - 1, position.count + 1),
            Path(position.x - 1, position.y, position.count + 1),
            Path(position.x, position.y + 1, position.count + 1),
            Path(position.x + 1, position.y, position.count + 1)
    )
}
