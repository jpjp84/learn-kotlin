package com.jp.algorithm.problem

import kotlin.math.cos

/**
 * https://www.notion.so/Gas-Station-291adcbe09ef44be9dc6dd464bd94821
 */


fun main() {
    val gas = intArrayOf(3, 3, 4)
    val cost = intArrayOf(3, 4, 4)
//    val gas = intArrayOf(1, 2, 3, 4, 5)
//    val cost = intArrayOf(3, 4, 5, 1, 2)
    println("Solution : ${GasStation().canCompleteCircuit(gas, cost)}")
}

class GasStation {

    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        gas.mapIndexed { index, gasElement ->
            if (cost[index] > gasElement) {
                return@mapIndexed
            }
            val foundCost = findCircuitCost(gas, cost, index, gasElement, 0)
            if (foundCost > 0) {
                return index
            }
        }
        return -1
    }

    private tailrec fun findCircuitCost(gas: IntArray, costs: IntArray, index: Int, cost: Int, loop: Int): Int {
        val currentCost = cost - costs[index]
        if (currentCost < 0) {
            return -1
        }

        if (loop == gas.size - 1) {
            return cost
        }

        val nextIndex = if (index + 1 < gas.size) index + 1 else 0
        return findCircuitCost(gas, costs, nextIndex, currentCost + gas[nextIndex], loop + 1)
    }
}
