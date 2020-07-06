package com.jp.algorithm

/**
 *
 */

fun main() {

    val gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA", "RUBY", "DIA", "DIA", "SAPPHIRE")
//    val gems = arrayOf("AA", "AB", "AC", "AA", "AC")
//    val gems = arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")
    println("Solution : ${ShoppingGem().solution(gems).toList()}")
}

class ShoppingGem {
    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(1, gems.size)
        val tokenKindSize = gems.toSet().size
        gems.foldIndexed(mutableMapOf<String, Int>()) { index, gemMap, gem ->
            gemMap[gem] = index
            if (gemMap.size == tokenKindSize) {
                val start = gemMap.minBy { it.value }!!.value
                val end = gemMap.maxBy { it.value }!!.value
                if (end - start < answer[1] - answer[0]) {
                    answer[0] = start + 1
                    answer[1] = end + 1
                }
            }
            gemMap
        }

        return answer
    }

    fun solution2(gems: Array<String>): IntArray {
        val answer = IntArray(2)
        val tokenKindSize = gems.toSet().size
        var tokenSize = tokenKindSize

        if (tokenKindSize == gems.size) {
            return intArrayOf(1, gems.size)
        }
        if (tokenSize == 1) {
            return intArrayOf(1, 1)
        }
        loop@ while (tokenSize <= gems.size) {
            for (i in 0..gems.size - tokenSize) {
                val sliceSize = gems.sliceArray(i until (i + tokenSize)).toSet().size
                if (sliceSize == tokenKindSize) {
                    answer[0] = i + 1
                    answer[1] = i + tokenSize
                    break@loop
                }
            }
            tokenSize++
        }
        return answer
    }
}
