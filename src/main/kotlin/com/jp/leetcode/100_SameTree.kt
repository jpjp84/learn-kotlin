package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Same-Tree-b97d4148eabe44608f2e18ca4ac9a529
 */

fun main() {
    val tree1 = TreeNode(1).apply {
        left = TreeNode(2)
    }
    val tree2 = TreeNode(1).apply {
        right = TreeNode(2)
    }
    println("Solution : ${SameTree().isSameTree(tree1, tree2)}")
}

class SameTree {

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        val pQueue: Queue<TreeNode> = LinkedList()
        pQueue.offer(p)
        val qQueue: Queue<TreeNode> = LinkedList()
        qQueue.offer(q)

        while (!pQueue.isEmpty() || !qQueue.isEmpty()) {
            val pValue = pQueue.poll()
            val qValue = qQueue.poll()

            if (pValue == null && qValue == null) {
                continue
            }

            if (pValue == null || qValue == null || pValue.`val` != qValue.`val`) {
                return false
            }

            pQueue.offer(pValue.left)
            pQueue.offer(pValue.right)
            qQueue.offer(qValue.left)
            qQueue.offer(qValue.right)
        }
        return true
    }
}

