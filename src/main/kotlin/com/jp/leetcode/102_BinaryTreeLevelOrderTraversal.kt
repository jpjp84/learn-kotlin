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
    println("Solution : ${BinaryTreeLevelOrderTraversal().levelOrder(tree)}")
}

class BinaryTreeLevelOrderTraversal {

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) {
            return emptyList()
        }

        val queue: Queue<TreeNode> = LinkedList(listOf(root))
        val list: MutableList<List<Int>> = mutableListOf()
        var levelIndex = queue.size

        while (!queue.isEmpty()) {
            val levelList: MutableList<Int> = mutableListOf()

            Array(levelIndex) {
                val a = queue.poll()
                a.left?.let { queue.add(a.left) }
                a.right?.let { queue.add(a.right) }
                levelList.add(a.`val`)
            }

            list.add(levelList)
            levelIndex = queue.size
        }

        return list
    }
}

