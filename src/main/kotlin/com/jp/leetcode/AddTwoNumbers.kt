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
    var li2 = AddTwoNumbers().parseListNode(9999999991)

    var result = AddTwoNumbers().addTwoNumbers(li1, li2)
    result?.print()
}

class AddTwoNumbers() {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val l1Number = parseReverseListNode(l1)
        val l2Number = parseReverseListNode(l2)
        println("sum $l1Number, $l2Number => ${l1Number + l2Number}")

        return parseListNode(l1Number + l2Number)
    }

    private fun parseReverseListNode(listNode: ListNode?): Int {
        if (listNode == null) {
            return 0
        }

        if (listNode.next == null) {
            return listNode.`val`
        }

        val a = parseReverseListNode(listNode.next)
        return (a * 10) + listNode.`val`
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