package com.jp.leetcode


/**
 * https://www.notion.so/Best-Time-to-Buy-and-Sell-Stock-II-fd5a636d767946d4854e4ef503830592
 */

fun main() {
    val prices = intArrayOf(1, 3, 4, 5, 18)
    println("Solution : ${BestTimetoBuyandSellStock2().maxProfit(prices)}")
}

class BestTimetoBuyandSellStock2 {

    fun maxProfit2(prices: IntArray): Int {
        //buy의 금액보다 남은 array에 큰 수가 있어야 한다.
        //선택한 buy의 금액이 이전의 array에서 작은 수가 있으면 볼 필요가 없다.

        //나올 수 있는 경우의 수들을 계산해 본다.
        // (1, 5) (1, 3) (1, 6) (1, 4)  -> (1, 2) (1, 3) (1, 4) (1, 5)
        // (5, 6)                       -> (2, 4)
        // (3, 6) (3, 4)                -> (3, 4) (3, 5)

        // (1, 2) (1, 3), (1, 5)
        // (3, 5)
        // (4, 5)

        val positions: MutableList<Pair<Int, Int>> = mutableListOf()
        prices.mapIndexed { buyIndex, price ->
            prices.slice(buyIndex + 1 until prices.size).mapIndexed { sellIndex, i ->
                if (i > price) {
                    positions.add(Pair(buyIndex, buyIndex + sellIndex + 1))
                }
            }
        }

        var index = 0
        var result = 0
        while (index != positions.size - 1) {
            var lastSelect: Pair<Int, Int> = positions[index] ?: return 0
            var sum = prices[lastSelect.second] - prices[lastSelect.first]

            positions.mapIndexed { positionIndex, position ->
                if (index >= positionIndex) {
                    return@mapIndexed
                }

                if (lastSelect.first == position.first || lastSelect.second >= position.first) {
                    return@mapIndexed
                }
                println("$lastSelect, $position")
                lastSelect = position
                sum += prices[lastSelect.second] - prices[lastSelect.first]
            }
            index++
            result = Math.max(sum, result)
            println("---------")
        }

        return result
    }
}

