package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Binary-Tree-Zigzag-Level-Order-Traversal-89b4590819ad4719bddc15da14f30947
 */

fun main() {
    val tree = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    println("Solution : ${BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(tree)}")
}

class BinaryTreeZigzagLevelOrderTraversal {

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        root?.let {
            val queue: Queue<Pair<Int, TreeNode?>> = LinkedList()
            queue.add(Pair(0, it))
            return bfs(queue)
        }
        return emptyList()
    }

    private fun bfs(queue: Queue<Pair<Int, TreeNode?>>): List<List<Int>> {
        val result: MutableMap<Int, MutableList<Int>> = mutableMapOf()

        while (!queue.isEmpty()) {
            val popItem = queue.poll()
            val treeLevel = popItem.first
            val treeNode = popItem.second ?: continue

            result.computeIfAbsent(treeLevel) { mutableListOf() }.apply {
                if (treeLevel % 2 == 0) {
                    add(treeNode.`val`)
                    return@apply
                }
                add(0, treeNode.`val`)
            }

            queue.add(Pair(treeLevel + 1, treeNode.left))
            queue.add(Pair(treeLevel + 1, treeNode.right))
        }

        return result.values.toList()
    }
}

