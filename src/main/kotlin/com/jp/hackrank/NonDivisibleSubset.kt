package com.jp.hackrank


fun main() {
//    val first_multiple_input = readLine()!!.trimEnd().split(" ")

    val n = 4

    val k = 3

    val s = arrayOf(1, 7, 2, 4)

    val result = NonDivisibleSubset().nonDivisibleSubset(k, s)

    println(result)
}

class NonDivisibleSubset {

    fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
        val answer = mutableSetOf<Int>()
        s.indices.map { first ->
            (first + 1 until s.size).map { second ->
                if ((s[first] + s[second]) % k != 0) {
                    answer.add(s[first])
                    answer.add(s[second])
                }
            }
        }
        return answer.size
    }
}
