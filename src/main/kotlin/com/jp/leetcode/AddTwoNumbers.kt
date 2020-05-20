package com.jp.leetcode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

fun main() {
    // int overflow
    var li1 = AddTwoNumbers().parseListNode(5)
    var li2 = AddTwoNumbers().parseListNode(5)

    var result = AddTwoNumbers().addTwoNumbers(li1, li2)
    result?.print()
}

class AddTwoNumbers() {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null || l2 == null) {
            return null
        }

        var tempL1: ListNode? = l1
        var tempL2: ListNode? = l2
        var result: ListNode? = null
        var plusPrefixNumber = 0

        while (tempL1 != null || tempL2 != null) {
            val tempSum = (tempL1?.`val` ?: 0) + (tempL2?.`val` ?: 0) + plusPrefixNumber
            val number = tempSum % 10

            if (result == null) {
                result = ListNode(number)
            } else {
                putListNode(result, ListNode(number))
            }

            plusPrefixNumber = tempSum / 10
            tempL1 = tempL1?.next
            tempL2 = tempL2?.next
        }

        if (plusPrefixNumber != 0) {
            result?.let { putListNode(it, ListNode(plusPrefixNumber)) }
        }

        return result
    }

    fun putListNode(origin: ListNode, addListNode: ListNode): ListNode? {
        if (origin.next == null) {
            origin.next = addListNode
            return origin
        }

        return putListNode(origin.next!!, addListNode)
    }

    fun parseListNode(i: Int): ListNode? {
        val remainNumber = i / 10
        val popNumber = i % 10

        if (remainNumber == 0) {
            return ListNode(popNumber)
        }

        return ListNode(popNumber).apply {
            next = parseListNode(remainNumber)
        }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    fun print() {
        print("$`val`")
        this.next?.let {
            System.out.print(" -> ")
            it.print()
        }
    }
}
