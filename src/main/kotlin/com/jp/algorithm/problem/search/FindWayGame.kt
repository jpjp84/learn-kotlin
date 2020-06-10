package com.jp.algorithm.problem.search

import java.awt.Point
import java.util.*
import javax.xml.crypto.Data
import kotlin.properties.Delegates

/**
 *
 */

fun main() {
    val nodeinfo = arrayOf(
            intArrayOf(5, 3),
            intArrayOf(11, 5),
            intArrayOf(13, 3),
            intArrayOf(3, 5),
            intArrayOf(6, 1),
            intArrayOf(1, 3),
            intArrayOf(8, 6),
            intArrayOf(7, 2),
            intArrayOf(2, 2)
    )
    println("Solution : ${FindWayGame().solution(nodeinfo).map { it.toList() }}")
}

class FindWayGame {
    data class DataSet(val index: Int, val point: Point)

    class Node(val data: DataSet, private val level: Int = 0) {
        fun put(childData: DataSet) {
            if (left == null && childData.point.x < data.point.x) {
                left = Node(childData, level + 1)
                return
            }

            if (right == null && childData.point.x > data.point.x && (left == null || left!!.data.point.y == childData.point.y)) {
                right = Node(childData, level + 1)
                return
            }

            if (left?.data?.point?.y != childData.point.y || right?.data?.point?.y != childData.point.y) {
                if (data.point.x > childData.point.x) {
                    left?.put(childData)
                } else {
                    right?.put(childData)
                }
            }
        }

        var left: Node? = null
        var right: Node? = null
    }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        var node: Node? = null

        nodeinfo.withIndex()
                .map { DataSet(it.index, Point(it.value[0], it.value[1])) }
                .sortedBy { it.point.x }
                .sortedByDescending { it.point.y }
                .map {
                    if (node == null) {
                        node = Node(it)
                        return@map
                    }
                    node?.put(it)
                }

        node?.let {
            return arrayOf(getFrontToEnd(it).toIntArray(), getEndToFront(it).toIntArray())
        }

        return emptyArray()
    }

    fun getFrontToEnd(node: Node): MutableList<Int> {
        val stack = Stack<Node>()
        val array = mutableListOf<Int>()
        stack.push(node)

        while (stack.isNotEmpty()) {
            val currentNode = stack.pop()
            array.add(currentNode.data.index + 1)

            if (currentNode.right != null) {
                stack.push(currentNode.right)
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left)
            }
        }
        return array
    }

    fun getEndToFront(node: Node): MutableList<Int> {
        val stack = Stack<Node>()
        val array = mutableListOf<Int>()
        stack.push(node)

        while (stack.isNotEmpty()) {
            val currentNode = stack.pop()
            array.add(currentNode.data.index + 1)

            if (currentNode.left != null) {
                stack.push(currentNode.left)
            }
            if (currentNode.right != null) {
                stack.push(currentNode.right)
            }
        }
        array.reverse()
        return array
    }
}
