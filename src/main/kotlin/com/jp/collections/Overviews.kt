package com.jp.collections

fun main() {
    mutableCollectionAndCollectionTest()
}

fun mutableCollectionAndCollectionTest() {
    val mutableList = mutableListOf("one", "two")
    mutableList.add("three")

    val list = listOf("one", "two", "one")
//    list.add("three") // error
    printAll(list)
    println()
    println(list.first())
    println()

    val set = setOf("one", "two", "one")
    printAll(set)
    println()
    println(set.last())
    println()

    val mutableSet = mutableSetOf("one", "two")
    mutableSet.add("one")
    printAll(mutableSet)
}

fun printAll(collection: Collection<String>) {
    for (element: String in collection) {
        print("$element ")
    }
}