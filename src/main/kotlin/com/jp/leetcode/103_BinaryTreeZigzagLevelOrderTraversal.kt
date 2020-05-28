package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
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
        if (root == null) {
            return emptyList()
        }

        val queue: Queue<Pair<Int, TreeNode?>> = LinkedList()
        val result: MutableList<MutableList<Int>> = mutableListOf()
        queue.add(Pair(0, root))

        while (!queue.isEmpty()) {
            val popItem = queue.poll()
            val treeLevel = popItem.first
            val treeNode = popItem.second ?: continue

            if (result.size == treeLevel) {
                result.add(mutableListOf())
            }

            if (treeLevel % 2 == 0) {
                result[treeLevel].add(treeNode.`val`)
            } else {
                result[treeLevel].add(0, treeNode.`val`)
            }

            queue.add(Pair(treeLevel + 1, treeNode.left))
            queue.add(Pair(treeLevel + 1, treeNode.right))
        }
        return result
    }
}

