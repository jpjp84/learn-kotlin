package com.jp.algorithm.problem.bitwise

import kotlin.math.min

fun main() {
    val price = listOf(2, 5)
    val special = listOf(
            listOf(3, 0, 5), // -> 3
            listOf(1, 2, 10) // -> 1
    )
    val needs = listOf(3, 2)
    // -> sp1 + sp1 + sp1 + (p2*2) -> (3, 0) -> 1110 12
    // -> sp1 + sp1 + sp2 + (p1*2) -> (2, 1) -> 0111 8
    // -> sp1 + sp1 + (p1*2 + p2*~) -> (2, 0)-> 0110 6
    // -> sp1 + sp2 + (p1~~) -> (1, 1) -> 0011 3
    // -> sp1 + (p1~~) -> (1, 0) -> 0010 2
    // -> sp2 + (p1~~) -> (0, 1) -> 0001 1
    // -> (0, 0), (3, 1)
    // -> 0010 == 0100 == 1000


//    val price = listOf(2, 3, 4)
//    val special = listOf(
//            listOf(1, 1, 0, 4), // -> 1
//            listOf(2, 2, 1, 9)  // -> X
//    )
//    val needs = listOf(1, 0, 0)
//    [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
    // -> (1, 0)


    println("Solution : ${ShoppingOffers().shoppingOffers(price, special, needs)}")
}

class ShoppingOffers {
    fun shoppingOffers(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int {
        var answer = needs.foldIndexed(0) { index, acc, i -> acc + price[index] * i }

        val filteredSpecial = special.filter { list ->
            list.filterIndexed { index, _ -> index < needs.size }
                    .mapIndexed { index, i ->
                        if (i > needs[index]) {
                            return@filter false
                        }
                    }
            true
        }

        val bitInfos = filteredSpecial.map {
            it.zip(needs).fold(-1) { acc, pair ->
                if (pair.first == 0) {
                    return@fold acc
                }
                if (acc < 0) {
                    return@fold pair.second / pair.first
                }
                acc.coerceAtMost(pair.second / pair.first)
            }
        }

        if (bitInfos.isEmpty()) {
            var calculatedPrice = 0
            for (i in needs.indices) {
                calculatedPrice += needs[i] * price[i]
            }
            return calculatedPrice
        }

        val checked = mutableListOf<List<Int>>()
        val bitCount = bitInfos.sum()
        for (useSpecial in 1..1.shl(bitCount)) {
            val eachSpecialCount = getCombinations(bitInfos, bitCount, useSpecial)
            if (checked.contains(eachSpecialCount)) {
                continue
            }
            checked.add(eachSpecialCount)

            val used = filteredSpecial.foldIndexed(Array(filteredSpecial[0].size) { 0 }) { index, acc, list ->
                list.mapIndexed { elementIndex, i -> acc[elementIndex] += i * eachSpecialCount[index] }
                acc
            }

            val calculatedPrice = getCalculatedPrice(used, needs, price)
            if (calculatedPrice != null) {
                answer = min(answer, calculatedPrice)
            }
        }

        return answer
    }

    private fun getCalculatedPrice(used: Array<Int>, needs: List<Int>, price: List<Int>): Int? {
        var calculatedPrice = used[used.size - 1]
        for (i in needs.indices) {
            calculatedPrice += (needs[i] - used[i]) * price[i]
        }
        return calculatedPrice
    }

    private fun getCombinations(bitInfos: List<Int>, bitCount: Int, useSpecial: Int): List<Int> {
        var startIndex = 0
        return bitInfos.map { bitInfo ->
            "%${bitCount}s"
                    .format(Integer.toBinaryString(useSpecial))
                    .replace(' ', '0')
                    .substring(startIndex until startIndex + bitInfo)
                    .count { bit -> bit == '1' }
                    .also { startIndex = bitInfo }
        }
    }

    private fun getCombinationsByBitwise(bitInfos: List<Int>, bitCount: Int, useSpecial: Int): List<Int> {
        var processedBitCount = 0
        return bitInfos.map { bitInfo ->
            processedBitCount += bitInfo
            Integer.toBinaryString(
                    useSpecial.and((1.shl(bitInfo) - 1).shl(bitCount - processedBitCount))
            ).count { it == '1' }
        }
    }
}
