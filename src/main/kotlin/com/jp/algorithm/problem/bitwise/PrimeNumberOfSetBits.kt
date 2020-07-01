package com.jp.algorithm.problem.bitwise

import kotlin.math.sqrt


/**
 * https://www.notion.so/Prime-Number-of-Set-Bits-in-Binary-Representation-d87911f4f100466099543582416b1dc0
 */

fun main() {
    val L = 10
    val R = 15
    println("Solution : ${PrimeNumberOfSetBits().countPrimeSetBits(L, R)}")
}

class PrimeNumberOfSetBits {

    fun countPrimeSetBits(L: Int, R: Int): Int {
        val primeList = mutableMapOf<Int, Boolean>()

        return (L..R)
                .map {
                    val count = setBit(it)
                    if (primeList.contains(count)) {
                        return@map primeList[count]
                    }

                    val isPrime = isPrime(count)
                    primeList[count] = isPrime
                    isPrime
                }
                .count { isPrime -> isPrime!! }
    }

    fun setBit(n: Int): Int {
        var ones = 0
        var num = n
        while (num > 0) {
            ones++
            num = num and (num - 1)
        }
        return ones
    }

    private fun isPrime(n: Int): Boolean {
        return n > 1 && (2..sqrt(n.toDouble()).toInt()).none { n % it == 0 }
    }
}
