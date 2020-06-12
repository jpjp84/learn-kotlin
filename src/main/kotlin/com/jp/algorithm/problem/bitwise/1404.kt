package com.jp.algorithm.problem.bitwise

/**
 *
 */
fun main() {
    val s = "1101"
    println("Solution : ${NumberOfStepsToReduce().numSteps(s)}")
}

class NumberOfStepsToReduce {

    fun numSteps(s: String): Int {
        var count = 0
        var answer = s

        while (answer != "1") {
            count++
            if (answer.last() == '0') {
                answer = answer.substring(0..answer.length - 2)
            } else {
                var post = ""
                while (answer.isNotEmpty()) {
                    if (answer.last() == '0') {
                        answer = answer.dropLast(1)
                        break
                    }
                    answer = answer.dropLast(1)
                    post += "0"
                }
                answer = "${answer}1$post"
            }
        }
        return count
    }
}
