package com.jp.algorithm.problem.implements

import java.math.*
import java.util.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*

// Complete the extraLongFactorials function below.
fun extraLongFactorials(n: Int): Unit {
    val factorial = (2..n).fold(BigInteger("1")) { acc, i -> acc * BigInteger("$i") }
    println(factorial)
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    extraLongFactorials(n)
}
