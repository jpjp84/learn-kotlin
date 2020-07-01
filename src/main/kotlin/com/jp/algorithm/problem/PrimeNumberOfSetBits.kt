package com.jp.algorithm.problem

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
                    val count = Integer.toBinaryString(it).count { bit -> bit == '1' }
                    if (primeList.contains(count)) {
                        return@map primeList[count]
                    }

                    val isPrime = isPrime(count)
                    primeList[count] = isPrime
                    isPrime
                }
                .count { isPrime -> isPrime!! }
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 0 || n == 1) {
            return false
        }

        val sqrt = sqrt(n.toDouble()).toInt()
        for (i in 2..sqrt) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }
}
