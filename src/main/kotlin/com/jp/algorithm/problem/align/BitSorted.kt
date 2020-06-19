package com.jp.algorithm.problem.align

import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the bigSorting function below.
fun bigSorting(unsorted: Array<String>): Array<String> {

//    return unsorted.sortedBy { BigInteger(it) }.toTypedArray()
    quickSort(0, unsorted.size - 1, unsorted)
    return emptyArray()
}

fun quickSort(first: Int, last: Int, unsorted: Array<String>) {
    println("$first, $last")
    val pivot = (first + last) / 2
    var firstIndex = first
    var lastIndex = last
    while (firstIndex < lastIndex) {
//        if ()
        if (BigInteger(unsorted[firstIndex]) < BigInteger(unsorted[pivot])) {
            firstIndex++
            continue
        }

        if (BigInteger(unsorted[lastIndex]) > BigInteger(unsorted[pivot])) {
            if (lastIndex > pivot) {
                lastIndex--
            }
            continue
        }

        if (BigInteger(unsorted[firstIndex]) > BigInteger(unsorted[lastIndex])) {
            unsorted[firstIndex] = unsorted[lastIndex].also { unsorted[lastIndex] = unsorted[firstIndex] }
        }
        firstIndex++
        lastIndex--
    }

    quickSort(first, pivot, unsorted)
    quickSort(pivot, last, unsorted)

}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val unsorted = Array<String>(n, { "" })
    for (i in 0 until n) {
        val unsortedItem = scan.nextLine()
        unsorted[i] = unsortedItem
    }

    val result = bigSorting(unsorted)

    println(result.joinToString("\n"))
}
