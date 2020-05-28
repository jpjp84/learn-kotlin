package com.jp.leetcode

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
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
        val pStack = Stack<TreeNode>()
        pStack.push(p)
        val qStack = Stack<TreeNode>()
        qStack.push(q)

        while (!pStack.isEmpty() || !qStack.isEmpty()) {
            val pValue = pStack.pop()
            val qValue = qStack.pop()

            if (pValue == null && qValue == null) {
                continue
            }

            if (pValue == null || qValue == null || pValue.`val` != qValue.`val`) {
                return false
            }

            pStack.push(pValue.left)
            pStack.push(pValue.right)
            qStack.push(qValue.left)
            qStack.push(qValue.right)
        }
        return true
    }
}

