package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val nums = intArrayOf(1, 2, 2, 3, 4, 4, 3)
    val tree = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3)
            right = TreeNode(4)
        }
        right = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(3)
        }
    }
    val result = SymmetricTree().isSymmetric(tree)
    println("Solution : $result, ${nums.toList()}}")
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

}

class SymmetricTree() {

    fun isSymmetric(root: TreeNode?): Boolean {
//        return isSymmetric(root, root)
        return isSymmetricByQueue(root)
    }

    private fun isSymmetric(tn1: TreeNode?, tn2: TreeNode?): Boolean {
        if (tn1 == null && tn2 == null) {
            return true
        }
        if (tn1 == null || tn2 == null) {
            return false
        }

        return tn1.`val` == tn2.`val` &&
                isSymmetric(tn1.left, tn2.right) &&
                isSymmetric(tn1.right, tn2.left)
    }

    private fun isSymmetricByQueue(root: TreeNode?): Boolean {
        if (root == null) {
            return false
        }

        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        queue.add(root)

        while (!queue.isEmpty()) {
            val tn1 = queue.poll()
            val tn2 = queue.poll()

            if (tn1 == null && tn2 == null) {
                continue
            }

            if (tn1 == null || tn2 == null) {
                return false
            }

            if (tn1.`val` != tn2.`val`) {
                return false
            }

            queue.add(tn1.left)
            queue.add(tn2.right)
            queue.add(tn1.right)
            queue.add(tn2.left)
            println(queue.toList())
        }

        return false
    }
}

