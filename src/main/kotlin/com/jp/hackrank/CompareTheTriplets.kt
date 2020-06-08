package com.jp.hackrank

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


fun main(args: Array<String>) {
    val a = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

    val b = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

    val result = CompareTheTriplets().compareTriplets(a, b)

    println(result.joinToString(" "))
}

class CompareTheTriplets {

    // Complete the compareTriplets function below.
    fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {
        return a.zip(b).fold(arrayOf(0, 0)) { acc, pair ->
            when {
                pair.first > pair.second -> acc[0] += 1
                pair.first < pair.second -> acc[1] += 1
            }
            acc
        }
    }

}
