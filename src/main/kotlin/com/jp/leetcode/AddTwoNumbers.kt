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
    var li1 = AddTwoNumbers().parseListNode(9)
    var li2 = AddTwoNumbers().parseListNode(999999991)

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
        val result = ListNode(0)
        var current = result
        var plusNumber = 0

        while (tempL1 != null || tempL2 != null) {
            val tempSum = (tempL1?.`val` ?: 0) + (tempL2?.`val` ?: 0) + plusNumber
            val number = tempSum % 10

            current = current.apply { next = ListNode(number) }.next!!

            plusNumber = tempSum / 10
            tempL1 = tempL1?.next
            tempL2 = tempL2?.next
        }

        if (plusNumber != 0) {
            current.apply { next = ListNode(plusNumber) }
        }

        return result.next
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
}

