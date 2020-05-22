package com.jp.algorithm

import java.util.*


fun main() {

    val map = listOf(
            listOf(0, 1, 1, 0, 1, 0, 0),
            listOf(0, 1, 1, 0, 1, 0, 1),
            listOf(1, 1, 1, 0, 1, 0, 1),
            listOf(0, 0, 0, 0, 1, 1, 1),
            listOf(0, 1, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 0, 0, 0)
    )
    val visited = Array(7) { Array(7) { false } }
    val group: MutableMap<Int, Int> = mutableMapOf()
    var groupIndex = -1

    Array(7) { x ->
        Array(7) innerArray@{ y ->
            if (visited[x][y] || map[x][y] == 0) {
                return@innerArray
            }
//            group[++groupIndex] = DFS(map, visited).recursiveDFS(x, y)
            group[++groupIndex] = DFS(map, visited).stackDFS(x, y)
        }
        println("-------")
    }

    println("${group.toList()}")
}

class DFS(private val map: List<List<Int>>, private val visited: Array<Array<Boolean>>) {

    @Suppress("DuplicatedCode")
    fun stackDFS(x: Int, y: Int, groupCount: Int = 1): Int {
        var count = 1
        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(x, y))

        visited[x][y] = true

        while (stack.isNotEmpty()) {
            println("${stack.toList()}")
            val popElement = stack.pop()
            val stackX = popElement.first
            val stackY = popElement.second

            Array(4) {
                var dx = stackX
                var dy = stackY

                when (it) {
                    0 -> dx -= 1
                    1 -> dy += 1
                    2 -> dx += 1
                    3 -> dy -= 1
                }

                if (dx < 0 || dx >= map[0].size || dy < 0 || dy >= map.size) {
                    return@Array
                }

                if (visited[dx][dy] || map[dx][dy] == 0) {
                    return@Array
                }

                visited[dx][dy] = true
                count++

//                stack.push(Pair(stackX, stackY))
                stack.push(Pair(dx, dy))
            }
        }

        return count
    }

    fun recursiveDFS(x: Int, y: Int, groupCount: Int = 1): Int {
        var tempCount = groupCount

        visited[x][y] = true

        Array(4) {
            var dx = x
            var dy = y
            when (it) {
                0 -> dx -= 1
                1 -> dy += 1
                2 -> dx += 1
                3 -> dy -= 1
            }

            if (dx < 0 || dx >= map[0].size || dy < 0 || dy >= map.size) {
                return@Array
            }

            if (visited[dx][dy] || map[dx][dy] == 0) {
                return@Array
            }

            tempCount = recursiveDFS(dx, dy, tempCount + 1)
        }
        return tempCount
    }
}
