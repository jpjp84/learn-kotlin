package com.jp.algorithm.problem.bfsNdfs

import java.util.*


fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val first = scan.nextLine().trim().split(" ")
    val n = first[1].toInt()
    val m = first[0].toInt()

    val box = Array(n) { Array(m) { 0 } }

    for (i in 0 until n) {
        box[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
    }

    println(TomatoBox().solution(box))
}

class TomatoBox {
    data class Tomato(val x: Int, val y: Int, var day: Int)

    fun solution(tomatoBox: Array<Array<Int>>): Int {
        val queue: Queue<Tomato> = LinkedList()

        tomatoBox.indices.map { x ->
            tomatoBox[x].indices.filter { tomatoBox[x][it] == 1 }.map { queue.offer(Tomato(x, it, 0)) }
        }
        return changeRipenTomato(tomatoBox, queue)
    }

    private fun changeRipenTomato(tomatoBox: Array<Array<Int>>, queue: Queue<Tomato>): Int {
        var maxDay = 0
        while (queue.isNotEmpty()) {
            val pollTomato = queue.poll()
            maxDay = maxDay.coerceAtLeast(pollTomato.day)

            directions(pollTomato).map {
                if (it.x < 0 || it.x >= tomatoBox.size || it.y < 0 || it.y >= tomatoBox[0].size) {
                    return@map
                }
                if (tomatoBox[it.x][it.y] == -1 || tomatoBox[it.x][it.y] == 1) {
                    return@map
                }
                tomatoBox[it.x][it.y] = 1
                queue.offer(it)
            }
        }
        if (tomatoBox.all { it.all { tomato -> tomato != 0 } }) {
            return maxDay
        }

        return -1
    }

    private fun directions(position: Tomato): Array<Tomato> = arrayOf(
            Tomato(position.x, position.y - 1, position.day + 1),
            Tomato(position.x - 1, position.y, position.day + 1),
            Tomato(position.x, position.y + 1, position.day + 1),
            Tomato(position.x + 1, position.y, position.day + 1)
    )
}
