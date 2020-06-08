package com.jp.hackrank

import java.util.*


fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val s = scan.nextLine()

    println(args)

    val result = CountingValleys().countingValleys(n, s)

    println(result)
}

class CountingValleys {

    fun countingValleys(n: Int, s: String): Int {
        var sum = 0
        var count = 0
        s.toList().map {
            val prevSum = sum
            when (it) {
                'U' -> sum += 1
                'D' -> sum -= 1
            }
            if (prevSum < 0 && sum == 0) {
                count++
            }
        }
        return count
    }
}
