package com.jp.algorithm.problem.dynamicprogram

fun main() {
    val N = 4
    val number = 17
    println("Solution : ${ExpressionN().solution(N, number)}")
}

class ExpressionN {
    /*
    X1 : 5
    X2 : 55, X1 Operation(5 + 5, 5 - 5, 5 * 5, 5 / 5)
    X3 : 555, 55 + 5, 55 - 5, 55 * 5, 55 / 5, 5 + 55, 5 - 55, 5 * 55, 5 / 55, 5 + 5 + 5,...
    X4 : 5555, 555 + 5, 55 + 55, 5 + 555
     */

    fun solution(N: Int, number: Int): Int {
        val numberMap = HashMap<Int, MutableSet<Int>>()
        var count = 0

        while (count++ <= 8) {
            val key = (1..count).fold(0) { acc, _ -> (acc * 10) + N }
            var left = key
            var right = 0
            numberMap.computeIfAbsent(key) { mutableSetOf() }.addAll(operation(left, right))

            while (left != 0) {
                right = (right * 10) + (left % 10)
                left /= 10

                numberMap[left]?.map { leftElement ->
                    numberMap[right]?.map { rightElement ->
                        numberMap[key]!!.addAll(operation(leftElement, rightElement))
                    }
                }
            }

            if (numberMap[key]!!.contains(number)) return count
        }

        return -1
    }

    private fun operation(result: Int, N: Int): List<Int> {
        return mutableListOf(
                result + N,
                result - N,
                N - result,
                if (result != 0 && N != 0) result * N else null,
                if (result != 0 && N != 0) N / result else null,
                if (result != 0 && N != 0) result / N else null
        ).filterNotNull()
    }
}
