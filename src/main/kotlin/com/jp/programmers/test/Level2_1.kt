package com.jp.programmers.test

import java.util.*
import kotlin.math.max
import kotlin.math.pow


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val nums = intArrayOf(1, 2, 7, 6, 4)


    println("Solution : ${Prac2_1().solution(nums)}")
}

class Prac2_1 {
    fun solution(nums: IntArray): Int {
        var answer = 0
        val maxNum: Int = nums.max()!!
        val secMaxNum: Int = nums.filter { it != maxNum }.max()!!
        val thirdMaxNum: Int = nums.filter { it != maxNum }.max()!!
        val maxSum = maxNum + secMaxNum + thirdMaxNum
        val availablePrimes = mutableListOf<Int>()

        (3..maxSum).map {
            for (i in 2..it) {
                if (it == i) break
                if (it % i != 0) continue
                return@map
            }
            availablePrimes.add(it)
        }

        println(availablePrimes.toList())

        for (i in nums.indices) {
            val firstNum = nums[i]
            for (j in i + 1 until nums.size) {
                val secNum = nums[j]
                for (k in j + 1 until nums.size) {
                    if (availablePrimes.contains(firstNum + secNum + nums[k])) {
                        println("[$firstNum, $secNum, ${nums[k]}]")
                        answer++
                    }
                }
            }
        }

        return answer
    }
}
