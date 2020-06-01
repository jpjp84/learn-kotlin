package com.jp.leetcode


/**
 * https://www.notion.so/Best-Time-to-Buy-and-Sell-Stock-II-fd5a636d767946d4854e4ef503830592
 */

fun main() {
    val prices = intArrayOf(1, 3, 4, 5, 18)
    println("Solution : ${BestTimetoBuyandSellStock2().maxProfit(prices)}")
}

class BestTimetoBuyandSellStock2 {
    fun maxProfit(prices: IntArray): Int {
        var sum = 0

        for (i in prices.size - 2 downTo 0) {
            if (prices[i + 1] > prices[i]) {
                sum += prices[i + 1] - prices[i]
            }
        }
        return sum
    }
}

