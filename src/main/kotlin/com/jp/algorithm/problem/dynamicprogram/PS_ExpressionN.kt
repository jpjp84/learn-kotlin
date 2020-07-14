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
        var numberList = mutableListOf<Int>()
        var count = 0

        while (count++ <= 8) {
            numberList = numberList
                    .flatMap { operation(it, N) }
                    .toMutableList()

            (1..count).fold(0) { acc, _ -> (acc * 10) + N }.let { fullNumber ->
                numberList.add(fullNumber)

                var a = fullNumber
                var b = 0
                repeat(count - 1) {
                    a /= 10
                    b = (b * 10) + (a % 10)
                    numberList.addAll(operation(a, b))
                }
            }

            if (numberList.contains(number)) return count
        }

        return -1
    }

    private fun operation(result: Int, N: Int): List<Int> {
        return mutableListOf(
                result + N,
                result - N,
                result * N,
                if (result != 0) N / result else null
        ).filterNotNull()
    }
}
