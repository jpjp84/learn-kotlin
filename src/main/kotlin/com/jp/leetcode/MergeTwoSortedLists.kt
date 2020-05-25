package com.jp.leetcode


fun main() {
    //Binary Search

    var list1: MergeTwoSortedLists.ListNode? = null
    var list2: MergeTwoSortedLists.ListNode? = null

    listOf(1, 2, 4).map {
        if (list1 == null) {
            list1 = MergeTwoSortedLists.ListNode(it)
            return@map
        }
        list1!!.put(MergeTwoSortedLists.ListNode(it))
    }
    listOf(3, 4, 5, 6).map {
        if (list2 == null) {
            list2 = MergeTwoSortedLists.ListNode(it)
            return@map
        }
        list2!!.put(MergeTwoSortedLists.ListNode(it))
    }

    MergeTwoSortedLists().mergeTwoLists(list1, list2)?.print()
}

class MergeTwoSortedLists {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val resultListNode = ListNode(0)
        var currentList: ListNode = resultListNode
        var tempList1 = list1
        var tempList2 = list2

        while (tempList1 != null || tempList2 != null) {
            when {
                tempList1 == null -> {
                    currentList.next = tempList2
                    return resultListNode
                }
                tempList2 == null -> {
                    currentList.next = tempList1
                    return resultListNode
                }
                tempList1.`val` <= tempList2.`val` -> {
                    currentList.next = tempList1
                    tempList1 = tempList1.next
                }
                else -> {
                    currentList.next = tempList2
                    tempList2 = tempList2.next
                }
            }
            currentList = currentList.next!!
        }

        return resultListNode.next
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

        fun put(value: ListNode) {
            if (next == null) {
                next = value
                return
            }

            next!!.put(value)
        }
    }
}
